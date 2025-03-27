package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Rol;
import co.edu.ufps.sistemaacademico.Services.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    // Obtener todos los roles
    @GetMapping
    public List<Rol> getAllRoles() {
        return rolService.getAllRoles();
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable Long id) {
        return rolService.getRolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo rol
    @PostMapping
    public Rol createRol(@RequestBody Rol rol) {
        return rolService.createRol(rol);
    }

    // Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar permisos a un rol
    @PostMapping("/{id}/asignar-permisos")
    public ResponseEntity<String> asignarPermisos(@PathVariable Long id, @RequestBody List<String> permisos) {
        rolService.asignarPermisos(id, permisos);
        return ResponseEntity.ok("Permisos asignados al rol.");
    }

    // Consultar permisos de un rol
    @GetMapping("/{id}/consultar-permisos")
    public ResponseEntity<List<String>> consultarPermisos(@PathVariable Long id) {
        try {
            List<String> permisos = rolService.consultarPermisos(id);
            return ResponseEntity.ok(permisos);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
