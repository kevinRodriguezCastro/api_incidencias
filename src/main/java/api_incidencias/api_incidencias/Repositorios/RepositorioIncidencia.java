package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioIncidencia extends JpaRepository<Incidencia, Long> {
}
