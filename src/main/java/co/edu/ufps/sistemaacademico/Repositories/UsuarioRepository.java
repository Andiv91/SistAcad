package co.edu.ufps.sistemaacademico.Repositories;


import co.edu.ufps.sistemaacademico.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
