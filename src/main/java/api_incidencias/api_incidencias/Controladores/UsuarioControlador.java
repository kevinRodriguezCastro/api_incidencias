package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Usuario;
import api_incidencias.api_incidencias.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/usuarios")

public class UsuarioControlador {
    @Autowired
    private UsuarioService userServicio;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return userServicio.obtenerTodosUsuarios();
    }

    @GetMapping("/{idUser}")
    public Optional<Usuario> getUserPorId(@PathVariable("idUser") Long idUser){
        return userServicio.obtenerUsuarioPorID(idUser);
    }

    @GetMapping("/{emailUser}")
    public Optional<Usuario> getUserPorEmail(@PathVariable("emailUser") String email){
        return userServicio.obtenerUsuarioPorEmail(email);
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUser(@RequestBody Usuario user){
        Usuario nuevoUsuario = userServicio.guardarUsuario(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<Usuario> actualizarUser(@PathVariable Long idUser, @RequestBody Usuario user) {
        Usuario userActualizado = userServicio.actualizarUsuario(idUser, user);
        if (userActualizado != null) {
            return new ResponseEntity<>(userActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> eliminarUser(@PathVariable("idUser") Long idUser){
        return userServicio.borrarUser(idUser);
    }
}
