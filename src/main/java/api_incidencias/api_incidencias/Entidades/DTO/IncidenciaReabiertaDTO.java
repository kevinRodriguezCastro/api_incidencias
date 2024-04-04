package api_incidencias.api_incidencias.Entidades.DTO;

import java.time.LocalDateTime;

public class IncidenciaReabiertaDTO {

    private String idIncidenciaReabierta;
    private Long idIncidenciaPrincipal;
    private LocalDateTime fechaReapertura;
    private String descripcionReapertura;

    /************************************ Getters y Setters ********************************************/

    public String getIdIncidenciaReabierta() {
        return idIncidenciaReabierta;
    }

    public void setIdIncidenciaReabierta(String idIncidenciaReabierta) {
        this.idIncidenciaReabierta = idIncidenciaReabierta;
    }

    public Long getIdIncidenciaPrincipal() {
        return idIncidenciaPrincipal;
    }

    public void setIdIncidenciaPrincipal(Long idIncidenciaPrincipal) {
        this.idIncidenciaPrincipal = idIncidenciaPrincipal;
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
