package org.devops.sprindcloud.msvc.evaluacioninspeccion.repositories;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.EvaluacionInspeccion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EvaluacionInspeccionRepository
        extends CrudRepository<EvaluacionInspeccion, Long> {

    Optional<EvaluacionInspeccion> findByOrdenInspeccionId(
            Long ordenInspeccionId
    );

    Optional<EvaluacionInspeccion> findByEjecucionInspeccionId(
            Long ejecucionInspeccionId
    );
}