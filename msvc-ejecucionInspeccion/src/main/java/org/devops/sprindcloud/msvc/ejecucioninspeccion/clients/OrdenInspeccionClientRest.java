package org.devops.sprindcloud.msvc.ejecucioninspeccion.clients;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.models.OrdenInspeccion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "msvc-ordenes-inspeccion",
        url = "localhost:8010/api/ordenes"
)
public interface OrdenInspeccionClientRest {

    @GetMapping("/{id}")
    OrdenInspeccion detalle(
            @PathVariable Long id
    );

    @PutMapping("/{id}/iniciar")
    OrdenInspeccion iniciar(
            @PathVariable Long id
    );

    @PutMapping("/{id}/completar")
    OrdenInspeccion completar(
            @PathVariable Long id
    );
}