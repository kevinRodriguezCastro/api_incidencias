package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.TiempoEmpleado;
import api_incidencias.api_incidencias.Repositorios.RepositorioTiempoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TiempoEmpleadoService {
    @Autowired
    private RepositorioTiempoEmpleado repositorioTiempoEmpleado;

    public TiempoEmpleado addTiempoEmpleado(TiempoEmpleado tiempoEmpleado){
        return repositorioTiempoEmpleado.save(tiempoEmpleado);
    }

    public List<TiempoEmpleado> getTiempoEmpleado(){
        return repositorioTiempoEmpleado.findAll();
    }

    public Optional<TiempoEmpleado> getTiempoEmpleadoPorId(Long id){
        return repositorioTiempoEmpleado.findById(id);
    }

    public List<TiempoEmpleado> getTiempoEmpleadoPorIdOrden(Long idOrdenParteTb){
        return repositorioTiempoEmpleado.findByIdOrden(idOrdenParteTb);
    }
    public TiempoEmpleado updateTiempoEmpleado(Long idTiempoEmpleado, TiempoEmpleado tiempoEmpleado){
        Optional<TiempoEmpleado> optional = repositorioTiempoEmpleado.findById(idTiempoEmpleado);

        if (optional.isPresent()) {
            TiempoEmpleado tiempoEmpleadoExistente = optional.get();

            if (idTiempoEmpleado.equals(tiempoEmpleado.getIdTiempoEmpleado())) {

                tiempoEmpleadoExistente.setFecha(tiempoEmpleado.getFecha());
                tiempoEmpleadoExistente.setParteTrabajo(tiempoEmpleado.getParteTrabajo());
                tiempoEmpleadoExistente.setHoraEntrada(tiempoEmpleado.getHoraEntrada());
                tiempoEmpleadoExistente.setHoraSalida(tiempoEmpleado.getHoraSalida());
                tiempoEmpleadoExistente.setModoResolucion(tiempoEmpleado.getModoResolucion());


                return repositorioTiempoEmpleado.save(tiempoEmpleadoExistente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID del tiempo empleado.");
            }
        } else {
            throw new IllegalArgumentException("El tiempo empleado con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> deleteTiempoUsado(Long id){
        Optional<TiempoEmpleado> tiempoEmpleado = repositorioTiempoEmpleado.findById(id);

        if (tiempoEmpleado.isPresent()) {
            repositorioTiempoEmpleado.deleteById(id);;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Tiempo usado eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el tiempo empleado correspondiente.");
        }
    }

}
