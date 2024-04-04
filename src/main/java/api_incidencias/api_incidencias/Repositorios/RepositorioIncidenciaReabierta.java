package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Comentario;
import api_incidencias.api_incidencias.Entidades.IncidenciaReabierta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioIncidenciaReabierta extends JpaRepository<IncidenciaReabierta, String> {
}
