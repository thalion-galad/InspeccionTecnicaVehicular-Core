package org.devops.sprindcloud.msvc.ejecucioninspeccion.models.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ejecuciones_inspeccion")
public class EjecucionInspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referencia al microservicio msvc-ordenes-inspeccion
    @Column(name = "orden_inspeccion_id", nullable = false)
    private Long ordenInspeccionId;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ejecucion_inspeccion_id")
    private List<PruebaInspeccion> pruebas;

    public EjecucionInspeccion() {
        pruebas = new ArrayList<>();
    }

    public void agregarPrueba(PruebaInspeccion prueba) {
        pruebas.add(prueba);
    }

    public void eliminarPrueba(PruebaInspeccion prueba) {
        pruebas.remove(prueba);
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