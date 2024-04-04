package api_incidencias.api_incidencias.Entidades.Clases;

import api_incidencias.api_incidencias.Entidades.Enum.ModoResolucion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Tiempo_Empleado")
public class TiempoEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tiempo_Empleado")
    private Long idTiempoEmpleado;
    @Column(name = "Fecha")
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "ID_Parte_Trabajo")
    private ParteTrabajo parteTrabajo;
    @Column(name = "Hora_Entrada")
    private LocalTime horaEntrada;
    @Column(name = "Hora_Salida")
    private LocalTime horaSalida;
    @Enumerated(EnumType.STRING)
    @Column(name = "Modo_Resolucion")
    private ModoResolucion modoResolucion;


    /************************************ Getters y Setters ********************************************/

    public Long getIdTiempoEmpleado() {
        return idTiempoEmpleado;
    }

    public void setIdTiempoEmpleado(Long idTiempoEmpleado) {
        this.idTiempoEmpleado = idTiempoEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public ParteTrabajo getParteTrabajo() {
        return parteTrabajo;
    }

    public void setParteTrabajo(ParteTrabajo parteTrabajo) {
        this.parteTrabajo = parteTrabajo;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public ModoResolucion getModoResolucion() {
        return modoResolucion;
    }

    public void setModoResolucion(ModoResolucion modoResolucion) {
        this.modoResolucion = modoResolucion;
    }
}
