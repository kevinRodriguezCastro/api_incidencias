package api_incidencias.api_incidencias.Entidades.Clases;

import api_incidencias.api_incidencias.Entidades.Enum.Estado;
import api_incidencias.api_incidencias.Entidades.Enum.Prioridad;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Incidencia")
    private Long idIncidencia;

    @Column(name = "Titulo")
    private String titulo;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Fecha_Creacion")
    //@JsonIgnore
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    @Column(name = "Estado")
    private Estado estado;
    @Enumerated(EnumType.STRING)
    @Column(name = "Prioridad")
    private Prioridad prioridad;
    @ManyToOne
    @JoinColumn(name = "Id_Cliente")
    private Cliente usuarioCliente;
    @OneToMany(mappedBy = "incidencia")
    @JsonIgnore
    private List<ParteTrabajo> listaPartesTrabajo;

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

    public Cliente getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Cliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public List<ParteTrabajo> getListaPartesTrabajo() {
        return listaPartesTrabajo;
    }

    public void setListaPartesTrabajo(List<ParteTrabajo> listaPartesTrabajo) {
        this.listaPartesTrabajo = listaPartesTrabajo;
    }
}
