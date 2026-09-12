package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.repositories;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.entity.EjecucionInspeccion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EjecucionInspeccionRepository
        extends CrudRepository<EjecucionInspeccion, Long> {

    Optional<EjecucionInspeccion> findByOrdenInspeccionId(
            Long ordenInspeccionId
    );

    boolean existsByOrdenInspeccionId(
            Long ordenInspeccionId
    );
}