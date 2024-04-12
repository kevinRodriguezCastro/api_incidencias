package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Clases.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioIncidencia extends JpaRepository<Incidencia, Long> {
    @Query("SELECT i FROM Incidencia i WHERE i.usuarioCliente.idUsuario = ?1")
    List<Incidencia> findByCliente(Long idCliente);

}
