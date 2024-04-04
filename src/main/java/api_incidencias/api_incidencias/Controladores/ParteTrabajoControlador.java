package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import api_incidencias.api_incidencias.Entidades.Clases.ParteTrabajo;
import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Entidades.DTO.IncidenciaDTO;
import api_incidencias.api_incidencias.Servicios.IncidenciaService;
import api_incidencias.api_incidencias.Servicios.ParteTrabajoService;
import api_incidencias.api_incidencias.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/parte-trabajo")
public class ParteTrabajoControlador {
    @Autowired
    private ParteTrabajoService parteTrabajoServicio;

    @GetMapping
    public List<ParteTrabajo> getPartesTrabajo(){
        return parteTrabajoServicio.getPartesTrabajo();
    }

    @GetMapping("/{idOrden}")
    public Optional<ParteTrabajo> getPartesTrabajoPorId(@PathVariable("idOrden") Long idOrden){
        return parteTrabajoServicio.getPartesTrabajoPorId(idOrden);
    }

    // Aqui Get de Partes de Trabajo por incidencia
    @GetMapping("incidencia/{idIncidencia}")
    public List<ParteTrabajo> getPartesTrabajoIncidencia(@PathVariable("idIncidencia") Long idIncidencia){
        return parteTrabajoServicio.getPartesTrabajoPorIncidencia(idIncidencia);
    }


    @PostMapping
    public ResponseEntity<ParteTrabajo> crearParteTrabajo(@RequestBody ParteTrabajo parteTb){

        //ParteTrabajo parteTrabajo = cargarDTO(parteTbDTO);

        ParteTrabajo incidenciaGuardada = parteTrabajoServicio.addParteTrabajo(parteTb);
        return new ResponseEntity<>(incidenciaGuardada, HttpStatus.CREATED);
    }

    @PutMapping("/{idOrden}")
    public ResponseEntity<ParteTrabajo> actualizarIncidencia(@PathVariable Long idOrden, @RequestBody ParteTrabajo parteTb) {

        //ParteTrabajo parteTrabajo = cargarDTO(parteTbDTO);

        ParteTrabajo parteTrabajoActualizado = parteTrabajoServicio.updateParteTrabajo(idOrden, parteTb);
        if (parteTrabajoActualizado != null) {
            return new ResponseEntity<>(parteTrabajoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{idOrden}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable("idOrden") Long idOrden){
        return parteTrabajoServicio.deleteParteTrabajo(idOrden);
    }

/*
    private Incidencia cargarDTO(IncidenciaDTO incidenciaDTO){
        Incidencia incidencia = new Incidencia();

        incidencia.setIdIncidencia(incidenciaDTO.getIdIncidencia());
        incidencia.setTitulo(incidenciaDTO.getTitulo());
        incidencia.setDescripcion(incidenciaDTO.getDescripcion());
        incidencia.setFechaCreacion(incidenciaDTO.getFechaCreacion());
        incidencia.setEstado(incidenciaDTO.getEstado());
        incidencia.setPrioridad(incidenciaDTO.getPrioridad());

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
 */

}
