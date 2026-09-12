package org.devops.sprindcloud.msvc.evaluacioninspeccion.controllers;

import feign.FeignException;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.DefectoDetectado;
import org.devops.sprindcloud.msvc.evaluacioninspeccion.models.EvaluacionInspeccion;

import org.devops.sprindcloud.msvc.evaluacioninspeccion.services.EvaluacionInspeccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionInspeccionController {

    @Autowired
    private EvaluacionInspeccionService service;

    @GetMapping
    public ResponseEntity<List<EvaluacionInspeccion>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {

        Optional<EvaluacionInspeccion> op = service.porId(id);

        if (op.isPresent()) {

            return ResponseEntity.ok(op.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/orden/{ordenInspeccionId}")
    public ResponseEntity<?> detallePorOrden(@PathVariable Long ordenInspeccionId) {

        Optional<EvaluacionInspeccion> op = service.porOrdenInspeccionId(ordenInspeccionId);

        if (op.isPresent()) {

            return ResponseEntity.ok(op.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody EvaluacionInspeccion evaluacionInspeccion) {

        EvaluacionInspeccion evaluacionDB = service.guardar(evaluacionInspeccion);

        return ResponseEntity.status(HttpStatus.CREATED).body(evaluacionDB);
    }

    @PostMapping("/{evaluacionInspeccionId}/defectos")
    public ResponseEntity<?> registrarDefecto(@RequestBody DefectoDetectado defectoDetectado, @PathVariable Long evaluacionInspeccionId) {

        Optional<DefectoDetectado> op = service.registrarDefecto(defectoDetectado, evaluacionInspeccionId);

        if (op.isPresent()) {

            return ResponseEntity.status(HttpStatus.CREATED).body(op.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{evaluacionInspeccionId}/determinarResultado")
    public ResponseEntity<?> determinarResultado(@PathVariable Long evaluacionInspeccionId) {

        Optional<EvaluacionInspeccion> op;

        try {

            op = service.determinarResultado(evaluacionInspeccionId);

        } catch (FeignException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Mensaje", "Error en la comunicación con el microservicio de ejecución: " + e.getMessage()));

        } catch (IllegalStateException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Mensaje", e.getMessage()));
        }

        if (op.isPresent()) {

            return ResponseEntity.ok(op.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        Optional<EvaluacionInspeccion> op = service.porId(id);

        if (op.isPresent()) {

            service.eliminar(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}