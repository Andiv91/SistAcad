package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especializacion;
    private String email;
    private String telefono;
    private int codigoDocente;

    // Métodos

    public void registrarDocente() {
        System.out.println("Docente registrado: " + this.nombre);
    }

    public void actualizarDocente(String nuevaEspecializacion, String nuevoTelefono) {
        this.especializacion = nuevaEspecializacion;
        this.telefono = nuevoTelefono;
        System.out.println("Datos del docente " + this.nombre + " actualizados.");
    }

    public void eliminarDocente() {
        System.out.println("Docente eliminado: " + this.nombre);
    }

    public void asignarCurso(String nombreCurso) {
        System.out.println("Curso asignado al docente " + this.nombre + ": " + nombreCurso);
    }

    public void removerCurso(String nombreCurso) {
        System.out.println("Curso removido del docente " + this.nombre + ": " + nombreCurso);
    }

    public void crearEvaluacion(String titulo, String curso) {
        System.out.println("Evaluación '" + titulo + "' creada para el curso " + curso + " por el docente " + this.nombre);
    }

    public List<String> consultarCursos() {
        // Ejemplo de cursos retornados lógicamente
        List<String> cursos = new ArrayList<>();
        cursos.add("Curso 1");
        cursos.add("Curso 2");
        cursos.add("Curso 3");
        System.out.println("Consultando cursos asignados al docente " + this.nombre);
        return cursos;
    }
}