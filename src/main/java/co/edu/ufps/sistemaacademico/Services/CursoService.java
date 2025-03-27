package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Curso;
import co.edu.ufps.sistemaacademico.Repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;

    // Obtener todos los cursos
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    // Obtener un curso por ID
    public Optional<Curso> getCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    // Crear un nuevo curso
    public Curso createCurso(Curso curso) {
        curso.crearCurso();
        return cursoRepository.save(curso);
    }

    // Actualizar un curso existente
    public Curso updateCurso(Long id, Curso cursoDetails) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNombreCurso(cursoDetails.getNombreCurso());
            curso.setCreditos(cursoDetails.getCreditos());
            curso.setHorario(cursoDetails.getHorario());
            curso.setCodigoCurso(cursoDetails.getCodigoCurso());
            curso.setCuposDisponibles(cursoDetails.getCuposDisponibles());
            curso.modificarCurso(cursoDetails.getHorario(), cursoDetails.getCuposDisponibles());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    // Eliminar un curso
    public void deleteCurso(Long id) {
        cursoRepository.findById(id).ifPresent(Curso::eliminarCurso);
        cursoRepository.deleteById(id);
    }

    // Asignar un docente al curso
    public void asignarDocenteACurso(Long cursoId, Long docenteId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.asignarDocente(docenteId);
        cursoRepository.save(curso);
    }

    // Remover un docente del curso
    public void removerDocenteDeCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.removerDocente();
        cursoRepository.save(curso);
    }

    // Obtener todos los cursos asignados a un docente
    public List<Curso> getCursosPorDocente(Long docenteId) {
        return cursoRepository.findByDocenteId(docenteId);
    }
}