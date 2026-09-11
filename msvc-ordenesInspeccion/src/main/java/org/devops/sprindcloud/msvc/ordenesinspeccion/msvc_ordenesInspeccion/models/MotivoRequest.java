package org.devops.sprindcloud.msvc.ordenesinspeccion.msvc_ordenesInspeccion.models;

import jakarta.validation.constraints.NotBlank;

public class MotivoRequest {

    @NotBlank
    private String motivo;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}