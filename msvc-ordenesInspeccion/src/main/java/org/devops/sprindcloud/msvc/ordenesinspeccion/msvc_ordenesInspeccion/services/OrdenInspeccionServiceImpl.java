package org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.services;

import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models.entity.EstadoOrden;
import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models.entity.OrdenInspeccion;
import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.repositories.OrdenInspeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenInspeccionServiceImpl implements OrdenInspeccionService{

    @Autowired
    private OrdenInspeccionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenInspeccion> listar() {
        return (List<OrdenInspeccion>) repository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenInspeccion> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public OrdenInspeccion guardar(OrdenInspeccion orden) {
        return repository.save(orden);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenInspeccion> listarPorVehiculo(Long vehiculoId) {
        return repository.findByVehiculoId(vehiculoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenInspeccion> listarPorSolicitud(Long solicitudId) {
        return repository.findBySolicitudId(solicitudId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenInspeccion> listarPorInspector(Long inspectorUsuarioId) {
        return repository.findByInspectorUsuarioId(inspectorUsuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrdenInspeccion> listarPorEstado(EstadoOrden estado) {
        return repository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean tieneOrdenActiva(Long vehiculoId) {
        List<EstadoOrden> estadosActivos = Arrays.asList(
                EstadoOrden.PENDIENTE,
                EstadoOrden.EN_PROCESO,
                EstadoOrden.SUSPENDIDA
        );

        return repository
                .findFirstByVehiculoIdAndEstadoIn(
                        vehiculoId,
                        estadosActivos
                )
                .isPresent();
    }

    @Override
    @Transactional
    public Optional<OrdenInspeccion> iniciar(Long id) {
        Optional<OrdenInspeccion> ordenOpt =
                repository.findById(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion orden = ordenOpt.get();

            if (orden.getEstado() != EstadoOrden.PENDIENTE) {
                return Optional.empty();
            }

            orden.setEstado(EstadoOrden.EN_PROCESO);

            return Optional.of(repository.save(orden));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<OrdenInspeccion> suspender(Long id, String motivo) {
        Optional<OrdenInspeccion> ordenOpt =
                repository.findById(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion orden = ordenOpt.get();

            if (orden.getEstado() != EstadoOrden.EN_PROCESO) {
                return Optional.empty();
            }

            orden.setEstado(EstadoOrden.SUSPENDIDA);
            orden.setMotivoSuspension(motivo);

            return Optional.of(repository.save(orden));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<OrdenInspeccion> reanudar(Long id) {
        Optional<OrdenInspeccion> ordenOpt =
                repository.findById(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion orden = ordenOpt.get();

            if (orden.getEstado() != EstadoOrden.SUSPENDIDA) {
                return Optional.empty();
            }

            orden.setEstado(EstadoOrden.EN_PROCESO);
            orden.setMotivoSuspension(null);

            return Optional.of(repository.save(orden));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<OrdenInspeccion> completar(Long id) {
        Optional<OrdenInspeccion> ordenOpt =
                repository.findById(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion orden = ordenOpt.get();

            if (orden.getEstado() != EstadoOrden.EN_PROCESO) {
                return Optional.empty();
            }

            orden.setEstado(EstadoOrden.COMPLETADA);
            orden.setFechaCierre(LocalDateTime.now());

            return Optional.of(repository.save(orden));
        }

        return Optional.empty();
    }

    @Override
    public Optional<OrdenInspeccion> anular(Long id, String motivo) {
        Optional<OrdenInspeccion> ordenOpt =
                repository.findById(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion orden = ordenOpt.get();

            if (orden.getEstado() == EstadoOrden.COMPLETADA ||
                    orden.getEstado() == EstadoOrden.ANULADA) {

                return Optional.empty();
            }

            orden.setEstado(EstadoOrden.ANULADA);
            orden.setMotivoAnulacion(motivo);
            orden.setFechaCierre(LocalDateTime.now());

            return Optional.of(repository.save(orden));
        }

        return Optional.empty();
    }

}
