package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int codigoRol;

    // Métodos

    public void asignarPermisos(List<String> permisos) {
        System.out.println("Permisos asignados al rol: " + nombre + ". Permisos: " + permisos);
    }

    public void eliminarRol() {
        System.out.println("El rol " + nombre + " ha sido eliminado.");
    }

    public List<String> consultarPermisos() {
        // Ejemplo de permisos consultados (esto puede ser ajustado según la lógica real)
        List<String> permisos = new ArrayList<>();
        permisos.add("PERMISO_LECTURA");
        permisos.add("PERMISO_ESCRITURA");
        permisos.add("PERMISO_ADMINISTRACION");
        System.out.println("Consultando permisos del rol: " + nombre);
        return permisos;
    }
}