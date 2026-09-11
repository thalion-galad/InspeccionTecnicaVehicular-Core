package org.devops.sprindcloud.msvc.evaluacioninspeccion.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "evaluacionesInspeccion")
public class EvaluacionInspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ordenInspeccionId", nullable = false)
    private Long ordenInspeccionId;

    @Column(name = "ejecucionInspeccionId", nullable = false)
    private Long ejecucionInspeccionId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "evaluacionInspeccionId")
    private List<DefectoDetectado> defectosDetectados;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado")
    private ResultadoInspeccion resultado;

    @Column(name = "fechaEvaluacion")
    private LocalDateTime fechaEvaluacion;

    public EvaluacionInspeccion() {
        defectosDetectados = new ArrayList<>();
    }

    public void addDefectoDetectado(DefectoDetectado defectoDetectado) {
        defectosDetectados.add(defectoDetectado);
    }

    public void removeDefectoDetectado(DefectoDetectado defectoDetectado) {
        defectosDetectados.remove(defectoDetectado);
    }

    public boolean tieneDefectoGrave() {
        return defectosDetectados.stream()
                .anyMatch(defecto ->
                        defecto.getGravedad() == GravedadDefecto.GRAVE);
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