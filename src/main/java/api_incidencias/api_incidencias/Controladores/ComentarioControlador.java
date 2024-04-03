package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Comentario;
import api_incidencias.api_incidencias.Servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/comentarios")
public class ComentarioControlador {
    @Autowired
    private ComentarioService comentServicio;

    @GetMapping
    public List<Comentario> obtenerComentarios(){
        return comentServicio.getComentario();
    }

    @GetMapping("/{idComentario}")
    public Optional<Comentario> getComentarioPorId(@PathVariable("idComentario") Long idComentario){
        return comentServicio.getComentarioPorId(idComentario);
    }

    @GetMapping("/{idIncidencia}")
    public List<Comentario> getComentariosPorIncidencia(@PathVariable("idIncidencia") Long idIncidencia){
        return comentServicio.getComentarioPorIncidencia(idIncidencia);
    }

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario){
        Comentario nuevoComent = comentServicio.addComentario(comentario);
        return new ResponseEntity<>(nuevoComent, HttpStatus.CREATED);
    }

    @PutMapping("/{idComentario}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long idComentario, @RequestBody Comentario comentario) {
        Comentario comentActualizado = comentServicio.updateComentario(idComentario, comentario);
        if (comentActualizado != null) {
            return new ResponseEntity<>(comentActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable("idComentario") Long idComentario){
        return comentServicio.deleteComentario(idComentario);
    }
}
