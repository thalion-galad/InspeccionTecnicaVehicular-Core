package org.devops.sprindcloud.msvc.evaluacioninspeccion.models.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EjecucionInspeccion {

    private Long id;
    private Long ordenInspeccionId;

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private List<PruebaInspeccion> pruebas;

    public EjecucionInspeccion() {
        pruebas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdenInspeccionId() {
        return ordenInspeccionId;
    }

    public void setOrdenInspeccionId(Long ordenInspeccionId) {
        this.ordenInspeccionId = ordenInspeccionId;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<PruebaInspeccion> getPruebas() {
        return pruebas;
    }

    public void setPruebas(List<PruebaInspeccion> pruebas) {
        this.pruebas = pruebas;
    }
}
