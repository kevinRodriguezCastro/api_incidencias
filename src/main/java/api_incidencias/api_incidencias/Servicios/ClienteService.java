package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.Cliente;
import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Repositorios.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private RepositorioCliente reposCliente;

    public Cliente addCliente(Cliente cliente) {
        return reposCliente.save(cliente);
    }

    public List<Cliente> getCliente() {
        return reposCliente.findAll();
    }

    public Optional<Cliente> getCliente(Long id) {
        return reposCliente.findById(id);
    }

    public Optional<Cliente> getCliente(String email) {
        return reposCliente.findByEmail(email);
    }

    /**
     * Si es el mismo o admin
     *
     * @param idCliente
     * @param cliente
     * @return
     */
    public Cliente updateCliente(Long idCliente, Cliente cliente) {
        Optional<Cliente> clienteExistenteOptional = reposCliente.findById(idCliente);

        if (clienteExistenteOptional.isPresent()) {
            Cliente clienteExixtente = clienteExistenteOptional.get();

            if (idCliente.equals(cliente.getIdUsuario())) {
                // Actualizo los atributos del libro existente con los del libro proporcionado
                clienteExixtente.setDni(cliente.getDni());
                clienteExixtente.setNombre(cliente.getNombre());
                clienteExixtente.setApellido(cliente.getApellido());
                clienteExixtente.setCorreoElectronico(cliente.getCorreoElectronico());
                clienteExixtente.setContrasena(cliente.getContrasena());

                clienteExixtente.setFechaRegistro(cliente.getFechaRegistro());
                clienteExixtente.setImagenPerfil(cliente.getImagenPerfil());
                clienteExixtente.setTelefono(cliente.getTelefono());

                clienteExixtente.setCalle(cliente.getCalle());
                clienteExixtente.setCiudad(cliente.getCiudad());
                clienteExixtente.setProvincia(cliente.getProvincia());
                clienteExixtente.setCodigoPostal(cliente.getCodigoPostal());
                clienteExixtente.setPais(cliente.getPais());

                // Guarda el usuario actualizado en el repositorio
                return reposCliente.save(clienteExixtente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID del usuario.");
            }
        } else {
            throw new IllegalArgumentException("El usuario con el ID proporcionado no existe.");
        }
    }

    /**
     * Solo admin
     *
     * @param id
     * @return
     */
    public ResponseEntity<String> deleteCliente(Long id) {

        Optional<Cliente> userOptional = reposCliente.findById(id);
        if (userOptional.isPresent()) {
            reposCliente.deleteById(id);
            ;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el usuario correspondiente.");
        }
    }

}
