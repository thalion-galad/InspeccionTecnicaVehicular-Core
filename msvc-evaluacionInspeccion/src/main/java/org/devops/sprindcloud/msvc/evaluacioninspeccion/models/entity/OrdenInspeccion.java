package org.devops.sprindcloud.msvc.evaluacioninspeccion.models.entity;

public class OrdenInspeccion {

    private Long id;
    private String estado;

    public OrdenInspeccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
