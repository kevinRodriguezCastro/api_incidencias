package api_incidencias.api_incidencias.Entidades.DTO;

import api_incidencias.api_incidencias.Entidades.Enum.Estado;
import api_incidencias.api_incidencias.Entidades.Enum.Prioridad;

import java.time.LocalDateTime;

public class IncidenciaDTO {
    private Long idIncidencia;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaInicioTrabajo;
    private LocalDateTime fechaFinTrabajo;
    private Estado estado;
    private Prioridad prioridad;
    private Long idUsuarioCliente;
    private Long idUsuarioTecnico;

    /************************************ Getters y Setters ********************************************/

    public Long getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Long idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaInicioTrabajo() {
        return fechaInicioTrabajo;
    }

    public void setFechaInicioTrabajo(LocalDateTime fechaInicioTrabajo) {
        this.fechaInicioTrabajo = fechaInicioTrabajo;
    }

    public LocalDateTime getFechaFinTrabajo() {
        return fechaFinTrabajo;
    }

    public void setFechaFinTrabajo(LocalDateTime fechaFinTrabajo) {
        this.fechaFinTrabajo = fechaFinTrabajo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Long getIdUsuarioCliente() {
        return idUsuarioCliente;
    }

    public void setIdUsuarioCliente(Long idUsuarioCliente) {
        this.idUsuarioCliente = idUsuarioCliente;
    }

    public Long getIdUsuarioTecnico() {
        return idUsuarioTecnico;
    }

    public void setIdUsuarioTecnico(Long idUsuarioTecnico) {
        this.idUsuarioTecnico = idUsuarioTecnico;
    }
}
