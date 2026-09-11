package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.services;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity.EjecucionInspeccion;

import java.util.List;
import java.util.Optional;

public interface EjecucionInspeccionService {
    List<EjecucionInspeccion> listar();
    Optional<EjecucionInspeccion> porId(Long id);
    EjecucionInspeccion guardar(EjecucionInspeccion ejecucionInspeccion);
    void eliminar(Long id);
}
