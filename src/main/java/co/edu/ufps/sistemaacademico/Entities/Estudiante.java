package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Date fechaNacimiento;
    private String direccion;
    private String email;
    private double codigoEstudiante;

    // Métodos

    public void registrarEstudiante() {
        System.out.println("Estudiante " + nombre + " registrado exitosamente.");
    }

    public void actualizarEstudiante(String direccion, String email) {
        this.direccion = direccion;
        this.email = email;
        System.out.println("Información del estudiante actualizada.");
    }

    public void eliminarEstudiante() {
        System.out.println("El estudiante con código " + codigoEstudiante + " ha sido eliminado.");
    }

    public String consultarHistorial() {
        return "Historial académico del estudiante: ";
    }

    public void inscribirCurso(String curso) {
        System.out.println("El estudiante " + nombre + " se inscribió en el curso: " + curso);
    }

    public void cancelarInscripcion(String curso) {
        System.out.println("El estudiante " + nombre + " canceló la inscripción al curso: " + curso);
    }
}