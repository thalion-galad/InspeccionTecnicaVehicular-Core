package org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.repositories;

import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models.entity.EstadoOrden;
import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models.entity.OrdenInspeccion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import java.util.Optional;

public interface OrdenInspeccionRepository
        extends CrudRepository<OrdenInspeccion, Long> {

    List<OrdenInspeccion> findByVehiculoId(Long vehiculoId);

    List<OrdenInspeccion> findBySolicitudId(Long solicitudId);

    List<OrdenInspeccion> findByInspectorUsuarioId(Long inspectorUsuarioId);

    List<OrdenInspeccion> findByEstado(EstadoOrden estado);

    Optional<OrdenInspeccion> findFirstByVehiculoIdAndEstadoIn(
            Long vehiculoId,
            List<EstadoOrden> estados
    );
}