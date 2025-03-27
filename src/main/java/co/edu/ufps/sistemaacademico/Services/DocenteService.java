package co.edu.ufps.sistemaacademico.Services;

import co.edu.ufps.sistemaacademico.Entities.Docente;
import co.edu.ufps.sistemaacademico.Repositories.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocenteService {

    @Autowired
    private final DocenteRepository docenteRepository;

    // Obtener todos los docentes
    public List<Docente> getAllDocentes() {
        return docenteRepository.findAll();
    }

    // Obtener un docente por ID
    public Optional<Docente> getDocenteById(Long id) {
        return docenteRepository.findById(id);
    }

    // Crear un nuevo docente
    public Docente createDocente(Docente docente) {
        docente.registrarDocente();
        return docenteRepository.save(docente);
    }

    // Actualizar un docente
    public Docente updateDocente(Long id, Docente docenteDetails) {
        return docenteRepository.findById(id).map(docente -> {
            docente.actualizarDocente(docenteDetails.getEspecializacion(), docenteDetails.getTelefono());
            docente.setNombre(docenteDetails.getNombre());
            docente.setEmail(docenteDetails.getEmail());
            docente.setCodigoDocente(docenteDetails.getCodigoDocente());
            return docenteRepository.save(docente);
        }).orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    // Eliminar un docente
    public void deleteDocente(Long id) {
        docenteRepository.findById(id).ifPresent(Docente::eliminarDocente);
        docenteRepository.deleteById(id);
    }

    // Asignar un curso a un docente
    public void asignarCurso(Long id, String nombreCurso) {
        docenteRepository.findById(id).ifPresent(docente -> docente.asignarCurso(nombreCurso));
    }

    // Remover un curso de un docente
    public void removerCurso(Long id, String nombreCurso) {
        docenteRepository.findById(id).ifPresent(docente -> docente.removerCurso(nombreCurso));
    }

    // Consultar cursos asignados a un docente
    public List<String> consultarCursosDeDocente(Long id) {
        return docenteRepository.findById(id)
                .map(Docente::consultarCursos)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }
}