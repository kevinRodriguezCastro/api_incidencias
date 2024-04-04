package api_incidencias.api_incidencias.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Incidencia")
    private Long idIncidencia;

    @Column(name = "Titulo")
    private String titulo;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Fecha_Creacion")
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @Column(name = "Fecha_Inicio_Trabajo")
    @JsonIgnore
    private LocalDateTime fechaInicioTrabajo;

    @Column(name = "Fecha_Fin_Trabajo")
    @JsonIgnore
    private LocalDateTime fechaFinTrabajo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estado")
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "Prioridad")
    private Prioridad prioridad;

    @ManyToOne
    @JoinColumn(name = "Id_Cliente")
    private Usuario usuarioCliente;

    @ManyToOne
    @JoinColumn(name = "Id_Tecnico")
    private Usuario usuarioTecnico;

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

    public Usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public Usuario getUsuarioTecnico() {
        return usuarioTecnico;
    }

    public void setUsuarioTecnico(Usuario usuarioTecnico) {
        this.usuarioTecnico = usuarioTecnico;
    }
}
