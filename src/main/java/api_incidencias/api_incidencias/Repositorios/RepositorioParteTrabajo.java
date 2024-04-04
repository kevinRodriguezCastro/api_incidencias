package api_incidencias.api_incidencias.Repositorios;

import api_incidencias.api_incidencias.Entidades.Clases.ParteTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioParteTrabajo extends JpaRepository<ParteTrabajo, Long> {
    // Aqui la consulta personalizada para coger los partes de trabajo por incidencia

}
