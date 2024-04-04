package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Clases.IncidenciaReabierta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioIncidenciaReabierta extends JpaRepository<IncidenciaReabierta, String> {
    @Query("SELECT i FROM IncidenciaReabierta i WHERE i.incidenciaPrincipal.idIncidencia = ?1")
    List<IncidenciaReabierta> findByIncidencia(Long idIncidencia);
}
