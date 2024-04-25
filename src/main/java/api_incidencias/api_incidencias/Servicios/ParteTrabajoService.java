package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.ParteTrabajo;
import api_incidencias.api_incidencias.Repositorios.RepositorioParteTrabajo;
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
    @Autowired
    private Seguridad seguridad;

    /**
     * solo trabajadores
     * @param parteTb
     * @return
     */
    public ParteTrabajo addParteTrabajo(ParteTrabajo parteTb){
        if (seguridad.isTrabajador())
        return reposParteTrabajo.save(parteTb);
        return null;
    }

    /**
     * solo trabajadores
     * @return
     */
    public List<ParteTrabajo> getPartesTrabajo(){
        if (seguridad.isTrabajador())
        return reposParteTrabajo.findAll();
        return null;
    }


    public List<ParteTrabajo> getPartesTrabajoPorIncidencia(String idIncidencia){
        return reposParteTrabajo.findByIdIncidencia(idIncidencia);
    }

    public Optional<ParteTrabajo> getPartesTrabajoPorId(Long idOrden){
        return reposParteTrabajo.findById(idOrden);
    }

    public List<ParteTrabajo> getPartesTrabajosNoTerminado(Long idUsuario,String idIncidencia){
        return reposParteTrabajo.findByNoTerminado(idUsuario,idIncidencia);
    }

    /**
     * solo admin
     * @param idOrden
     * @param parteTb
     * @return
     */
    public ParteTrabajo updateParteTrabajo(Long idOrden, ParteTrabajo parteTb){
        if (seguridad.isAdmin()) {
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
                    parteTbExistente.setTecnico(parteTb.getTecnico());
                    parteTbExistente.setTerminado(parteTb.isTerminado());
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
        throw new IllegalArgumentException("No eres admin.");
    }

    /**
     * solo admin
     * @param idOrden
     * @return
     */
    public ResponseEntity<String> deleteParteTrabajo(Long idOrden){
        if(seguridad.isAdmin()) {
            Optional<ParteTrabajo> parteTb = reposParteTrabajo.findById(idOrden);

            if (parteTb.isPresent()) {
                reposParteTrabajo.deleteById(idOrden);
                ;

                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("ParteTrabajo eliminado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el parteTrabajo correspondiente.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("No tienes permisos.");
    }
}
