package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Reporte;
import co.edu.ufps.sistemaacademico.Repositories.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReporteService {

    @Autowired
    private final ReporteRepository reporteRepository;

    // Obtener todos los reportes
    public List<Reporte> getAllReportes() {
        return reporteRepository.findAll();
    }

    // Obtener un reporte por ID
    public Optional<Reporte> getReporteById(Long id) {
        return reporteRepository.findById(id);
    }

    // Crear un nuevo reporte
    public Reporte createReporte(Reporte reporte) {
        reporte.generarReporte();
        return reporteRepository.save(reporte);
    }

    // Consultar estadísticas de un reporte
    public String consultarEstadisticas(Long id) {
        return reporteRepository.findById(id)
                .map(reporte -> reporte.consultarEstadisticas().getDatos())
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }
}
