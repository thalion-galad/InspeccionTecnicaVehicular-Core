package org.devops.sprindcloud.msvc.ejecucioninspeccion.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionInspeccion {

    private Long id;

    private Long ordenInspeccionId;

    private Long ejecucionInspeccionId;

    private List<DefectoDetectado> defectosDetectados;

    private ResultadoInspeccion resultado;

    private LocalDateTime fechaEvaluacion;


    public EvaluacionInspeccion() {
        defectosDetectados = new ArrayList<>();
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


    public Long getEjecucionInspeccionId() {
        return ejecucionInspeccionId;
    }

    public void setEjecucionInspeccionId(Long ejecucionInspeccionId) {
        this.ejecucionInspeccionId = ejecucionInspeccionId;
    }


    public List<DefectoDetectado> getDefectosDetectados() {
        return defectosDetectados;
    }

    public void setDefectosDetectados(List<DefectoDetectado> defectosDetectados) {
        this.defectosDetectados = defectosDetectados;
    }


    public ResultadoInspeccion getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoInspeccion resultado) {
        this.resultado = resultado;
    }


    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
}