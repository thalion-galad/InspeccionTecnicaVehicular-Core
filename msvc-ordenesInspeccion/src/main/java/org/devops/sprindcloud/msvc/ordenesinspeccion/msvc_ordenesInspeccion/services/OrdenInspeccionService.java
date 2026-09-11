package org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.services;

import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models.entity.EstadoOrden;
import org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models.entity.OrdenInspeccion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrdenInspeccionService {

    List<OrdenInspeccion> listar();

    Optional<OrdenInspeccion> porId(Long id);

    OrdenInspeccion guardar(OrdenInspeccion orden);

    void eliminar(Long id);


    List<OrdenInspeccion> listarPorVehiculo(Long vehiculoId);

    List<OrdenInspeccion> listarPorSolicitud(Long solicitudId);

    List<OrdenInspeccion> listarPorInspector(Long inspectorUsuarioId);

    List<OrdenInspeccion> listarPorEstado(EstadoOrden estado);


    boolean tieneOrdenActiva(Long vehiculoId);


    Optional<OrdenInspeccion> iniciar(Long id);

    Optional<OrdenInspeccion> suspender(Long id, String motivo);

    Optional<OrdenInspeccion> reanudar(Long id);

    Optional<OrdenInspeccion> completar(Long id);

    Optional<OrdenInspeccion> anular(Long id, String motivo);


}
