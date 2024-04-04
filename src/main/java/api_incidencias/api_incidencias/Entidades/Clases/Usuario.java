package api_incidencias.api_incidencias.Entidades.Clases;

import api_incidencias.api_incidencias.Entidades.Enum.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

import java.time.LocalDate;


@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @Column(name = "DNI", unique = true)
    private String dni;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Correo_Electronico",unique = true)
    private String correoElectronico;
  
    @Column(name = "Contrasena")
    private String contrasena;

    @Column(name = "Fecha_Registro")
    private LocalDate fechaRegistro;

    @Column(name = "Ruta_Imagen")
    private String rutaImagen;
    @Column(name = "Rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(name = "Telefono")
    private String telefono;
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
    @OneToMany(mappedBy = "usuarioCliente")
    @JsonIgnore
    private List<Incidencia> incidenciasUsuario;
    @OneToMany(mappedBy = "usuarioTecnico")
    @JsonIgnore
    private List<Incidencia> incidenciasTecnico;


    /************************************ Getters y Setters ********************************************/

     public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

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

    public List<Incidencia> getIncidenciasUsuario() {
        return incidenciasUsuario;
    }

    public void setIncidenciasUsuario(List<Incidencia> incidenciasUsuario) {
        this.incidenciasUsuario = incidenciasUsuario;
    }

    public List<Incidencia> getIncidenciasTecnico() {
        return incidenciasTecnico;
    }

    public void setIncidenciasTecnico(List<Incidencia> incidenciasTecnico) {
        this.incidenciasTecnico = incidenciasTecnico;
    }
}
