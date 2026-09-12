package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.clients;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.EvaluacionInspeccion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "msvc-evaluacionInspeccion",
        url = "localhost:8020/api/evaluaciones"
)
public interface EvaluacionInspeccionClientRest {

    @GetMapping
    List<EvaluacionInspeccion> listar();

    @GetMapping("/{id}")
    EvaluacionInspeccion detalle(@PathVariable Long id);

    @GetMapping("/orden/{ordenInspeccionId}")
    EvaluacionInspeccion detallePorOrden(
            @PathVariable Long ordenInspeccionId
    );

    @PostMapping
    EvaluacionInspeccion crear(
            @RequestBody EvaluacionInspeccion evaluacionInspeccion
    );

    @PutMapping("/{evaluacionInspeccionId}/determinarResultado")
    EvaluacionInspeccion determinarResultado(
            @PathVariable Long evaluacionInspeccionId
    );

    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Long id);
}