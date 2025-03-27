package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Estudiante;
import co.edu.ufps.sistemaacademico.Repositories.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    @Autowired
    private final EstudianteRepository estudianteRepository;

    // Obtener todos los estudiantes
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    // Crear un nuevo estudiante
    public Estudiante createEstudiante(Estudiante estudiante) {
        estudiante.registrarEstudiante();
        return estudianteRepository.save(estudiante);
    }

    // Actualizar un estudiante
    public Estudiante updateEstudiante(Long id, Estudiante estudianteDetails) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.actualizarEstudiante(estudianteDetails.getDireccion(), estudianteDetails.getEmail());
            estudiante.setNombre(estudianteDetails.getNombre());
            estudiante.setCodigoEstudiante(estudianteDetails.getCodigoEstudiante());
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    // Eliminar un estudiante
    public void deleteEstudiante(Long id) {
        estudianteRepository.findById(id).ifPresent(Estudiante::eliminarEstudiante);
        estudianteRepository.deleteById(id);
    }
}