package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models;

import java.time.LocalDateTime;

public class OrdenInspeccion {

    private Long id;

    private Long solicitudId;

    private Long vehiculoId;

    private Long inspectorUsuarioId;

    private String estado;

    private LocalDateTime fechaCierre;

    private String motivoSuspension;

    private String motivoAnulacion;


    public OrdenInspeccion() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }


    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
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


    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }


    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }


    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }
}