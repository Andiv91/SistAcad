package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCurso;
    private int creditos;
    private String horario;
    private double codigoCurso;
    private int cuposDisponibles;

    private Long docenteId; // Relación lógica con el Docente a través del ID

    // Métodos
    public void crearCurso() {
        System.out.println("Curso creado: " + nombreCurso);
    }

    public void modificarCurso(String nuevoHorario, int nuevosCupos) {
        this.horario = nuevoHorario;
        this.cuposDisponibles = nuevosCupos;
        System.out.println("Curso modificado: " + nombreCurso);
    }

    public void eliminarCurso() {
        System.out.println("Curso eliminado: " + nombreCurso);
    }

    public void asignarDocente(Long docenteId) {
        this.docenteId = docenteId;
        System.out.println("Docente con ID " + docenteId + " asignado al curso: " + nombreCurso);
    }

    public void removerDocente() {
        this.docenteId = null;
        System.out.println("Docente removido del curso: " + nombreCurso);
    }
}