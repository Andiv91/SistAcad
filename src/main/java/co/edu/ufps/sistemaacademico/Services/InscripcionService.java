package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Curso;
import co.edu.ufps.sistemaacademico.Entities.Estudiante;
import co.edu.ufps.sistemaacademico.Entities.Inscripcion;
import co.edu.ufps.sistemaacademico.Repositories.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    @Autowired
    private final InscripcionRepository inscripcionRepository;

    // Obtener todas las inscripciones
    public List<Inscripcion> getAllInscripciones() {
        return inscripcionRepository.findAll();
    }

    // Obtener una inscripción por ID
    public Optional<Inscripcion> getInscripcionById(Long id) {
        return inscripcionRepository.findById(id);
    }

    // Crear una nueva inscripción
    public Inscripcion createInscripcion(Inscripcion inscripcion) {
        inscripcion.setEstado("en proceso");
        return inscripcionRepository.save(inscripcion);
    }

    // Cancelar una inscripción
    public void cancelarInscripcion(Long id) {
        inscripcionRepository.findById(id).ifPresent(inscripcion -> {
            inscripcion.cancelarInscripcion();
            inscripcionRepository.save(inscripcion);
        });
    }

    // Validar prerrequisitos
    public boolean validarPrerrequisitos(Long id, Estudiante estudiante, Curso curso) {
        return inscripcionRepository.findById(id)
                .map(inscripcion -> inscripcion.validarPrerrequisitos(estudiante, curso))
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }

    // Verificar cupos en un curso
    public boolean verificarCupos(Long id, Curso curso) {
        return inscripcionRepository.findById(id)
                .map(inscripcion -> inscripcion.verificarCupos(curso))
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }

    // Consultar detalles de una inscripción
    public void consultarInscripcion(Long id) {
        inscripcionRepository.findById(id).ifPresent(Inscripcion::consultarInscripcion);
    }
}