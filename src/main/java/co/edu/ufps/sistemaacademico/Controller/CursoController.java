package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Curso;
import co.edu.ufps.sistemaacademico.Services.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    // Obtener todos los cursos
    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getAllCursos();
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoService.getCursoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo curso
    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.createCurso(curso);
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        try {
            Curso updatedCurso = cursoService.updateCurso(id, cursoDetails);
            return ResponseEntity.ok(updatedCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar un docente a un curso
    @PostMapping("/{cursoId}/asignar-docente/{docenteId}")
    public ResponseEntity<String> asignarDocenteACurso(@PathVariable Long cursoId, @PathVariable Long docenteId) {
        cursoService.asignarDocenteACurso(cursoId, docenteId);
        return ResponseEntity.ok("Docente asignado al curso.");
    }

    // Remover un docente de un curso
    @PostMapping("/{cursoId}/remover-docente")
    public ResponseEntity<String> removerDocenteDeCurso(@PathVariable Long cursoId) {
        cursoService.removerDocenteDeCurso(cursoId);
        return ResponseEntity.ok("Docente removido del curso.");
    }

    // Obtener todos los cursos asignados a un docente
    @GetMapping("/docente/{docenteId}")
    public List<Curso> getCursosPorDocente(@PathVariable Long docenteId) {
        return cursoService.getCursosPorDocente(docenteId);
    }
}