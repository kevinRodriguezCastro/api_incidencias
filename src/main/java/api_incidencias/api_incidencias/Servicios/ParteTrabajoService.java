package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import api_incidencias.api_incidencias.Entidades.Clases.ParteTrabajo;
import api_incidencias.api_incidencias.Repositorios.RepositorioIncidencia;
import api_incidencias.api_incidencias.Repositorios.RepositorioParteTrabajo;
import api_incidencias.api_incidencias.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParteTrabajoService {
    @Autowired
    private RepositorioParteTrabajo reposParteTrabajo;

    public ParteTrabajo addParteTrabajo(ParteTrabajo parteTb){
        return reposParteTrabajo.save(parteTb);
    }

    public List<ParteTrabajo> getPartesTrabajo(){
        return reposParteTrabajo.findAll();
    }
    // Este hay que hacerlo
    //public List<ParteTrabajo> getPartesTrabajoPorIncidencia(Long idIncidencia){
    //    return reposParteTrabajo.findByIncidencia(idIncidencia);
    //}

    public Optional<ParteTrabajo> getPartesTrabajoPorId(Long idOrden){
        return reposParteTrabajo.findById(idOrden);
    }


    public ParteTrabajo updateParteTrabajo(Long idOrden, ParteTrabajo parteTb){
        Optional<ParteTrabajo> parteTbExistenteOptional = reposParteTrabajo.findById(idOrden);

        if (parteTbExistenteOptional.isPresent()) {
            ParteTrabajo parteTbExistente = parteTbExistenteOptional.get();

            if (idOrden.equals(parteTb.getIdOrden())) {
                // Actualizo los atributos del parteTrabajo existente con los del parteTrabajo proporcionado
                parteTbExistente.setTrabajoRealizado(parteTb.getTrabajoRealizado());
                parteTbExistente.setDiagnostico(parteTb.getDiagnostico());
                parteTbExistente.setObservaciones(parteTb.getObservaciones());
                parteTbExistente.setCosteReparacion(parteTb.getCosteReparacion());
                parteTbExistente.setParteTrabajoImg(parteTb.getParteTrabajoImg());
                //parteTbExistente.setIncidencia(parteTb.getIncidencia());

                // Guarda el parte de trabajo actualizado en el repositorio
                return reposParteTrabajo.save(parteTbExistente);
            } else {
                throw new IllegalArgumentException("El idOrden proporcionado no coincide con el ID del parteTrabajo.");
            }
        } else {
            throw new IllegalArgumentException("El parteTrabajo con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> deleteParteTrabajo(Long idOrden){
        Optional<ParteTrabajo> parteTb = reposParteTrabajo.findById(idOrden);

        if (parteTb.isPresent()) {
            reposParteTrabajo.deleteById(idOrden);;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("ParteTrabajo eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el parteTrabajo correspondiente.");
        }
    }
}
