package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import api_incidencias.api_incidencias.Entidades.Enum.Estado;
import api_incidencias.api_incidencias.Repositorios.RepositorioIncidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {

    @Autowired
    private RepositorioIncidencia reposIncidencia;

    private Seguridad seguridad;

    public IncidenciaService(){
        seguridad = new Seguridad();
    }

    public Incidencia addIncidencia(Incidencia incidencia){
        return reposIncidencia.save(incidencia);
    }

    /**
     * Solo pueden acceder a todas las incidencias los trabajadores
     * @return
     */
    public List<Incidencia> getIncidencias() {
        if (seguridad.isTrabajador()){
            return reposIncidencia.findAll();
        }
        return null;
    }
    public List<Incidencia> getIncidenciasCliente(Long idCliente){
        return reposIncidencia.findByCliente(idCliente);
    }

    public Optional<Incidencia> getIncidencias(Long id){
        return reposIncidencia.findById(id);
    }


    /**
     * Solo se puede editar si la incidencia esta abierta
     * @param idIncidencia
     * @param incidencia
     * @return
     */
    public Incidencia updateIncidencia(Long idIncidencia, Incidencia incidencia) {
        Optional<Incidencia> incidenciaExistenteOptional = reposIncidencia.findById(idIncidencia);

        if (incidenciaExistenteOptional.isPresent()) {
            Incidencia incidenciaExistente = incidenciaExistenteOptional.get();

            //Si la incidencia es aceptada o finalizada no se podra modificar
            if (incidencia.getEstado() != Estado.abierto) {

                if (idIncidencia.equals(incidencia.getIdIncidencia())) {
                    // Actualizo los atributos del libro existente con los del libro proporcionado
                    incidenciaExistente.setTitulo(incidencia.getTitulo());
                    incidenciaExistente.setDescripcion(incidencia.getDescripcion());
                    incidenciaExistente.setFechaCreacion(incidencia.getFechaCreacion());
                    incidenciaExistente.setEstado(incidencia.getEstado());
                    incidenciaExistente.setPrioridad(incidencia.getPrioridad());
                    incidenciaExistente.setUsuarioCliente(incidencia.getUsuarioCliente());
                    // Guarda el usuario actualizado en el repositorio
                    return reposIncidencia.save(incidenciaExistente);
                } else {
                    throw new IllegalArgumentException("El id proporcionado no coincide con el ID de la incidencia.");
                }
            }
        } else {
            throw new IllegalArgumentException("La incidencia con el ID proporcionado no existe.");
        }
        return null;
    }

    /**
     * Solo puede borrar el admin
     * @param id
     * @return
     */
    public ResponseEntity<String> deleteIncidencia(Long id){
        if (seguridad.isAdmin()) {
            Optional<Incidencia> incidencia = reposIncidencia.findById(id);
            if (incidencia.isPresent()) {
                reposIncidencia.deleteById(id);

                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body("Incidencia eliminado correctamente.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la incidencia correspondiente.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("No tienes permisos de admnistrador");
    }



}
