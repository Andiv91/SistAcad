package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Evaluacion;
import co.edu.ufps.sistemaacademico.Services.EvaluacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    // Obtener todas las evaluaciones
    @GetMapping
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionService.getAllEvaluaciones();
    }

    // Obtener evaluación por ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable Long id) {
        return evaluacionService.getEvaluacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva evaluación
    @PostMapping
    public Evaluacion createEvaluacion(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.createEvaluacion(evaluacion);
    }

    // Modificar una evaluación existente
    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> updateEvaluacion(
            @PathVariable Long id,
            @RequestParam String nuevoTipo,
            @RequestParam Date nuevaFecha
    ) {
        try {
            Evaluacion updatedEvaluacion = evaluacionService.updateEvaluacion(id, nuevoTipo, nuevaFecha);
            return ResponseEntity.ok(updatedEvaluacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una evaluación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluacion(@PathVariable Long id) {
        evaluacionService.deleteEvaluacion(id);
        return ResponseEntity.noContent().build();
    }

    // Registrar calificaciones
    @PostMapping("/{id}/registrar-calificaciones")
    public ResponseEntity<String> registrarCalificaciones(@PathVariable Long id) {
        evaluacionService.registrarCalificaciones(id);
        return ResponseEntity.ok("Calificaciones registradas.");
    }

    // Consultar resultados
    @GetMapping("/{id}/resultados")
    public ResponseEntity<List<String>> consultarResultados(@PathVariable Long id) {
        try {
            List<String> resultados = evaluacionService.consultarResultados(id);
            return ResponseEntity.ok(resultados);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}