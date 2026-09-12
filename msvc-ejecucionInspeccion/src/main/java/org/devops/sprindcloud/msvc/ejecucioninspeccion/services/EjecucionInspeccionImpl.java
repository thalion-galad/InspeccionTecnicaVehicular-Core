package org.devops.sprindcloud.msvc.ejecucioninspeccion.services;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.clients.OrdenInspeccionClientRest;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.models.entity.EjecucionInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.models.entity.EstadoPrueba;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.models.entity.PruebaInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.models.entity.ResultadoPrueba;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.models.OrdenInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.repositories.EjecucionInspeccionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EjecucionInspeccionImpl implements EjecucionInspeccionService {

    @Autowired
    private EjecucionInspeccionRepository repository;

    @Autowired
    private OrdenInspeccionClientRest ordenClient;


    @Override
    @Transactional(readOnly = true)
    public List<EjecucionInspeccion> listar() {

        return (List<EjecucionInspeccion>) repository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EjecucionInspeccion> porId(Long id) {

        return repository.findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EjecucionInspeccion> porOrdenInspeccionId(Long ordenInspeccionId) {

        return repository.findByOrdenInspeccionId(ordenInspeccionId);
    }


    @Override
    @Transactional
    public EjecucionInspeccion crearEjecucion(EjecucionInspeccion ejecucionInspeccion) {

        if (ejecucionInspeccion.getOrdenInspeccionId() == null) {

            throw new IllegalStateException("La ejecución debe estar asociada a una orden de inspección");
        }

        OrdenInspeccion orden = ordenClient.detalle(ejecucionInspeccion.getOrdenInspeccionId());
        if (!"EN_PROCESO".equals(orden.getEstado())) {
            throw new IllegalStateException("La orden debe encontrarse EN_PROCESO para crear la ejecución");
        }

        if (orden == null) {

            throw new IllegalStateException("No se encontró la orden de inspección");
        }

        if (repository.existsByOrdenInspeccionId(ejecucionInspeccion.getOrdenInspeccionId())) {

            throw new IllegalStateException("La orden ya posee una ejecución de inspección");
        }

        if (ejecucionInspeccion.getPruebas() == null || ejecucionInspeccion.getPruebas().isEmpty()) {

            throw new IllegalStateException("La ejecución debe contener pruebas de inspección");
        }

        for (PruebaInspeccion prueba : ejecucionInspeccion.getPruebas()) {

            if (prueba.getTipoPrueba() == null) {

                throw new IllegalStateException("Todas las pruebas deben indicar su tipo");
            }

            if (prueba.getObligatoria() == null) {
                prueba.setObligatoria(true);
            }

            prueba.setEstado(EstadoPrueba.PENDIENTE);

            if (prueba.getResultado() != null) {

                throw new IllegalStateException("No se puede crear una ejecución con resultados ya registrados");
            }
        }

        ejecucionInspeccion.setFechaInicio(null);
        ejecucionInspeccion.setFechaFin(null);

        return repository.save(ejecucionInspeccion);
    }

    @Override
    @Transactional
    public Optional<EjecucionInspeccion> iniciarPrueba(Long ejecucionId, Long pruebaId) {

        Optional<EjecucionInspeccion> op = repository.findById(ejecucionId);

        if (op.isEmpty()) {
            return Optional.empty();
        }

        EjecucionInspeccion ejecucion = op.get();

        validarEjecucionAbierta(ejecucion);

        validarOrdenEnProceso(ejecucion);

        PruebaInspeccion prueba = buscarPrueba(ejecucion, pruebaId);

        if (prueba.getEstado() != EstadoPrueba.PENDIENTE) {

            throw new IllegalStateException("Solo una prueba pendiente puede iniciarse");
        }

        prueba.setEstado(EstadoPrueba.EN_PROCESO);

        if (ejecucion.getFechaInicio() == null) {

            ejecucion.setFechaInicio(LocalDateTime.now());
        }

        repository.save(ejecucion);

        return Optional.of(ejecucion);
    }


    @Override
    @Transactional
    public Optional<EjecucionInspeccion> registrarResultado(Long ejecucionId, Long pruebaId, ResultadoPrueba resultado) {

        Optional<EjecucionInspeccion> op = repository.findById(ejecucionId);

        if (op.isEmpty()) {
            return Optional.empty();
        }

        EjecucionInspeccion ejecucion = op.get();

        validarEjecucionAbierta(ejecucion);

        validarOrdenEnProceso(ejecucion);

        PruebaInspeccion prueba = buscarPrueba(ejecucion, pruebaId);

        if (prueba.getEstado() != EstadoPrueba.EN_PROCESO) {

            throw new IllegalStateException("La prueba debe estar en proceso antes de registrar su resultado");
        }

        if (resultado == null) {

            throw new IllegalStateException("Debe registrar un resultado para la prueba");
        }

        if (resultado.getConforme() == null) {

            throw new IllegalStateException("Debe indicar si la prueba es conforme o no conforme");
        }


        if (resultado.getValor() != null && (resultado.getUnidad() == null || resultado.getUnidad().isBlank())) {

            throw new IllegalStateException("Una medición debe indicar su unidad");
        }

        prueba.setResultado(resultado);

        prueba.setEstado(EstadoPrueba.EJECUTADA);

        repository.save(ejecucion);

        return Optional.of(ejecucion);
    }


    @Override
    @Transactional
    public Optional<EjecucionInspeccion> finalizarEjecucion(Long ejecucionId) {

        Optional<EjecucionInspeccion> op = repository.findById(ejecucionId);

        if (op.isEmpty()) {
            return Optional.empty();
        }

        EjecucionInspeccion ejecucion = op.get();

        validarEjecucionAbierta(ejecucion);
        validarOrdenEnProceso(ejecucion);

        if (ejecucion.getPruebas() == null || ejecucion.getPruebas().isEmpty()) {

            throw new IllegalStateException("La ejecución no contiene pruebas");
        }

        boolean pruebasObligatoriasPendientes = ejecucion.getPruebas().stream().filter(prueba -> Boolean.TRUE.equals(prueba.getObligatoria())).anyMatch(prueba ->

                prueba.getEstado() != EstadoPrueba.EJECUTADA

                        ||

                        prueba.getResultado() == null

                        ||

                        prueba.getResultado().getConforme() == null);

        if (pruebasObligatoriasPendientes) {

            throw new IllegalStateException("Existen pruebas obligatorias pendientes");
        }

        ejecucion.setFechaFin(LocalDateTime.now());

        repository.save(ejecucion);

        return Optional.of(ejecucion);
    }

    private PruebaInspeccion buscarPrueba(EjecucionInspeccion ejecucion, Long pruebaId) {

        return ejecucion.getPruebas().stream().filter(prueba -> prueba.getId() != null && prueba.getId().equals(pruebaId)).findFirst().orElseThrow(() -> new IllegalStateException("La prueba no pertenece a la ejecución indicada"));
    }

    private void validarEjecucionAbierta(EjecucionInspeccion ejecucion) {

        if (ejecucion.getFechaFin() != null) {

            throw new IllegalStateException("La ejecución ya fue finalizada y no puede modificarse");
        }
    }

    private void validarOrdenEnProceso(EjecucionInspeccion ejecucion) {

        OrdenInspeccion orden = ordenClient.detalle(ejecucion.getOrdenInspeccionId());

        if (orden == null) {

            throw new IllegalStateException("No existe la orden asociada a la ejecución");
        }


        if (!"EN_PROCESO".equals(orden.getEstado())) {

            throw new IllegalStateException("La orden no se encuentra en estado EN_PROCESO");
        }
    }
}