package api_incidencias.api_incidencias.Entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "IncidenciaReabierta")
public class IncidenciaReabierta {

    @Id
    @Column(name = "ID_Incidencia_Reabierta")
    private String idIncidenciaReabierta;

    @ManyToOne
    @JoinColumn(name = "ID_Incidencia_Principal")
    private Incidencia incidenciaPrincipal;

    @Column(name = "Fecha_Reapertura")
    private LocalDateTime fechaReapertura;

    @Column(name = "Descripcion_Reapertura")
    private String descripcionReapertura;

    public String getIdIncidenciaReabierta() {
        return idIncidenciaReabierta;
    }

    public void setIdIncidenciaReabierta(String idIncidenciaReabierta) {
        this.idIncidenciaReabierta = idIncidenciaReabierta;
    }

    public Incidencia getIncidenciaPrincipal() {
        return incidenciaPrincipal;
    }

    public void setIncidenciaPrincipal(Incidencia incidenciaPrincipal) {
        this.incidenciaPrincipal = incidenciaPrincipal;
    }

    public LocalDateTime getFechaReapertura() {
        return fechaReapertura;
    }

    public void setFechaReapertura(LocalDateTime fechaReapertura) {
        this.fechaReapertura = fechaReapertura;
    }

    public String getDescripcionReapertura() {
        return descripcionReapertura;
    }

    public void setDescripcionReapertura(String descripcionReapertura) {
        this.descripcionReapertura = descripcionReapertura;
    }
}
