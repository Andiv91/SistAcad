package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Reporte;
import co.edu.ufps.sistemaacademico.Services.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    // Obtener todos los reportes
    @GetMapping
    public List<Reporte> getAllReportes() {
        return reporteService.getAllReportes();
    }

    // Obtener un reporte por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable Long id) {
        return reporteService.getReporteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo reporte
    @PostMapping
    public Reporte createReporte(@RequestBody Reporte reporte) {
        return reporteService.createReporte(reporte);
    }

    // Consultar estadísticas de un reporte
    @GetMapping("/{id}/estadisticas")
    public ResponseEntity<String> consultarEstadisticas(@PathVariable Long id) {
        try {
            String estadisticas = reporteService.consultarEstadisticas(id);
            return ResponseEntity.ok(estadisticas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}