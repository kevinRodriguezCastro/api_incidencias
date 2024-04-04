package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import api_incidencias.api_incidencias.Entidades.Clases.IncidenciaReabierta;
import api_incidencias.api_incidencias.Entidades.DTO.IncidenciaReabiertaDTO;
import api_incidencias.api_incidencias.Servicios.IncidenciaReabiertaService;
import api_incidencias.api_incidencias.Servicios.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/incidencias-reabiertas")
public class IncidReabiertaControlador {
    @Autowired
    private IncidenciaReabiertaService incidReabiertaService;
    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public List<IncidenciaReabierta> getIncidenciasReabiertas(){
        return incidReabiertaService.getIncidenciasReabiertas();
    }

    @GetMapping("/{idIncidReabierta}")
    public Optional<IncidenciaReabierta> getIncidReabiertasPorId(@PathVariable("idIncidReabierta") String idIncidReabierta){
        return incidReabiertaService.getIncidenciasReabiertas(idIncidReabierta);
    }
    @GetMapping("incidencia-principal/{idIncidencia}")
    public List<IncidenciaReabierta> getIncidReabiertasPorIncidenciaPrincipal(@PathVariable("idIncidencia") Long idIncidencia){
        return incidReabiertaService.getIncidenciasReabiertas(idIncidencia);
    }
    @PostMapping
    public ResponseEntity<IncidenciaReabierta> crearIncidenciaReabierta(@RequestBody IncidenciaReabiertaDTO incidenciaReabiertaDTO){
        IncidenciaReabierta incidenciaReabierta = cargarDTO(incidenciaReabiertaDTO);

        IncidenciaReabierta nuevaIncidReabierta = incidReabiertaService.addIncidenciaReabierta(incidenciaReabierta);
        return new ResponseEntity<>(nuevaIncidReabierta, HttpStatus.CREATED);
    }

    @PutMapping("/{idIncidReabierta}")
    public ResponseEntity<IncidenciaReabierta> actualizarIncidenciaReabierta(@PathVariable String idIncidReabierta, @RequestBody IncidenciaReabiertaDTO incidReabiertaDTO) {
        IncidenciaReabierta incidenciaReabierta = cargarDTO(incidReabiertaDTO);

        IncidenciaReabierta incidReabiertaActualizada = incidReabiertaService.updateIncidenciaReabierta(idIncidReabierta, incidenciaReabierta);
        if (incidReabiertaActualizada != null) {
            return new ResponseEntity<>(incidReabiertaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idIncidReabierta}")
    public ResponseEntity<String> eliminarIncidenciaReabierta(@PathVariable("idIncidReabierta") String idIncidReabierta){
        return incidReabiertaService.deleteIncidenciaReabierta(idIncidReabierta);
    }

    private IncidenciaReabierta cargarDTO(IncidenciaReabiertaDTO incidenciaReabiertaDTO){
        IncidenciaReabierta incidenciaReabierta = new IncidenciaReabierta();

        incidenciaReabierta.setIdIncidenciaReabierta(incidenciaReabiertaDTO.getIdIncidenciaReabierta());
        incidenciaReabierta.setDescripcionReapertura(incidenciaReabiertaDTO.getDescripcionReapertura());
        incidenciaReabierta.setFechaReapertura(incidenciaReabiertaDTO.getFechaReapertura());

        Optional<Incidencia> optionalIncidencia = incidenciaService.getIncidencias(incidenciaReabiertaDTO.getIdIncidenciaPrincipal());
        if (optionalIncidencia.isPresent()){
            incidenciaReabierta.setIncidenciaPrincipal(optionalIncidencia.get());
        }

        return incidenciaReabierta;
    }
}
