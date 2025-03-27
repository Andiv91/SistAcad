package co.edu.ufps.sistemaacademico.Repositories;

import co.edu.ufps.sistemaacademico.Entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Consultar los cursos asignados a un docente específico por ID
    List<Curso> findByDocenteId(Long docenteId);
}