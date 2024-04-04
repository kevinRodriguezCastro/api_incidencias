package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import api_incidencias.api_incidencias.Entidades.Clases.MaterialUtilizado;
import api_incidencias.api_incidencias.Entidades.Clases.TiempoEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioTiempoEmpleado extends JpaRepository<TiempoEmpleado, Long> {
    @Query("SELECT t FROM TiempoEmpleado t WHERE t.parteTrabajo.idOrden = ?1")
    List<TiempoEmpleado> findByIdOrden(Long idOrden);
}
