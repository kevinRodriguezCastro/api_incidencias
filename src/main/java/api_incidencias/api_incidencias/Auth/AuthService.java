package api_incidencias.api_incidencias.Auth;

import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Entidades.Enum.Rol;
import api_incidencias.api_incidencias.Jwt.JwtService;
import api_incidencias.api_incidencias.Repositorios.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    // Inyectamos el repositorio del usuario
    @Autowired
    private RepositorioUsuario reposUser;

    // Inyectamos el servicio del Jwt encargado de los tokens
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwdEncoder;

    public AuthResponse login(LoginRequest request){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailUser(), request.getPassword()));
        UserDetails userLogueado = reposUser.findByEmail(request.getEmailUser()).orElseThrow();
        String tokenUser = jwtService.getToken(userLogueado);

        return AuthResponse.builder().token(tokenUser).build();
    }
    public AuthResponse registrarUser(RegisterRequest request){
        Usuario newUser = Usuario.builder()
                .dni(request.getDni())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correoElectronico(request.getCorreoElectronico())
                .contrasena(request.getContrasena())
                .telefono(request.getTelefono())
                .calle(request.getCalle())
                .ciudad(request.getCiudad())
                .provincia(request.getProvincia())
                .codigoPostal(request.getCodigoPostal())
                .pais(request.getPais())
                .rol(Rol.cliente)
                .build();

        // Guardamos el usuario usando el repositorio del usuario
        reposUser.save(newUser);

        // Retornamos el objeto usuario creado junto con el token que obtenemos mediante el servicio JWT
        return AuthResponse.builder()
                .token(jwtService.getToken(newUser))
                .build();
    }

}
