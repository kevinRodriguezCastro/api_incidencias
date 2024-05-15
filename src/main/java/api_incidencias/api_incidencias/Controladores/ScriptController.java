package api_incidencias.api_incidencias.Controladores;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.MediaType;
@Controller
@RequestMapping("/app/scripts")
public class ScriptController {

    @GetMapping("/{scriptName}")
    public ResponseEntity<Resource> getScript(@PathVariable String scriptName) {
        Resource resource = new ClassPathResource("static/js/" + scriptName);

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/javascript"))
                .body(resource);
    }
}
