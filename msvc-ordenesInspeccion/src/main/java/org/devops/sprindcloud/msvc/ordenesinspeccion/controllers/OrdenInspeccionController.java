package org.devops.sprindcloud.msvc.ordenesinspeccion.controllers;

import jakarta.validation.Valid;

import org.devops.sprindcloud.msvc.ordenesinspeccion.models.MotivoRequest;
import org.devops.sprindcloud.msvc.ordenesinspeccion.models.entity.EstadoOrden;
import org.devops.sprindcloud.msvc.ordenesinspeccion.models.entity.OrdenInspeccion;
import org.devops.sprindcloud.msvc.ordenesinspeccion.services.OrdenInspeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/ordenes")
public class OrdenInspeccionController {

    @Autowired
    private OrdenInspeccionService service;


    @GetMapping
    public List<OrdenInspeccion> listar() {
        return service.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {

        Optional<OrdenInspeccion> ordenOpt = service.porId(id);

        if (ordenOpt.isPresent()) {
            return ResponseEntity.ok(ordenOpt.get());
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody OrdenInspeccion orden, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (service.tieneOrdenActiva(orden.getVehiculoId())) {

            return ResponseEntity.badRequest().body(Map.of("mensaje", "El vehículo ya tiene una orden activa"));
        }

        orden.setId(null);
        orden.setEstado(EstadoOrden.PENDIENTE);
        orden.setFechaCierre(null);
        orden.setMotivoSuspension(null);
        orden.setMotivoAnulacion(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(orden));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody OrdenInspeccion orden, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<OrdenInspeccion> ordenOpt = service.porId(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion ordenDB = ordenOpt.get();

            if (ordenDB.getEstado() != EstadoOrden.PENDIENTE) {

                return ResponseEntity.badRequest().body(Map.of("mensaje", "Solo se puede editar una orden pendiente"));
            }

            ordenDB.setSolicitudId(orden.getSolicitudId());

            ordenDB.setVehiculoId(orden.getVehiculoId());

            ordenDB.setInspectorUsuarioId(orden.getInspectorUsuarioId());

            return ResponseEntity.ok(service.guardar(ordenDB));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        Optional<OrdenInspeccion> ordenOpt = service.porId(id);

        if (ordenOpt.isPresent()) {

            OrdenInspeccion orden = ordenOpt.get();

            if (orden.getEstado() != EstadoOrden.PENDIENTE) {

                return ResponseEntity.badRequest().body(Map.of("mensaje", "Solo puede eliminarse una orden pendiente"));
            }

            service.eliminar(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<?> porVehiculo(@PathVariable Long vehiculoId) {

        return ResponseEntity.ok(service.listarPorVehiculo(vehiculoId));
    }


    @GetMapping("/solicitud/{solicitudId}")
    public ResponseEntity<?> porSolicitud(@PathVariable Long solicitudId) {

        return ResponseEntity.ok(service.listarPorSolicitud(solicitudId));
    }


    @GetMapping("/inspector/{inspectorId}")
    public ResponseEntity<?> porInspector(@PathVariable Long inspectorId) {

        return ResponseEntity.ok(service.listarPorInspector(inspectorId));
    }


    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> porEstado(@PathVariable EstadoOrden estado) {

        return ResponseEntity.ok(service.listarPorEstado(estado));
    }


    @PutMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciar(@PathVariable Long id) {

        Optional<OrdenInspeccion> ordenOpt = service.iniciar(id);

        if (ordenOpt.isPresent()) {
            return ResponseEntity.ok(ordenOpt.get());
        }

        return ResponseEntity.badRequest().body(Map.of("mensaje", "La orden no existe o no se encuentra pendiente"));
    }


    @PutMapping("/{id}/suspender")
    public ResponseEntity<?> suspender(@PathVariable Long id, @Valid @RequestBody MotivoRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<OrdenInspeccion> ordenOpt = service.suspender(id, request.getMotivo());

        if (ordenOpt.isPresent()) {
            return ResponseEntity.ok(ordenOpt.get());
        }

        return ResponseEntity.badRequest().body(Map.of("mensaje", "Solo puede suspenderse una orden en proceso"));
    }


    @PutMapping("/{id}/reanudar")
    public ResponseEntity<?> reanudar(@PathVariable Long id) {

        Optional<OrdenInspeccion> ordenOpt = service.reanudar(id);

        if (ordenOpt.isPresent()) {
            return ResponseEntity.ok(ordenOpt.get());
        }

        return ResponseEntity.badRequest().body(Map.of("mensaje", "Solo puede reanudarse una orden suspendida"));
    }


    @PutMapping("/{id}/completar")
    public ResponseEntity<?> completar(@PathVariable Long id) {

        Optional<OrdenInspeccion> ordenOpt = service.completar(id);

        if (ordenOpt.isPresent()) {
            return ResponseEntity.ok(ordenOpt.get());
        }

        return ResponseEntity.badRequest().body(Map.of("mensaje", "Solo puede completarse una orden en proceso"));
    }


    @PutMapping("/{id}/anular")
    public ResponseEntity<?> anular(@PathVariable Long id, @Valid @RequestBody MotivoRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<OrdenInspeccion> ordenOpt = service.anular(id, request.getMotivo());

        if (ordenOpt.isPresent()) {
            return ResponseEntity.ok(ordenOpt.get());
        }

        return ResponseEntity.badRequest().body(Map.of("mensaje", "La orden no existe o ya no puede anularse"));
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {

        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors().forEach(err -> {

            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());

        });

        return ResponseEntity.badRequest().body(errores);
    }

}