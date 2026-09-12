package org.devops.sprindcloud.msvc.ejecucioninspeccion.models;

public class OrdenInspeccion {

    private Long id;
    private Long vehiculoId;
    private Long solicitudId;
    private Long inspectorUsuarioId;
    private String estado;

    public OrdenInspeccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Long getInspectorUsuarioId() {
        return inspectorUsuarioId;
    }

    public void setInspectorUsuarioId(Long inspectorUsuarioId) {
        this.inspectorUsuarioId = inspectorUsuarioId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}