package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Docente;
import co.edu.ufps.sistemaacademico.Services.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    // Obtener todos los docentes
    @GetMapping
    public List<Docente> getAllDocentes() {
        return docenteService.getAllDocentes();
    }

    // Obtener un docente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable Long id) {
        return docenteService.getDocenteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo docente
    @PostMapping
    public Docente createDocente(@RequestBody Docente docente) {
        return docenteService.createDocente(docente);
    }

    // Actualizar un docente existente
    @PutMapping("/{id}")
    public ResponseEntity<Docente> updateDocente(@PathVariable Long id, @RequestBody Docente docenteDetails) {
        try {
            Docente updatedDocente = docenteService.updateDocente(id, docenteDetails);
            return ResponseEntity.ok(updatedDocente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un docente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar un curso a un docente
    @PostMapping("/{id}/asignar-curso")
    public ResponseEntity<String> asignarCurso(@PathVariable Long id, @RequestParam String nombreCurso) {
        docenteService.asignarCurso(id, nombreCurso);
        return ResponseEntity.ok("Curso asignado al docente.");
    }

    // Remover un curso de un docente
    @PostMapping("/{id}/remover-curso")
    public ResponseEntity<String> removerCurso(@PathVariable Long id, @RequestParam String nombreCurso) {
        docenteService.removerCurso(id, nombreCurso);
        return ResponseEntity.ok("Curso removido del docente.");
    }

    // Consultar cursos de un docente
    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<String>> consultarCursos(@PathVariable Long id) {
        try {
            List<String> cursos = docenteService.consultarCursosDeDocente(id);
            return ResponseEntity.ok(cursos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}