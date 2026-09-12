package org.devops.sprindcloud.msvc.ejecucioninspeccion.msvc_ejecucionInspeccion.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pruebas_inspeccion")
public class PruebaInspeccion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private TipoPrueba tipoPrueba;


    @Enumerated(EnumType.STRING)
    private EstadoPrueba estado;


    @Embedded
    private ResultadoPrueba resultado;


    public PruebaInspeccion() {
    }


    public PruebaInspeccion(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
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