package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.controllers;

import feign.FeignException;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.entity.EjecucionInspeccion;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.entity.ResultadoPrueba;
import org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.services.EjecucionInspeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ejecucion-inspeccion")
public class EjecucionInspeccionController {

    @Autowired
    private EjecucionInspeccionService service;


    @GetMapping
    public ResponseEntity<List<EjecucionInspeccion>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(
            @PathVariable Long id) {

        Optional<EjecucionInspeccion> op =
                service.porId(id);

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/orden/{ordenInspeccionId}")
    public ResponseEntity<?> detallePorOrden(
            @PathVariable Long ordenInspeccionId) {

        Optional<EjecucionInspeccion> op =
                service.porOrdenInspeccionId(
                        ordenInspeccionId
                );

        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<?> crear(
            @RequestBody EjecucionInspeccion ejecucion) {

        try {

            EjecucionInspeccion ejecucionDB =
                    service.crearEjecucion(ejecucion);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ejecucionDB);

        } catch (FeignException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Collections.singletonMap(
                                    "Mensaje",
                                    "No existe la orden de inspección o hubo un error en la comunicación: "
                                            + e.getMessage()
                            )
                    );

        } catch (IllegalStateException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Collections.singletonMap(
                                    "Mensaje",
                                    e.getMessage()
                            )
                    );
        }
    }


    @PutMapping("/{ejecucionId}/pruebas/{pruebaId}/iniciar")
    public ResponseEntity<?> iniciarPrueba(
            @PathVariable Long ejecucionId,
            @PathVariable Long pruebaId) {

        try {

            Optional<EjecucionInspeccion> op =
                    service.iniciarPrueba(
                            ejecucionId,
                            pruebaId
                    );

            if (op.isPresent()) {
                return ResponseEntity.ok(op.get());
            }

            return ResponseEntity.notFound().build();

        } catch (IllegalStateException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Collections.singletonMap(
                                    "Mensaje",
                                    e.getMessage()
                            )
                    );
        }
    }


    @PutMapping("/{ejecucionId}/pruebas/{pruebaId}/resultado")
    public ResponseEntity<?> registrarResultado(
            @PathVariable Long ejecucionId,
            @PathVariable Long pruebaId,
            @RequestBody ResultadoPrueba resultado) {

        try {

            Optional<EjecucionInspeccion> op =
                    service.registrarResultado(
                            ejecucionId,
                            pruebaId,
                            resultado
                    );

            if (op.isPresent()) {
                return ResponseEntity.ok(op.get());
            }

            return ResponseEntity.notFound().build();

        } catch (IllegalStateException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Collections.singletonMap(
                                    "Mensaje",
                                    e.getMessage()
                            )
                    );
        }
    }


    @PutMapping("/{ejecucionId}/finalizar")
    public ResponseEntity<?> finalizarEjecucion(
            @PathVariable Long ejecucionId) {

        try {

            Optional<EjecucionInspeccion> op =
                    service.finalizarEjecucion(
                            ejecucionId
                    );

            if (op.isPresent()) {
                return ResponseEntity.ok(op.get());
            }

            return ResponseEntity.notFound().build();

        } catch (IllegalStateException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                            Collections.singletonMap(
                                    "Mensaje",
                                    e.getMessage()
                            )
                    );
        }
    }
}