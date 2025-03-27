package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Rol;
import co.edu.ufps.sistemaacademico.Repositories.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService {

    @Autowired
    private final RolRepository rolRepository;

    // Obtener todos los roles
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    // Obtener un rol por ID
    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    // Crear un nuevo rol
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Eliminar un rol
    public void deleteRol(Long id) {
        rolRepository.findById(id).ifPresent(Rol::eliminarRol);
        rolRepository.deleteById(id);
    }

    // Asignar permisos a un rol
    public void asignarPermisos(Long id, List<String> permisos) {
        rolRepository.findById(id).ifPresent(rol -> rol.asignarPermisos(permisos));
    }

    // Consultar permisos de un rol
    public List<String> consultarPermisos(Long id) {
        return rolRepository.findById(id)
                .map(Rol::consultarPermisos)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }
}
