package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float valor; // Valor numérico de la calificación
    private String comentario; // Comentario sobre la calificación

    // Métodos

    public void registrarCalificacion(float valor, String comentario) {
        this.valor = valor;
        this.comentario = comentario;
        System.out.println("Calificación registrada: Valor = " + valor + ", Comentario = " + comentario);
    }

    public void consultarCalificacion() {
        System.out.println("Calificación consultada: Valor = " + valor + ", Comentario = " + comentario);
    }

    public void modificarCalificacion(float nuevoValor, String nuevoComentario) {
        this.valor = nuevoValor;
        this.comentario = nuevoComentario;
        System.out.println("Calificación modificada: Nuevo Valor = " + nuevoValor + ", Nuevo Comentario = " + nuevoComentario);
    }

    public boolean apruebaONoAprueba(float evaluacionValor) {
        boolean aprueba = this.valor >= evaluacionValor;
        System.out.println("¿Aprueba? " + aprueba);
        return aprueba;
    }
}