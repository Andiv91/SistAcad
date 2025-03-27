package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Evaluacion;
import co.edu.ufps.sistemaacademico.Repositories.EvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluacionService {

    @Autowired
    private final EvaluacionRepository evaluacionRepository;

    // Obtener todas las evaluaciones
    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }

    // Obtener evaluación por ID
    public Optional<Evaluacion> getEvaluacionById(Long id) {
        return evaluacionRepository.findById(id);
    }

    // Crear una nueva evaluación
    public Evaluacion createEvaluacion(Evaluacion evaluacion) {
        evaluacion.crearEvaluacion();
        return evaluacionRepository.save(evaluacion);
    }

    // Modificar una evaluación existente
    public Evaluacion updateEvaluacion(Long id, String nuevoTipo, Date nuevaFecha) {
        return evaluacionRepository.findById(id).map(evaluacion -> {
            evaluacion.modificarEvaluacion(nuevoTipo, nuevaFecha);
            return evaluacionRepository.save(evaluacion);
        }).orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }

    // Eliminar una evaluación
    public void deleteEvaluacion(Long id) {
        evaluacionRepository.findById(id).ifPresent(Evaluacion::eliminarEvaluacion);
        evaluacionRepository.deleteById(id);
    }

    // Registrar calificaciones
    public void registrarCalificaciones(Long id) {
        evaluacionRepository.findById(id).ifPresent(Evaluacion::registrarCalificaciones);
    }

    // Consultar resultados
    public List<String> consultarResultados(Long id) {
        return evaluacionRepository.findById(id)
                .map(Evaluacion::consultarResultados)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }
}