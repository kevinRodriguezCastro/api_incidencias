package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioService userServicio;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return userServicio.getUser();
    }

    @GetMapping("/{idUser}")
    public Optional<Usuario> getUserPorId(@PathVariable("idUser") Long idUser){
        return userServicio.getUser(idUser);
    }

    @GetMapping("/email/{emailUser}")
    public Optional<Usuario> getUserPorEmail(@PathVariable("emailUser") String email){
        return userServicio.getUser(email);
    }

    @PostMapping("/{idUsuario}/imagen")
    public ResponseEntity<String> subirImagenPerfil(@PathVariable Long idUsuario, @RequestParam("file") MultipartFile file) {
        String urlImagen = userServicio.subirImagen(idUsuario, file);
        return new ResponseEntity<>(urlImagen, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUser(@RequestBody Usuario user){
        Usuario nuevoUsuario = userServicio.addUser(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<Usuario> actualizarUser(@PathVariable Long idUser, @RequestBody Usuario user) {
        Usuario userActualizado = userServicio.updateUser(idUser, user);
        if (userActualizado != null) {
            return new ResponseEntity<>(userActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> eliminarUser(@PathVariable("idUser") Long idUser){
        return userServicio.deleteUser(idUser);
    }
}
