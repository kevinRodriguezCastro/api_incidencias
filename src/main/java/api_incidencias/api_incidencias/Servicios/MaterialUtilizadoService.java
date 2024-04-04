package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.MaterialUtilizado;
import api_incidencias.api_incidencias.Entidades.Clases.TiempoEmpleado;
import api_incidencias.api_incidencias.Repositorios.RepositorioMaterialUtilizado;
import api_incidencias.api_incidencias.Repositorios.RepositorioTiempoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class MaterialUtilizadoService {
    @Autowired
    private RepositorioMaterialUtilizado repositorioMaterialUtilizado;
    public MaterialUtilizado addMaterialUtilizado(MaterialUtilizado materialUtilizado){
        return repositorioMaterialUtilizado.save(materialUtilizado);
    }

    public List<MaterialUtilizado> getMaterialUtilizados(){
        return repositorioMaterialUtilizado.findAll();
    }

    public Optional<MaterialUtilizado> getMaterialUtilizados(Long id){
        return repositorioMaterialUtilizado.findById(id);
    }
    public List<MaterialUtilizado> getMaterialUtilizadosOrden(Long idOrden){
        return repositorioMaterialUtilizado.findByIdOrden(idOrden);
    }

    public MaterialUtilizado updateMaterialUtilizado(Long idMaterialutilizado, MaterialUtilizado materialUtilizado){
        Optional<MaterialUtilizado> optional = repositorioMaterialUtilizado.findById(idMaterialutilizado);

        if (optional.isPresent()) {
            MaterialUtilizado materialUtilizadoExistente = optional.get();

            if (idMaterialutilizado.equals(materialUtilizado.getIdMaterial())) {

                materialUtilizadoExistente.setNombre(materialUtilizado.getNombre());
                materialUtilizadoExistente.setCantidad(materialUtilizado.getCantidad());
                materialUtilizadoExistente.setCoste(materialUtilizado.getCoste());
                materialUtilizadoExistente.setParteTrabajo(materialUtilizado.getParteTrabajo());



                return repositorioMaterialUtilizado.save(materialUtilizadoExistente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID de la incidencia.");
            }
        } else {
            throw new IllegalArgumentException("La incidencia con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> deleteMaterialUtilizado(Long id){
        Optional<MaterialUtilizado> materialUtilizado = repositorioMaterialUtilizado.findById(id);

        if (materialUtilizado.isPresent()) {
            repositorioMaterialUtilizado.deleteById(id);;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Incidencia eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la incidencia correspondiente.");
        }
    }
}
