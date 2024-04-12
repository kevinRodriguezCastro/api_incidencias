package api_incidencias.api_incidencias.Entidades.Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "ID_Usuario")
public class Cliente extends Usuario{
    @Column(name = "Calle")
    private String calle;
    @Column(name = "Ciudad")
    private String ciudad;
    @Column(name = "Provincia")
    private String provincia;
    @Column(name = "Codigo_Postal")
    private String codigoPostal;
    @Column(name = "Pais")
    private String pais;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Usuario") // Esta anotación es opcional si el nombre de la columna en la tabla es el mismo que el atributo
    private Usuario usuario;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
