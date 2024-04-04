package api_incidencias.api_incidencias.Entidades.Clases;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ParteTrabajo")
public class ParteTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long idOrden;
    @Column(name = "trabajo_realizado")
    private String trabajoRealizado;
    @Column(name = "diagnostico")
    private String diagnostico;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "coste_reparacion")
    private double costeReparacion;
    @Column(name = "parte_trabajo_img")
    private String parteTrabajoImg;
    @ManyToOne
    @JoinColumn(name = "incidencia")
    private Incidencia incidencia;
    @OneToMany(mappedBy = "tiempo")
    @JsonIgnore
    private List<TiempoEmpleado> tiempoEmpleados;

    @OneToMany(mappedBy = "tiempo")
    @JsonIgnore
    private List<MaterialUtilizado> materialUtilizado;

    /************************************ Getters y Setters ********************************************/

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getTrabajoRealizado() {
        return trabajoRealizado;
    }

    public void setTrabajoRealizado(String trabajoRealizado) {
        this.trabajoRealizado = trabajoRealizado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getCosteReparacion() {
        return costeReparacion;
    }

    public void setCosteReparacion(double costeReparacion) {
        this.costeReparacion = costeReparacion;
    }

    public String getParteTrabajoImg() {
        return parteTrabajoImg;
    }

    public void setParteTrabajoImg(String parteTrabajoImg) {
        this.parteTrabajoImg = parteTrabajoImg;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public List<TiempoEmpleado> getTiempoEmpleados() {
        return tiempoEmpleados;
    }

    public void setTiempoEmpleados(List<TiempoEmpleado> tiempoEmpleados) {
        this.tiempoEmpleados = tiempoEmpleados;
    }

    public List<MaterialUtilizado> getMaterialUtilizado() {
        return materialUtilizado;
    }

    public void setMaterialUtilizado(List<MaterialUtilizado> materialUtilizado) {
        this.materialUtilizado = materialUtilizado;
    }
}
