package org.devops.sprindcloud.msvc.evaluacioninspeccion.clients;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.entity.OrdenInspeccion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "msvc-ordenes-inspeccion",
        url = "localhost:8010/api/ordenes"
)
public interface OrdenInspeccionClientRest {

    @GetMapping("/{id}")
    OrdenInspeccion detalle(@PathVariable Long id);
}