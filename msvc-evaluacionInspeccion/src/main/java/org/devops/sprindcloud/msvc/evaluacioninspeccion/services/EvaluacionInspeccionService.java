package org.devops.sprindcloud.msvc.evaluacioninspeccion.services;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.DefectoDetectado;
import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.EvaluacionInspeccion;

import java.util.List;
import java.util.Optional;

public interface EvaluacionInspeccionService {

    List<EvaluacionInspeccion> listar();
    Optional<EvaluacionInspeccion> porId(Long id);
    Optional<EvaluacionInspeccion> porOrdenInspeccionId(Long ordenInspeccionId);
    EvaluacionInspeccion guardar(EvaluacionInspeccion evaluacionInspeccion);

    void eliminar(Long id);

    Optional<DefectoDetectado> registrarDefecto(
            DefectoDetectado defectoDetectado,
            Long evaluacionInspeccionId
    );

    Optional<EvaluacionInspeccion> determinarResultado(
            Long evaluacionInspeccionId
    );
}