package api_incidencias.api_incidencias.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/app")
public class FrontControlador {
    @GetMapping("/login")
    public String index() {
        return "app_trabajador/login"; // Se refiere al archivo index.html en src/main/resources/templates
    }
}
