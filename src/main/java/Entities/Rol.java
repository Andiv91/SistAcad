package Entities;
import java.util.*;
class Rol {
    String nombre;
    int codigoRol;
    List<Permiso> permisos = new ArrayList<>();

    void asignarPermiso(Permiso permiso) {
        permisos.add(permiso);
    }

    void eliminarRol() {
        // Lógica para eliminar el rol
    }
}
