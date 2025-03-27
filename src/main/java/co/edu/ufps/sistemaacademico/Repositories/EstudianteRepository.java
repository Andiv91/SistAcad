package co.edu.ufps.sistemaacademico.Repositories;

import co.edu.ufps.sistemaacademico.Entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}