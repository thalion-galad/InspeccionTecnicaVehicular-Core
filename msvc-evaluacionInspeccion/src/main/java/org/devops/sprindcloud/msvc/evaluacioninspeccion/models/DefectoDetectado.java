package org.devops.sprindcloud.msvc.evaluacioninspeccion.models;

import jakarta.persistence.*;

@Entity
@Table(name = "defectosDetectados")
public class DefectoDetectado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pruebaInspeccionId", nullable = false)
    private Long pruebaInspeccionId;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "gravedad", nullable = false)
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