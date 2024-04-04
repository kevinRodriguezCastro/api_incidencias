package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Incidencia;
import api_incidencias.api_incidencias.Servicios.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "api/v1/incidencias")
public class IncidenciaControlador {
    @Autowired
    private IncidenciaService incidenciaServicio;

    @GetMapping
    public List<Incidencia> getIncidencias(){
        return incidenciaServicio.getIncidencias();
    }

    @GetMapping("/{idIncidencia}")
    public Optional<Incidencia> getIncidenciaPorId(@PathVariable("idIncidencia") Long idIncidencia){
        return incidenciaServicio.getIncidencias(idIncidencia);
    }
    @GetMapping("/cliente/{idCliente}")
    public List<Incidencia> getIncidenciaCliente(@PathVariable("idCliente") Long idCliente){
        return incidenciaServicio.getIncidenciasCliente(idCliente);
    }
    @GetMapping("/tecnico/{idTecnico}")
    public List<Incidencia> getIncidenciaTecnico(@PathVariable("idTecnico") Long idTecnico){
        return incidenciaServicio.getIncidenciasTecnico(idTecnico);
    }
    @PostMapping
    public ResponseEntity<Incidencia> crearIncidencia(@RequestBody Incidencia incidencia){
        Incidencia nuevaIncidencia = incidenciaServicio.addIncidencia(incidencia);
        return new ResponseEntity<>(nuevaIncidencia, HttpStatus.CREATED);
    }

    @PutMapping("/{idIncidencia}")
    public ResponseEntity<Incidencia> actualizarIncidencia(@PathVariable Long idIncidencia, @RequestBody Incidencia incidencia) {
        Incidencia incidenciaActualizada = incidenciaServicio.updateIncidencia(idIncidencia, incidencia);
        if (incidenciaActualizada != null) {
            return new ResponseEntity<>(incidenciaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{idIncidencia}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable("idIncidencia") Long idIncidencia){
        return incidenciaServicio.deleteIncidencia(idIncidencia);
    }
}
