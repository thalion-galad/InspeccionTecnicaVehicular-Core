package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity;

import jakarta.persistence.Embeddable;


@Embeddable
public class ResultadoPrueba {


    private Boolean conforme;


    private Double valorMedido;


    private String unidadMedida;


    private String observacion;


    public ResultadoPrueba() {
    }


    public ResultadoPrueba(Boolean conforme,
                           Double valorMedido,
                           String unidadMedida,
                           String observacion) {

        this.conforme = conforme;
        this.valorMedido = valorMedido;
        this.unidadMedida = unidadMedida;
        this.observacion = observacion;
    }


    public Boolean getConforme() {
        return conforme;
    }


    public void setConforme(Boolean conforme) {
        this.conforme = conforme;
    }


    public Double getValorMedido() {
        return valorMedido;
    }


    public void setValorMedido(Double valorMedido) {
        this.valorMedido = valorMedido;
    }


    public String getUnidadMedida() {
        return unidadMedida;
    }


    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }


    public String getObservacion() {
        return observacion;
    }


    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}