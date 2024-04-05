package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Clases.ParteTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioParteTrabajo extends JpaRepository<ParteTrabajo, Long> {
    @Query("SELECT p FROM ParteTrabajo p WHERE p.incidencia.idIncidencia = ?1")
    List<ParteTrabajo> findByIdIncidencia(Long idIncidencia);

}
