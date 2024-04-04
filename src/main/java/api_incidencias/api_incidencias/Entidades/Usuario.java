package api_incidencias.api_incidencias.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "Correo_Electronico",unique = true)
    private String correoElectronico;
    @Column(name = "Contrasena")
    private String contrasena;
    @Column(name = "Rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "Ruta_Imagen")
    private String rutaImagen;

    @OneToMany(mappedBy = "usuarioCreador")
    @JsonIgnore
    private List<Incidencia> incidenciasCreadores;

    @OneToMany(mappedBy = "usuarioUltimaModificacion")
    @JsonIgnore
    private List<Incidencia> incidenciasModificados;

    @OneToMany(mappedBy = "idTecnico")
    @JsonIgnore
    private List<Incidencia> incendenciasTecnico;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
