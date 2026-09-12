package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models;

public class OrdenInspeccion {

    private Long id;

    private Long vehiculoId;

    private Long solicitudId;

    private Long inspectorId;

    private String estado;

    private String resultado;


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


    public Long getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Long inspectorId) {
        this.inspectorId = inspectorId;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}