package api_incidencias.api_incidencias.Auth;

import api_incidencias.api_incidencias.Servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthControlador {
    @Autowired
    private AuthService authServicio;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authServicio.login(request));
    }

    @PostMapping("/registrar-user")
    public ResponseEntity<AuthResponse> registro(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authServicio.registrarUser(request));
    }

}
