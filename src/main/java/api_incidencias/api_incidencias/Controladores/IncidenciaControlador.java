package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Entidades.DTO.IncidenciaDTO;
import api_incidencias.api_incidencias.Servicios.IncidenciaService;
import api_incidencias.api_incidencias.Servicios.UsuarioService;
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
    @Autowired
    private UsuarioService usuarioServicio;

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
    public ResponseEntity<Incidencia> crearIncidencia(@RequestBody IncidenciaDTO incidenciaDTO){

        Incidencia incidencia = cargarDTO(incidenciaDTO);

        Incidencia incidenciaGuardada = incidenciaServicio.addIncidencia(incidencia);
        return new ResponseEntity<>(incidenciaGuardada, HttpStatus.CREATED);
    }

    @PutMapping("/{idIncidencia}")
    public ResponseEntity<Incidencia> actualizarIncidencia(@PathVariable Long idIncidencia, @RequestBody IncidenciaDTO incidenciaDTO) {

        Incidencia incidencia = cargarDTO(incidenciaDTO);

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


    private Incidencia cargarDTO(IncidenciaDTO incidenciaDTO){
        Incidencia incidencia = new Incidencia();

        incidencia.setIdIncidencia(incidenciaDTO.getIdIncidencia());
        incidencia.setTitulo(incidenciaDTO.getTitulo());
        incidencia.setDescripcion(incidenciaDTO.getDescripcion());
        incidencia.setFechaCreacion(incidenciaDTO.getFechaCreacion());
        incidencia.setEstado(incidenciaDTO.getEstado());
        incidencia.setPrioridad(incidenciaDTO.getPrioridad());
        incidencia.setFechaInicioTrabajo(incidenciaDTO.getFechaInicioTrabajo());
        incidencia.setFechaFinTrabajo(incidenciaDTO.getFechaFinTrabajo());

        Optional<Usuario> optionalCliente = usuarioServicio.getUser(incidenciaDTO.getIdUsuarioCliente());
        Optional<Usuario> optionalTecnico = usuarioServicio.getUser(incidenciaDTO.getIdUsuarioTecnico());

        if (optionalCliente.isPresent()){
            incidencia.setUsuarioCliente(optionalCliente.get());
        }
        if (optionalTecnico.isPresent()){
            incidencia.setUsuarioTecnico(optionalTecnico.get());
        }

        return incidencia;
    }
}
