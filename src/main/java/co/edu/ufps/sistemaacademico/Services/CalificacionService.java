package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Calificacion;
import co.edu.ufps.sistemaacademico.Repositories.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    @Autowired
    private final CalificacionRepository calificacionRepository;

    // Obtener todas las calificaciones
    public List<Calificacion> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    // Obtener una calificación por ID
    public Optional<Calificacion> getCalificacionById(Long id) {
        return calificacionRepository.findById(id);
    }

    // Registrar una nueva calificación
    public Calificacion registrarCalificacion(float valor, String comentario) {
        Calificacion calificacion = new Calificacion();
        calificacion.registrarCalificacion(valor, comentario);
        return calificacionRepository.save(calificacion);
    }

    // Modificar una calificación existente
    public Calificacion modificarCalificacion(Long id, float nuevoValor, String nuevoComentario) {
        return calificacionRepository.findById(id).map(calificacion -> {
            calificacion.modificarCalificacion(nuevoValor, nuevoComentario);
            return calificacionRepository.save(calificacion);
        }).orElseThrow(() -> new RuntimeException("Calificación no encontrada"));
    }

    // Consultar los detalles de una calificación
    public void consultarCalificacion(Long id) {
        calificacionRepository.findById(id).ifPresent(Calificacion::consultarCalificacion);
    }

    // Validar si una calificación aprueba o no
    public boolean apruebaONoAprueba(Long id, float evaluacionValor) {
        return calificacionRepository.findById(id)
                .map(calificacion -> calificacion.apruebaONoAprueba(evaluacionValor))
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));
    }
}