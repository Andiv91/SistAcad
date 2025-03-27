package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;

    private String estado; // "activo", "cancelado", "en proceso"

    // Métodos

    public boolean validarPrerrequisitos(Estudiante estudiante, Curso curso) {
        // Simulación de validación de prerrequisitos
        System.out.println("Validando prerrequisitos para el estudiante: " + estudiante.getNombre() + " en el curso: " + curso.getNombreCurso());
        return true; // Lógica ficticia, ajustar según el caso real
    }

    public boolean verificarCupos(Curso curso) {
        // Verifica si hay cupos disponibles en el curso
        System.out.println("Verificando cupos para el curso: " + curso.getNombreCurso());
        return curso.getCuposDisponibles() > 0;
    }

    public void cancelarInscripcion() {
        this.estado = "cancelado";
        System.out.println("Inscripción cancelada.");
    }

    public void consultarInscripcion() {
        System.out.println("Consultando inscripción con ID: " + id + ". Estado: " + estado + ", Fecha: " + fechaInscripcion);
    }
}
