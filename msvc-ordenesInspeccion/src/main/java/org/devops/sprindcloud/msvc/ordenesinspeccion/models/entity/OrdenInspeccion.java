package org.devops.sprindcloud.msvc.ordenesinspeccion.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_inspeccion")
public class OrdenInspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "solicitud_id", nullable = false)
    private Long solicitudId;

    @NotNull
    @Column(name = "vehiculo_id", nullable = false)
    private Long vehiculoId;

    @NotNull
    @Column(name = "inspector_usuario_id", nullable = false)
    private Long inspectorUsuarioId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOrden estado;

    @Column(name = "fecha_apertura")
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "motivo_suspension")
    private String motivoSuspension;

    @Column(name = "motivo_anulacion")
    private String motivoAnulacion;


    @PrePersist
    public void prePersist() {

        if (estado == null) {
            estado = EstadoOrden.PENDIENTE;
        }

        if (fechaApertura == null) {
            fechaApertura = LocalDateTime.now();
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Long solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Long getInspectorUsuarioId() {
        return inspectorUsuarioId;
    }

    public void setInspectorUsuarioId(Long inspectorUsuarioId) {
        this.inspectorUsuarioId = inspectorUsuarioId;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getMotivoSuspension() {
        return motivoSuspension;
    }

    public void setMotivoSuspension(String motivoSuspension) {
        this.motivoSuspension = motivoSuspension;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }
}