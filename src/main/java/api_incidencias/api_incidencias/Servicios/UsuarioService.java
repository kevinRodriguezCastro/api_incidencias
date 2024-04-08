package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario reposUser;

    private static final String RUTA_IMG = "./imgUsuarios";

    /**
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = reposUser.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuario.getCorreoElectronico(), usuario.getContrasena(), new ArrayList<>());
    }
    */
    public Usuario addUser(Usuario user){
        return reposUser.save(user);
    }

    public String subirImagen(Long idUsuario, MultipartFile file) {
        // Verifica que el archivo no esté vacío
        if (file.isEmpty()) {
            throw new RuntimeException("El archivo está vacío");
        }

        try {
            // Genera un nombre único para la imagen
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // Crea el directorio si no existe
            Path directory = Paths.get(RUTA_IMG);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Guarda el archivo en el servidor
            Path filePath = directory.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // Devuelve la URL de la imagen
            String urlImagen = RUTA_IMG + fileName;

            // Actualizar BD
            Optional<Usuario> usuario = getUser(idUsuario);
            if (usuario.isPresent()){
                usuario.get().setRutaImagen(urlImagen);
                updateUser(idUsuario,usuario.get());
            }else {
                System.out.println("Error al actualizar imagen");
            }
            return urlImagen;
        } catch (Exception e) {
            throw new RuntimeException("Falló la carga de la imagen", e);
        }
    }

    public List<Usuario> getUser(){
        return reposUser.findAll();
    }
    public Optional<Usuario> getUser(Long id){
        return reposUser.findById(id);
    }

    public Optional<Usuario> getUser(String email){
        return reposUser.findByEmail(email);
    }
    public Usuario updateUser(Long idUser, Usuario user){
        //reposUser.save(user);
        Optional<Usuario> userExistenteOptional = reposUser.findById(idUser);

        if (userExistenteOptional.isPresent()) {
            Usuario usuarioExistente = userExistenteOptional.get();

            if (idUser.equals(user.getIdUsuario())) {
                // Actualizo los atributos del libro existente con los del libro proporcionado
                usuarioExistente.setDni(user.getDni());
                usuarioExistente.setNombre(user.getNombre());
                usuarioExistente.setApellido(user.getApellido());
                usuarioExistente.setCorreoElectronico(user.getCorreoElectronico());
                usuarioExistente.setContrasena(user.getContrasena());

                usuarioExistente.setFechaRegistro(user.getFechaRegistro());
                usuarioExistente.setRutaImagen(user.getRutaImagen());
                usuarioExistente.setRol(user.getRol());

                usuarioExistente.setTelefono(user.getTelefono());
                usuarioExistente.setCalle(user.getCalle());
                usuarioExistente.setCiudad(user.getCiudad());
                usuarioExistente.setProvincia(user.getProvincia());
                usuarioExistente.setCodigoPostal(user.getCodigoPostal());
                usuarioExistente.setPais(user.getPais());

                // Guarda el usuario actualizado en el repositorio
                return reposUser.save(usuarioExistente);
            } else {
                throw new IllegalArgumentException("El id proporcionado no coincide con el ID del usuario.");
            }
        } else {
            throw new IllegalArgumentException("El usuario con el ID proporcionado no existe.");
        }
    }

    public ResponseEntity<String> deleteUser(Long id){
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
