package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Comentario;
import api_incidencias.api_incidencias.Entidades.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, Long> {
}
