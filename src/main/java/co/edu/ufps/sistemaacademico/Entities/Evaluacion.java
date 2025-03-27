package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo; // Puede ser "examen", "trabajo" o "práctica"
    private Date fecha;

    // Métodos

    public void crearEvaluacion() {
        System.out.println("Evaluación creada con tipo: " + tipo + " y fecha: " + fecha);
    }

    public void modificarEvaluacion(String nuevoTipo, Date nuevaFecha) {
        this.tipo = nuevoTipo;
        this.fecha = nuevaFecha;
        System.out.println("Evaluación modificada. Nuevo tipo: " + tipo + ", nueva fecha: " + fecha);
    }

    public void eliminarEvaluacion() {
        System.out.println("Evaluación eliminada con tipo: " + tipo + " y fecha: " + fecha);
    }

    public void registrarCalificaciones() {
        System.out.println("Calificaciones registradas para la evaluación tipo: " + tipo);
    }

    public List<String> consultarResultados() {
        // Simulación de resultados (esto puede ser ajustado según tu lógica)
        List<String> resultados = new ArrayList<>();
        resultados.add("Calificación 1: 85");
        resultados.add("Calificación 2: 90");
        resultados.add("Calificación 3: 78");
        System.out.println("Resultados consultados para la evaluación tipo: " + tipo);
        return resultados;
    }
}