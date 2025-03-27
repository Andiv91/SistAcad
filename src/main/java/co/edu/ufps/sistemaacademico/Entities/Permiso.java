package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion; // Descripción del permiso

    // Métodos
    public void asignarPermiso(String rol) {
        System.out.println("Permiso '" + descripcion + "' asignado al rol: " + rol);
    }

    public void revocarPermiso(String rol) {
        System.out.println("Permiso '" + descripcion + "' revocado del rol: " + rol);
    }
}