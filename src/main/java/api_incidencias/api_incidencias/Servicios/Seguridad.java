package api_incidencias.api_incidencias.Servicios;

import api_incidencias.api_incidencias.Entidades.Clases.Cliente;
import api_incidencias.api_incidencias.Entidades.Clases.Trabajador;
import api_incidencias.api_incidencias.Entidades.Clases.Usuario;
import api_incidencias.api_incidencias.Entidades.Enum.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class Seguridad {
    @Autowired
    private TrabajadorService trabajadorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;
    /**
     * devuelve el usuario que hace la peticion
     * @return
     */
    public Usuario getUsuarioPeticion(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String correo = userDetails.getUsername();

            // Aquí puedes usar el username para obtener más detalles del usuario si es necesario
            Optional<Usuario> optional = usuarioService.getUser(correo);
            if (optional.isPresent()) {
                return optional.get();
            }
        }
        return null;
    }

    public boolean isAdmin(){
        Usuario usuario = getUsuarioPeticion();
        if (usuario != null){
            Optional<Trabajador> optional = trabajadorService.getTrabajador(usuario.getIdUsuario());
            if (optional.isPresent()){
                if (optional.get().getRol() == Rol.administrador){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isTrabajador(){
        Usuario usuario = getUsuarioPeticion();
        if (usuario != null){
            Optional<Trabajador> optional = trabajadorService.getTrabajador(usuario.getIdUsuario());
            if (optional.isPresent()){
                return true;
            }
        }
        return false;
    }
    public boolean isTecnicoJefe(){
        Usuario usuario = getUsuarioPeticion();
        if (usuario != null){
            Optional<Trabajador> optional = trabajadorService.getTrabajador(usuario.getIdUsuario());
            if (optional.isPresent()){
                if (optional.get().getRol() == Rol.tecnico_jefe){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isElMismo(Long id){
        Usuario usuario = getUsuarioPeticion();
        if (usuario != null){
            if (usuario.getIdUsuario().equals(id)){
                return true;
            }
        }
        return false;
    }
    public boolean isCliente(){
        Usuario usuario = getUsuarioPeticion();
        if (usuario != null){
            Optional<Cliente> optional = clienteService.getCliente(usuario.getIdUsuario());
            if (optional.isPresent()){
                return true;
            }
        }
        return false;
    }
}
