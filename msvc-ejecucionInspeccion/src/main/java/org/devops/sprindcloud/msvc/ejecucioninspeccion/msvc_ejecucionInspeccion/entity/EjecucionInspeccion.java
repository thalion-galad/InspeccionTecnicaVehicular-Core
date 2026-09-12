package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ejecuciones_inspeccion")
public class EjecucionInspeccion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Referencia al microservicio de ordenes-inspeccion
    @Column(name = "orden_inspeccion_id")
    private Long ordenInspeccionId;


    @Enumerated(EnumType.STRING)
    private EstadoPrueba estado;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "ejecucion_inspeccion_id")
    private List<PruebaInspeccion> pruebas;


    public EjecucionInspeccion() {
        pruebas = new ArrayList<>();
    }


    public void agregarPrueba(PruebaInspeccion prueba){
        pruebas.add(prueba);
    }


    public void eliminarPrueba(PruebaInspeccion prueba){
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


    public EstadoPrueba getEstado() {
        return estado;
    }


    public void setEstado(EstadoPrueba estado) {
        this.estado = estado;
    }


    public List<PruebaInspeccion> getPruebas() {
        return pruebas;
    }


    public void setPruebas(List<PruebaInspeccion> pruebas) {
        this.pruebas = pruebas;
    }
}