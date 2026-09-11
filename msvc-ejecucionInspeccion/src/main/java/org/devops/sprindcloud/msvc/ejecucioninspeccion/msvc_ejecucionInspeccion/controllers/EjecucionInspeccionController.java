package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.controllers;

import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity.EjecucionInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.services.EjecucionInspeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ejecucion-inspeccion")
public class EjecucionInspeccionController {
    @Autowired
    private EjecucionInspeccionService service;

    @GetMapping
    public ResponseEntity<List<EjecucionInspeccion>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<EjecucionInspeccion> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody EjecucionInspeccion ejecucion) {
        EjecucionInspeccion ejecucionDB = service.guardar(ejecucion);
        return ResponseEntity.status(HttpStatus.CREATED).body(ejecucionDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody EjecucionInspeccion ejecucion, @PathVariable Long id) {
        Optional<EjecucionInspeccion> op = service.porId(id);
        if (op.isPresent()) {
            EjecucionInspeccion ejecucionDB = op.get();
            // Actualizar aquí los atributos propios de EjecucionInspeccion
            // ej: ejecucionDB.setEstado(ejecucion.getEstado());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(ejecucionDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<EjecucionInspeccion> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
