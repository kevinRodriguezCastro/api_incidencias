package api_incidencias.api_incidencias.Auth;

import api_incidencias.api_incidencias.Entidades.Clases.Cliente;
import api_incidencias.api_incidencias.Entidades.Clases.Trabajador;
import api_incidencias.api_incidencias.Jwt.JwtService;
import api_incidencias.api_incidencias.Repositorios.RepositorioUsuario;
import api_incidencias.api_incidencias.Servicios.ClienteService;
import api_incidencias.api_incidencias.Servicios.Seguridad;
import api_incidencias.api_incidencias.Servicios.TrabajadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    // Inyectamos el repositorio del usuario
    @Autowired
    private RepositorioUsuario reposUser;

    @Autowired
    private ClienteService clienteService;

    // Inyectamos el servicio del Jwt encargado de los tokens
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private Seguridad seguridad;
    @Autowired
    private TrabajadorService trabajadorService;
    @Autowired
    private PasswordEncoder passwdEncoder;


    public AuthResponse login(LoginRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreoElectronico(), request.getContrasena()));
        UserDetails userLogueado = reposUser.findByEmail(request.getCorreoElectronico()).orElseThrow();
        String tokenUser = jwtService.getToken(userLogueado);

        return AuthResponse.builder().token(tokenUser).build();
    }
    public AuthResponse registrarCliente(RegisterRequest_Cliente request){
        /*
        Usuario newUser = Usuario.builder()
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correoElectronico(request.getCorreoElectronico())
                .contrasena(passwdEncoder.encode(request.getContrasena()))
                .telefono(request.getTelefono())
                .calle(request.getCalle())
                .ciudad(request.getCiudad())
                .provincia(request.getProvincia())
                .codigoPostal(request.getCodigoPostal())
                .pais(request.getPais())
                .rol(Rol.cliente)
                .build();
        */

        /*
        Cliente newCliente = Cliente.builder()
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correoElectronico(request.getCorreoElectronico())
                .contrasena(passwdEncoder.encode(request.getContrasena()))
                .telefono(request.getTelefono())
                .calle(request.getCalle())
                .ciudad(request.getCiudad())
                .provincia(request.getProvincia())
                .codigoPostal(request.getCodigoPostal())
                .pais(request.getPais())
                .build();
         */
        System.out.println("Registramos cleinte");

        Cliente newCliente = new Cliente();
        newCliente.setDni(request.getDni());
        newCliente.setNombre(request.getNombre());
        newCliente.setApellido(request.getApellido());
        newCliente.setCorreoElectronico(request.getCorreoElectronico());
        newCliente.setContrasena(passwdEncoder.encode(request.getContrasena()));
        newCliente.setTelefono(request.getTelefono());
        newCliente.setCalle(request.getCalle());
        newCliente.setCiudad(request.getCiudad());
        newCliente.setProvincia(request.getProvincia());
        newCliente.setCodigoPostal(request.getCodigoPostal());
        newCliente.setPais(request.getPais());
        newCliente.setFechaRegistro(LocalDate.now());

        // Guardamos el usuario usando el repositorio del usuario
        clienteService.addCliente(newCliente);

        System.out.println("cliente guardado");

        // Retornamos el objeto usuario creado junto con el token que obtenemos mediante el servicio JWT
        return AuthResponse.builder()
                .token(jwtService.getToken(newCliente))
                .build();
    }

    /**
     * Solo admin y tecnicos jefe
     * @param request
     * @return
     */
    public AuthResponse registrarTrabajador(RegisterRequest_Trabajador request){

        if (seguridad.isTecnicoJefe()  || seguridad.isAdmin()){

            System.out.println("Estoy aqui Admin o TecnicoJefe");

            Trabajador newTrabajador = new Trabajador();

            newTrabajador.setDni(request.getDni());
            newTrabajador.setNombre(request.getNombre());
            newTrabajador.setApellido(request.getApellido());
            newTrabajador.setCorreoElectronico(request.getCorreoElectronico());
            newTrabajador.setContrasena(passwdEncoder.encode(request.getContrasena()));
            newTrabajador.setTelefono(request.getTelefono());
            newTrabajador.setRol(request.getRol());
            newTrabajador.setFechaRegistro(LocalDate.now());

            // Guardamos el usuario usando el repositorio del usuario
            trabajadorService.addTrabajador(newTrabajador);

            // Retornamos el objeto usuario creado junto con el token que obtenemos mediante el servicio JWT
            return AuthResponse.builder()
                    .token(jwtService.getToken(newTrabajador))
                    .build();
        }
        System.out.println("No soy ni admin ni TecnicoJefe");
        return null;
    }

}
