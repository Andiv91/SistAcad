package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estadisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float promedioGeneral;
    private float asistenciaPromedio;

    private String datos; // Atributo que almacena los datos estadísticos relevantes

    // Métodos

    public float calcularPromedio(float[] calificaciones) {
        float suma = 0;
        for (float calificacion : calificaciones) {
            suma += calificacion;
        }
        this.promedioGeneral = suma / calificaciones.length;
        System.out.println("Promedio calculado: " + this.promedioGeneral);
        return this.promedioGeneral;
    }

    public void generarEstadisticas(float[] asistencias, float[] calificaciones) {
        this.promedioGeneral = calcularPromedio(calificaciones);
        this.asistenciaPromedio = calcularPromedio(asistencias);
        this.datos = "Estadísticas generadas: Promedio General: " + this.promedioGeneral +
                ", Asistencia Promedio: " + this.asistenciaPromedio;
        System.out.println(this.datos);
    }
}