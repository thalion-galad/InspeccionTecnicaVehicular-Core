package org.devops.sprindcloud.msvc.evaluacioninspeccion.services;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.clients.EjecucionInspeccionClientRest;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.DefectoDetectado;
import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.EvaluacionInspeccion;
import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.ResultadoInspeccion;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.entity.EjecucionInspeccion;
import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.entity.PruebaInspeccion;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.repositories.EvaluacionInspeccionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionInspeccionImpl implements EvaluacionInspeccionService {

    @Autowired
    private EvaluacionInspeccionRepository repository;

    @Autowired
    private EjecucionInspeccionClientRest ejecucionClient;

    @Override
    @Transactional(readOnly = true)
    public List<EvaluacionInspeccion> listar() {

        return (List<EvaluacionInspeccion>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluacionInspeccion> porId(Long id) {

        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EvaluacionInspeccion> porOrdenInspeccionId(
            Long ordenInspeccionId) {

        return repository.findByOrdenInspeccionId(
                ordenInspeccionId
        );
    }

    @Override
    @Transactional
    public EvaluacionInspeccion guardar(
            EvaluacionInspeccion evaluacionInspeccion) {

        return repository.save(evaluacionInspeccion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<DefectoDetectado> registrarDefecto(
            DefectoDetectado defectoDetectado,
            Long evaluacionInspeccionId) {

        Optional<EvaluacionInspeccion> op =
                repository.findById(evaluacionInspeccionId);

        if (op.isPresent()) {

            EvaluacionInspeccion evaluacionInspeccion =
                    op.get();

            evaluacionInspeccion.addDefectoDetectado(
                    defectoDetectado
            );

            repository.save(evaluacionInspeccion);

            return Optional.of(defectoDetectado);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<EvaluacionInspeccion> determinarResultado(
            Long evaluacionInspeccionId) {

        Optional<EvaluacionInspeccion> op =
                repository.findById(evaluacionInspeccionId);

        if (op.isEmpty()) {

            return Optional.empty();
        }

        EvaluacionInspeccion evaluacionInspeccion =
                op.get();

        EjecucionInspeccion ejecucionInspeccion =
                ejecucionClient.detalle(
                        evaluacionInspeccion
                                .getEjecucionInspeccionId()
                );
        if(ejecucionInspeccion.getFechaFin()==null){

            throw new IllegalStateException(
                    "La ejecución todavía no finalizó"
            );

        }

        if (ejecucionInspeccion.getPruebas() == null ||
                ejecucionInspeccion.getPruebas().isEmpty()) {

            throw new IllegalStateException(
                    "La ejecución no contiene pruebas registradas"
            );
        }

        boolean pruebasPendientes =
                ejecucionInspeccion
                        .getPruebas()
                        .stream()
                        .filter(prueba ->
                                Boolean.TRUE.equals(
                                        prueba.getObligatoria()
                                )
                        )
                        .anyMatch(prueba ->
                                prueba.getResultado() == null ||
                                        prueba.getResultado()
                                                .getConforme() == null
                        );

        if (pruebasPendientes) {

            throw new IllegalStateException(
                    "Existen pruebas obligatorias pendientes"
            );
        }

        boolean pruebaNoConforme =
                ejecucionInspeccion
                        .getPruebas()
                        .stream()
                        .map(PruebaInspeccion::getResultado)
                        .filter(resultado ->
                                resultado != null
                        )
                        .anyMatch(resultado ->
                                Boolean.FALSE.equals(
                                        resultado.getConforme()
                                )
                        );

        boolean defectoGrave =
                evaluacionInspeccion.tieneDefectoGrave();

        if (pruebaNoConforme || defectoGrave) {

            evaluacionInspeccion.setResultado(
                    ResultadoInspeccion.DESAPROBADO
            );

        } else {

            evaluacionInspeccion.setResultado(
                    ResultadoInspeccion.APROBADO
            );
        }

        evaluacionInspeccion.setFechaEvaluacion(
                LocalDateTime.now()
        );

        repository.save(evaluacionInspeccion);

        return Optional.of(evaluacionInspeccion);
    }
}