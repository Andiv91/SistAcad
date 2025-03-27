package co.edu.ufps.sistemaacademico.Controller;

import co.edu.ufps.sistemaacademico.Entities.Permiso;
import co.edu.ufps.sistemaacademico.Services.PermisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permisos")
@RequiredArgsConstructor
public class PermisoController {

    private final PermisoService permisoService;

    // Obtener todos los permisos
    @GetMapping
    public List<Permiso> getAllPermisos() {
        return permisoService.getAllPermisos();
    }

    // Obtener un permiso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Permiso> getPermisoById(@PathVariable Long id) {
        return permisoService.getPermisoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo permiso
    @PostMapping
    public Permiso createPermiso(@RequestBody Permiso permiso) {
        return permisoService.createPermiso(permiso);
    }

    // Eliminar un permiso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermiso(@PathVariable Long id) {
        permisoService.deletePermiso(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar un permiso a un rol
    @PostMapping("/{id}/asignar")
    public ResponseEntity<String> asignarPermiso(@PathVariable Long id, @RequestParam String rol) {
        permisoService.asignarPermiso(id, rol);
        return ResponseEntity.ok("Permiso asignado al rol.");
    }

    // Revocar un permiso de un rol
    @PostMapping("/{id}/revocar")
    public ResponseEntity<String> revocarPermiso(@PathVariable Long id, @RequestParam String rol) {
        permisoService.revocarPermiso(id, rol);
        return ResponseEntity.ok("Permiso revocado del rol.");
    }
}