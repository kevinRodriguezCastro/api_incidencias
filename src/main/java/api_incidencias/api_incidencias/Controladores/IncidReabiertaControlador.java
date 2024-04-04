package api_incidencias.api_incidencias.Controladores;

import api_incidencias.api_incidencias.Entidades.IncidenciaReabierta;
import api_incidencias.api_incidencias.Servicios.IncidenciaReabiertaService;
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

    @GetMapping
    public List<IncidenciaReabierta> getIncidenciasReabiertas(){
        return incidReabiertaService.getIncidenciasReabiertas();
    }

    @GetMapping("/{idIncidReabierta}")
    public Optional<IncidenciaReabierta> getIncidReabiertasPorId(@PathVariable("idIncidReabierta") String idIncidReabierta){
        return incidReabiertaService.getIncidenciasReabiertas(idIncidReabierta);
    }

    @PostMapping
    public ResponseEntity<IncidenciaReabierta> crearIncidenciaReabierta(@RequestBody IncidenciaReabierta incidReabierta){
        IncidenciaReabierta nuevaIncidReabierta = incidReabiertaService.addIncidenciaReabierta(incidReabierta);
        return new ResponseEntity<>(nuevaIncidReabierta, HttpStatus.CREATED);
    }

    @PutMapping("/{idIncidReabierta}")
    public ResponseEntity<IncidenciaReabierta> actualizarIncidenciaReabierta(@PathVariable String idIncidReabierta, @RequestBody IncidenciaReabierta incidReabierta) {
        IncidenciaReabierta incidReabiertaActualizada = incidReabiertaService.updateIncidenciaReabierta(idIncidReabierta, incidReabierta);
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
}
