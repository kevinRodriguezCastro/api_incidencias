package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Incidencia;
import api_incidencias.api_incidencias.Entidades.Usuario;
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

    public Incidencia addIncidencia(Incidencia incidencia){
        return reposIncidencia.save(incidencia);
    }

    public List<Incidencia> getIncidencias(){
        return reposIncidencia.findAll();
    }

    public Optional<Incidencia> getIncidencias(Long id){
        return reposIncidencia.findById(id);
    }


    public Incidencia updateIncidencia(Long idIncidencia, Incidencia incidencia){
        //reposUser.save(user);
        Optional<Incidencia> userExistenteOptional = reposIncidencia.findById(idIncidencia);

        if (userExistenteOptional.isPresent()) {
            Incidencia incidenciaExistente = userExistenteOptional.get();

            if (idIncidencia.equals(incidencia.getIdIncidencia())) {
                // Actualizo los atributos del libro existente con los del libro proporcionado
                incidenciaExistente.setTitulo(incidencia.getTitulo());
                incidenciaExistente.setDescripcion(incidencia.getDescripcion());
                incidenciaExistente.setFechaCreacion(incidencia.getFechaCreacion());
                incidenciaExistente.setEstado(incidencia.getEstado());
                incidenciaExistente.setPrioridad(incidencia.getPrioridad());
                //incidenciaExistente.setUsuarioUltimaModificacion(incidencia.getUsuarioUltimaModificacion());
                incidenciaExistente.setUsuarioCliente(incidencia.getUsuarioCliente());
                incidenciaExistente.setUsuarioTecnico(incidencia.getUsuarioTecnico());
                // Guarda el usuario actualizado en el repositorio
                return reposIncidencia.save(incidenciaExistente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID de la incidencia.");
            }
        } else {
            throw new IllegalArgumentException("La incidencia con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> deleteIncidencia(Long id){
        Optional<Incidencia> incidencia = reposIncidencia.findById(id);

        if (incidencia.isPresent()) {
            reposIncidencia.deleteById(id);;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Incidencia eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la incidencia correspondiente.");
        }
    }
}
