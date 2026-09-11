package org.devops.sprindcloud.msvc.evaluacioninspeccion.entity;

public class PruebaInspeccion {

    private Long id;
    private String tipoPrueba;
    private Boolean obligatoria;
    private ResultadoPrueba resultado;

    public PruebaInspeccion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public ResultadoPrueba getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoPrueba resultado) {
        this.resultado = resultado;
    }
}
