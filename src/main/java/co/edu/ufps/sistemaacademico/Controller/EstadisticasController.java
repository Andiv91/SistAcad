package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Estadisticas;
import co.edu.ufps.sistemaacademico.Services.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    // Obtener todas las estadísticas
    @GetMapping
    public List<Estadisticas> getAllEstadisticas() {
        return estadisticasService.getAllEstadisticas();
    }

    // Obtener estadísticas por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estadisticas> getEstadisticasById(@PathVariable Long id) {
        return estadisticasService.getEstadisticasById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevas estadísticas
    @PostMapping
    public Estadisticas createEstadisticas(@RequestBody Estadisticas estadisticas) {
        return estadisticasService.createEstadisticas(estadisticas);
    }

    // Actualizar estadísticas existentes
    @PutMapping("/{id}")
    public ResponseEntity<Estadisticas> updateEstadisticas(@PathVariable Long id, @RequestBody Estadisticas estadisticasDetails) {
        try {
            Estadisticas updatedEstadisticas = estadisticasService.updateEstadisticas(id, estadisticasDetails);
            return ResponseEntity.ok(updatedEstadisticas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar estadísticas
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstadisticas(@PathVariable Long id) {
        estadisticasService.deleteEstadisticas(id);
        return ResponseEntity.noContent().build();
    }
}
