package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Calificacion;
import co.edu.ufps.sistemaacademico.Services.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final CalificacionService calificacionService;

    // Obtener todas las calificaciones
    @GetMapping
    public List<Calificacion> getAllCalificaciones() {
        return calificacionService.getAllCalificaciones();
    }

    // Obtener una calificación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable Long id) {
        return calificacionService.getCalificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar una nueva calificación
    @PostMapping
    public Calificacion registrarCalificacion(@RequestParam float valor, @RequestParam String comentario) {
        return calificacionService.registrarCalificacion(valor, comentario);
    }

    // Modificar una calificación existente
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> modificarCalificacion(
            @PathVariable Long id,
            @RequestParam float nuevoValor,
            @RequestParam String nuevoComentario) {
        try {
            Calificacion updatedCalificacion = calificacionService.modificarCalificacion(id, nuevoValor, nuevoComentario);
            return ResponseEntity.ok(updatedCalificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Consultar los detalles de una calificación
    @GetMapping("/{id}/consultar")
    public ResponseEntity<String> consultarCalificacion(@PathVariable Long id) {
        try {
            calificacionService.consultarCalificacion(id);
            return ResponseEntity.ok("Consulta realizada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Validar si una calificación aprueba o no
    @GetMapping("/{id}/aprueba")
    public ResponseEntity<Boolean> apruebaONoAprueba(@PathVariable Long id, @RequestParam float evaluacionValor) {
        try {
            boolean aprueba = calificacionService.apruebaONoAprueba(id, evaluacionValor);
            return ResponseEntity.ok(aprueba);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}