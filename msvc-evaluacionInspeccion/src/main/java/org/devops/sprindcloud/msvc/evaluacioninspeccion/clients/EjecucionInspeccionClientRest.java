package org.devops.sprindcloud.msvc.evaluacioninspeccion.clients;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.entity.EjecucionInspeccion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "msvc-ejecucion-inspeccion",
        url = "localhost:8030/api/ejecuciones"
)
public interface EjecucionInspeccionClientRest {

    @GetMapping("/{id}")
    EjecucionInspeccion detalle(@PathVariable Long id);
}