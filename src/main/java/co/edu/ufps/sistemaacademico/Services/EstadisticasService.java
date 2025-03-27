package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Estadisticas;
import co.edu.ufps.sistemaacademico.Repositories.EstadisticasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticasService {

    @Autowired
    private final EstadisticasRepository estadisticasRepository;

    // Obtener todas las estadísticas
    public java.util.List<Estadisticas> getAllEstadisticas() {
        return estadisticasRepository.findAll();
    }

    // Obtener una estadística por ID
    public Optional<Estadisticas> getEstadisticasById(Long id) {
        return estadisticasRepository.findById(id);
    }

    // Crear nuevas estadísticas
    public Estadisticas createEstadisticas(Estadisticas estadisticas) {
        estadisticas.generarEstadisticas(new float[]{}, new float[]{}); // Ejemplo: Generar estadísticas vacías
        return estadisticasRepository.save(estadisticas);
    }

    // Actualizar estadísticas
    public Estadisticas updateEstadisticas(Long id, Estadisticas estadisticasDetails) {
        return estadisticasRepository.findById(id).map(estadisticas -> {
            estadisticas.setPromedioGeneral(estadisticasDetails.getPromedioGeneral());
            estadisticas.setAsistenciaPromedio(estadisticasDetails.getAsistenciaPromedio());
            estadisticas.generarEstadisticas(new float[]{}, new float[]{});
            return estadisticasRepository.save(estadisticas);
        }).orElseThrow(() -> new RuntimeException("Estadísticas no encontradas"));
    }

    // Eliminar estadísticas
    public void deleteEstadisticas(Long id) {
        estadisticasRepository.deleteById(id);
    }
}
