package co.edu.ufps.sistemaacademico.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String contrasena;
    private String tipoIdentificacion;
    private double numIdentificacion;

    // Métodos

    // Validar las credenciales del usuario
    public boolean autenticarUsuario(String email, String contrasena) {
        return this.email.equals(email) && this.contrasena.equals(contrasena);
    }

    // Recuperar o restablecer la contraseña
    public void recuperarContrasena(String nuevaContrasena) {
        this.contrasena = nuevaContrasena;
    }

    // Asignar un rol al usuario
    public void asignarRol(String rol) {
        System.out.println("Rol " + rol + " asignado al usuario: " + this.nombre);
    }

    // Agregar un usuario (Lógica específica de tu caso de uso)
    public void agregarUsuario() {
        System.out.println("Usuario " + this.nombre + " agregado.");
    }

    // Eliminar un usuario (Lógica específica de tu caso de uso)
    public void eliminarUsuario() {
        System.out.println("Usuario " + this.nombre + " eliminado.");
    }
}