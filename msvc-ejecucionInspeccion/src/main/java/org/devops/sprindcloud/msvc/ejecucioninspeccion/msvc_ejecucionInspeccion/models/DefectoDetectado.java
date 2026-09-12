package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models;

public class DefectoDetectado {

    private Long id;

    private Long pruebaInspeccionId;

    private String descripcion;

    private GravedadDefecto gravedad;


    public DefectoDetectado() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getPruebaInspeccionId() {
        return pruebaInspeccionId;
    }

    public void setPruebaInspeccionId(Long pruebaInspeccionId) {
        this.pruebaInspeccionId = pruebaInspeccionId;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public GravedadDefecto getGravedad() {
        return gravedad;
    }

    public void setGravedad(GravedadDefecto gravedad) {
        this.gravedad = gravedad;
    }
}