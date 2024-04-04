package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.IncidenciaReabierta;
import api_incidencias.api_incidencias.Repositorios.RepositorioIncidenciaReabierta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaReabiertaService {
    @Autowired
    private RepositorioIncidenciaReabierta reposIncidenciaReabierta;

    public IncidenciaReabierta addIncidenciaReabierta(IncidenciaReabierta incidenciaReabierta){
        return reposIncidenciaReabierta.save(incidenciaReabierta);
    }

    public List<IncidenciaReabierta> getIncidenciasReabiertas(){
        return reposIncidenciaReabierta.findAll();
    }

    public Optional<IncidenciaReabierta> getIncidenciasReabiertas(String idIncidenciaReabierta){
        return reposIncidenciaReabierta.findById(idIncidenciaReabierta);
    }

    public List<IncidenciaReabierta> getIncidenciasReabiertas(Long idIncidencia){
        return reposIncidenciaReabierta.findByIncidencia(idIncidencia);
    }
  
    public IncidenciaReabierta updateIncidenciaReabierta(String idIncidenciaReabierta, IncidenciaReabierta incidenciaReabierta){
        Optional<IncidenciaReabierta> incidenciaOptional = reposIncidenciaReabierta.findById(idIncidenciaReabierta);

        if (incidenciaOptional.isPresent()) {
            IncidenciaReabierta incidenciaReabiertaExistente = incidenciaOptional.get();

            if (idIncidenciaReabierta.equals(incidenciaReabierta.getIdIncidenciaReabierta())) {
                // Actualizo los atributos del libro existente con los del libro proporcionado
                incidenciaReabiertaExistente.setDescripcionReapertura(incidenciaReabierta.getDescripcionReapertura());
                incidenciaReabiertaExistente.setFechaReapertura(incidenciaReabierta.getFechaReapertura());
                incidenciaReabiertaExistente.setIncidenciaPrincipal(incidenciaReabierta.getIncidenciaPrincipal());
                // Guarda el usuario actualizado en el repositorio
                return reposIncidenciaReabierta.save(incidenciaReabiertaExistente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID de la incidencia.");
            }
        } else {
            throw new IllegalArgumentException("La incidencia con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> deleteIncidenciaReabierta(String id){
        Optional<IncidenciaReabierta> incidenciaReabierta = reposIncidenciaReabierta.findById(id);

        if (incidenciaReabierta.isPresent()) {
            reposIncidenciaReabierta.deleteById(id);;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Incidencia eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la incidencia correspondiente.");
        }
    }

}
