package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.clients;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.OrdenInspeccion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "msvc-ordenes-inspeccion",
        url = "http://localhost:8010/api/ordenes-inspeccion"
)
public interface OrdenInspeccionClientRest {

    @GetMapping("/{id}")
    OrdenInspeccion detalle(@PathVariable Long id);
}