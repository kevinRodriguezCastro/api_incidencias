package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Comentario;
import api_incidencias.api_incidencias.Entidades.Incidencia;
import api_incidencias.api_incidencias.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, Long> {
    @Query("SELECT c FROM Comentario c WHERE c.incidencia.idIncidencia = ?1")
    List<Comentario> findByIncidencia(Long idIncidencia);
}
