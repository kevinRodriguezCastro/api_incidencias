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
    @Autowired
    private Seguridad seguridad;

    public IncidenciaReabierta addIncidenciaReabierta(IncidenciaReabierta incidenciaReabierta){
        String id = generarId(incidenciaReabierta);
        incidenciaReabierta.setIdIncidenciaReabierta(id);
        return reposIncidenciaReabierta.save(incidenciaReabierta);
    }

    /**
     * solo trabajadores
     * @return
     */
    public List<IncidenciaReabierta> getIncidenciasReabiertas(){
        if (seguridad.isTrabajador())
            return reposIncidenciaReabierta.findAll();
        return null;
    }

    public Optional<IncidenciaReabierta> getIncidenciasReabiertas(String idIncidenciaReabierta){
        return reposIncidenciaReabierta.findById(idIncidenciaReabierta);
    }

    public List<IncidenciaReabierta> getIncidenciasReabiertas(Long idIncidencia){
        return reposIncidenciaReabierta.findByIncidencia(idIncidencia);
    }

    /**
     * Solo admin
      * @param idIncidenciaReabierta
     * @param incidenciaReabierta
     * @return
     */
    public IncidenciaReabierta updateIncidenciaReabierta(String idIncidenciaReabierta, IncidenciaReabierta incidenciaReabierta){
        if(seguridad.isAdmin()){
            Optional<IncidenciaReabierta> incidenciaOptional = reposIncidenciaReabierta.findById(idIncidenciaReabierta);

            if (incidenciaOptional.isPresent()) {
                IncidenciaReabierta incidenciaReabiertaExistente = incidenciaOptional.get();

                if (idIncidenciaReabierta.equals(incidenciaReabierta.getIdIncidenciaReabierta())) {

                    incidenciaReabiertaExistente.setDescripcionReapertura(incidenciaReabierta.getDescripcionReapertura());
                    incidenciaReabiertaExistente.setFechaReapertura(incidenciaReabierta.getFechaReapertura());
                    incidenciaReabiertaExistente.setIncidenciaPrincipal(incidenciaReabierta.getIncidenciaPrincipal());

                    return reposIncidenciaReabierta.save(incidenciaReabiertaExistente);
                } else {
                    throw new IllegalArgumentException("El id proporcionado no coincide con el ID de la incidencia.");
                }
            } else {
                throw new IllegalArgumentException("La incidencia con el ID proporcionado no existe.");
            }
        }
        throw new IllegalArgumentException("No eres admin");
    }

    /**
     * Solo admin
     * @param id
     * @return
     */
    public ResponseEntity<String> deleteIncidenciaReabierta(String id){
        if (seguridad.isAdmin()){
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
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("No eres admin");
    }

    private String generarId(IncidenciaReabierta incidenciaReabierta){
        Long idIncidencia = incidenciaReabierta.getIncidenciaPrincipal().getIdIncidencia();
        List<IncidenciaReabierta> listaIncidenciasReabiertas = getIncidenciasReabiertas(idIncidencia);
        String id;

        if (listaIncidenciasReabiertas.isEmpty()){
            id = idIncidencia+"R"+1;
        }else {
            //Coge el ultimo id registrado. coge el numero despues de la R y le suma uno. ejemplo si la ultima incidencia reabierta es 1455R2 dara 1455R3
            String ultimoNumero = listaIncidenciasReabiertas.get(listaIncidenciasReabiertas.size() - 1).getIdIncidenciaReabierta().split("R")[1];
            int nuevoNumero = Integer.parseInt(ultimoNumero) + 1;
            id = idIncidencia + "R" + nuevoNumero;
        }
        return id;
    }

}
