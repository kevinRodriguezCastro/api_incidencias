package api_incidencias.api_incidencias.Entidades.Clases;

import api_incidencias.api_incidencias.Entidades.Enum.Rol;
import jakarta.persistence.*;

@Entity
@Table(name = "trabajador")
@PrimaryKeyJoinColumn(name = "ID_Usuario")
public class Trabajador extends Usuario{
    @Column(name = "Rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
