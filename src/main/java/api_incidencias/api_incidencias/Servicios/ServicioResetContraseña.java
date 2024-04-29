package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Jwt.JwtService;
import api_incidencias.api_incidencias.Repositorios.RepositorioUsuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServicioResetContraseña {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public void enviarCorreoResetContraseña(String correo) {
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByEmail(correo);
        Usuario usuario = null;
        if (usuarioOptional.isPresent()){
            usuario = usuarioOptional.get();
        }

        if (usuario == null) {
            System.out.println("No se encontró un usuario con este correo electrónico");
        }

        // Generar un token único y guardarlo en la base de datos con una fecha de expiración
        String token = generarToken(usuario.getCorreoElectronico(),usuario.getIdUsuario());
        LocalDateTime horaExpiracion = LocalDateTime.now().plusHours(24); // Expira en 24 horas


        // Enviar correo electrónico al usuario con el token
        String enlaceReset = "https://tudominio.com/reset-contraseña?token=" + token;
        enviarCorreo(correo, "Restablecer contraseña", "Haga clic en el siguiente enlace para restablecer su contraseña: " + enlaceReset);
    }



    public static String generarToken(String correo, Long idUsuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("correo", correo);
        claims.put("idUsuario", idUsuario);
        LocalDateTime fechaExpiracion = LocalDateTime.now().plusHours(24); // Expira en 24 horas

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(java.sql.Timestamp.valueOf(fechaExpiracion))
                .signWith(SECRET_KEY)
                .compact();
    }

    private void enviarCorreo(String para, String asunto, String cuerpo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);
        javaMailSender.send(mensaje);
    }
}

