package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.services;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity.EjecucionInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity.ResultadoPrueba;

import java.util.List;
import java.util.Optional;

public interface EjecucionInspeccionService {

    List<EjecucionInspeccion> listar();

    Optional<EjecucionInspeccion> porId(Long id);

    Optional<EjecucionInspeccion> porOrdenInspeccionId(
            Long ordenInspeccionId
    );

    EjecucionInspeccion crearEjecucion(
            EjecucionInspeccion ejecucionInspeccion
    );

    Optional<EjecucionInspeccion> iniciarPrueba(
            Long ejecucionId,
            Long pruebaId
    );

    Optional<EjecucionInspeccion> registrarResultado(
            Long ejecucionId,
            Long pruebaId,
            ResultadoPrueba resultado
    );

    Optional<EjecucionInspeccion> finalizarEjecucion(
            Long ejecucionId
    );
}