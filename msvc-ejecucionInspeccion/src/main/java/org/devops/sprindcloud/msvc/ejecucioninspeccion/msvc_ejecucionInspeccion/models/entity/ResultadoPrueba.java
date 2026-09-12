package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ResultadoPrueba {

    private Boolean conforme;

    private Double valor;

    private String unidad;

    private String observacion;

    public ResultadoPrueba() {
    }

    public ResultadoPrueba(
            Boolean conforme,
            Double valor,
            String unidad,
            String observacion) {

        this.conforme = conforme;
        this.valor = valor;
        this.unidad = unidad;
        this.observacion = observacion;
    }

    public Boolean getConforme() {
        return conforme;
    }

    public void setConforme(Boolean conforme) {
        this.conforme = conforme;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}