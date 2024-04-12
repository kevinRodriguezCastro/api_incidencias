package api_incidencias.api_incidencias.Auth;

import api_incidencias.api_incidencias.Entidades.Enum.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest_Trabajador {
    private String dni;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contrasena;
    private String telefono;
    //private LocalDate fechaRegistro;
    //private String imagenPerfil;
    private Rol rol;
}
