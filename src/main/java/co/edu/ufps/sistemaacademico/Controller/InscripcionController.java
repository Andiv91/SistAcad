package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Curso;
import co.edu.ufps.sistemaacademico.Entities.Estudiante;
import co.edu.ufps.sistemaacademico.Entities.Inscripcion;
import co.edu.ufps.sistemaacademico.Services.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> getInscripcionById(@PathVariable Long id) {
        return inscripcionService.getInscripcionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva inscripción
    @PostMapping
    public Inscripcion createInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.createInscripcion(inscripcion);
    }

    // Cancelar una inscripción
    @PostMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarInscripcion(@PathVariable Long id) {
        inscripcionService.cancelarInscripcion(id);
        return ResponseEntity.ok("Inscripción cancelada.");
    }

    // Validar prerrequisitos
    @PostMapping("/{id}/validar-prerrequisitos")
    public ResponseEntity<Boolean> validarPrerrequisitos(
            @PathVariable Long id,
            @RequestBody Estudiante estudiante,
            @RequestBody Curso curso) {
        boolean resultado = inscripcionService.validarPrerrequisitos(id, estudiante, curso);
        return ResponseEntity.ok(resultado);
    }

    // Verificar cupos en un curso
    @PostMapping("/{id}/verificar-cupos")
    public ResponseEntity<Boolean> verificarCupos(@PathVariable Long id, @RequestBody Curso curso) {
        boolean resultado = inscripcionService.verificarCupos(id, curso);
        return ResponseEntity.ok(resultado);
    }

    // Consultar detalles de una inscripción
    @GetMapping("/{id}/consultar")
    public ResponseEntity<String> consultarInscripcion(@PathVariable Long id) {
        try {
            inscripcionService.consultarInscripcion(id);
            return ResponseEntity.ok("Consulta realizada exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
