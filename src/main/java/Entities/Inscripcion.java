package Entities;
import java.util.*;
class Inscripcion {
    Date fechaInscripcion;
    String estado;

    boolean validarPrerequisitos(Estudiante estudiante, Curso curso) {
        return true;
    }

    void cancelarInscripcion() {}
}
