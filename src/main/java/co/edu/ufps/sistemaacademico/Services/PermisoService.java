package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Permiso;
import co.edu.ufps.sistemaacademico.Repositories.PermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermisoService {

    @Autowired
    private final PermisoRepository permisoRepository;

    // Obtener todos los permisos
    public List<Permiso> getAllPermisos() {
        return permisoRepository.findAll();
    }

    // Obtener un permiso por ID
    public Optional<Permiso> getPermisoById(Long id) {
        return permisoRepository.findById(id);
    }

    // Crear un nuevo permiso
    public Permiso createPermiso(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    // Eliminar un permiso
    public void deletePermiso(Long id) {
        permisoRepository.findById(id).ifPresent(permiso -> {
            permiso.revocarPermiso("desconocido"); // Revocar en contexto ficticio
            permisoRepository.deleteById(id);
        });
    }

    // Asignar un permiso a un rol
    public void asignarPermiso(Long id, String rol) {
        permisoRepository.findById(id).ifPresent(permiso -> permiso.asignarPermiso(rol));
    }

    // Revocar un permiso de un rol
    public void revocarPermiso(Long id, String rol) {
        permisoRepository.findById(id).ifPresent(permiso -> permiso.revocarPermiso(rol));
    }
}
