package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pruebas_inspeccion")
public class PruebaInspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_prueba", nullable = false)
    private TipoPrueba tipoPrueba;

    @Column(name = "obligatoria", nullable = false)
    private Boolean obligatoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoPrueba estado;

    @Embedded
    private ResultadoPrueba resultado;

    public PruebaInspeccion() {
    }

    public PruebaInspeccion(TipoPrueba tipoPrueba, Boolean obligatoria) {
        this.tipoPrueba = tipoPrueba;
        this.obligatoria = obligatoria;
        this.estado = EstadoPrueba.PENDIENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public EstadoPrueba getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrueba estado) {
        this.estado = estado;
    }

    public ResultadoPrueba getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoPrueba resultado) {
        this.resultado = resultado;
    }
}