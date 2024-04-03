package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Usuario;
import api_incidencias.api_incidencias.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario reposUser;

    public List<Usuario> obtenerTodosUsuarios(){
        return reposUser.findAll();
    }
    public Optional<Usuario> obtenerUsuarioPorID(Long id){
        return reposUser.findById(id);
    }

    public Optional<Usuario> obtenerUsuarioPorEmail(String email){
        return reposUser.findByEmail(email);
    }

    public void addUser(Usuario user){
        reposUser.save(user);
    }

    public Usuario actualizarUsuario(Long idUser, Usuario user){
        //reposUser.save(user);
        Optional<Usuario> userExistenteOptional = reposUser.findById(idUser);

        if (userExistenteOptional.isPresent()) {
            Usuario usuarioExistente = userExistenteOptional.get();

            if (idUser.equals(user.getIdUsuario())) {
                // Actualizo los atributos del libro existente con los del libro proporcionado
                usuarioExistente.setNombre(user.getNombre());
                usuarioExistente.setCorreoElectronico(user.getCorreoElectronico());
                usuarioExistente.setContrasenia(user.getContrasenia());
                usuarioExistente.setApellido(user.getApellido());
                usuarioExistente.setRol(user.getRol());

                // Guarda el usuario actualizado en el repositorio
                return reposUser.save(usuarioExistente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID del usuario.");
            }
        } else {
            throw new IllegalArgumentException("El usuario con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> borrarUser(Long id){
        Optional<Usuario> userOptional = reposUser.findById(id);

        if (userOptional.isPresent()) {
            reposUser.deleteById(id);;

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Usuario eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el usuario correspondiente.");
        }
    }
}
