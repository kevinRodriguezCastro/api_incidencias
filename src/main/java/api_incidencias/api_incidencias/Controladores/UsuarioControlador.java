package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Servicios.Seguridad;
import api_incidencias.api_incidencias.Servicios.ServicioResetContraseña;
import api_incidencias.api_incidencias.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioService userServicio;
    @Autowired
    private Seguridad seguridad;
    @Autowired
    private ServicioResetContraseña servicioResetContraseña;

    /*
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        userServicio.addUser(usuario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";

    }*/



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

    @GetMapping("/obtener-imagen-user/{idUser}")
    public ResponseEntity<InputStreamResource> obtenerImagen(@PathVariable Long idUser) {
        return userServicio.getImagenUser(idUser);
    }

    @PostMapping("/{idUsuario}/imagen")
    public ResponseEntity<String> subirImagenPerfil(@PathVariable Long idUsuario, @RequestParam("file") MultipartFile file) {
        String urlImagen = userServicio.subirImagen(idUsuario, file);
        return new ResponseEntity<>(urlImagen, HttpStatus.CREATED);
    }

    /*@PostMapping
    public ResponseEntity<Usuario> crearUser(@RequestBody Usuario user){
        Usuario nuevoUsuario = userServicio.addUser(user);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
*/
    @PutMapping("/{idUser}")
    public ResponseEntity<Usuario> actualizarUser(@PathVariable Long idUser, @RequestBody Usuario user) {
        Usuario userActualizado = userServicio.updateUser(idUser, user);
        if (userActualizado != null) {
            return new ResponseEntity<>(userActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/codigo-contraseña")
    public String enviarCorreoResetContraseña() {

        servicioResetContraseña.enviarCorreoResetContraseña(seguridad.getCorreoPeticion());
        return "Se ha enviado un correo con el código de restablecimiento de contraseña.";
    }

    @PutMapping("/cambiar-contraseña/{codigo}")
    public ResponseEntity<?> resetContraseña(@PathVariable String codigo, @RequestBody String nuevaContraseña) {
        // Verificar si el código proporcionado es válido
        if (servicioResetContraseña.isValidCode(codigo)) {
            // Modificar la contraseña del usuario asociado al código
            if (servicioResetContraseña.modificarContraseña(codigo, nuevaContraseña)) {
                return ResponseEntity.ok("Contraseña modificada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar la contraseña");
            }
        } else {
            return ResponseEntity.badRequest().body("Código de restablecimiento de contraseña no válido");
        }

    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> eliminarUser(@PathVariable("idUser") Long idUser){
        return userServicio.deleteUser(idUser);
    }
}
