package RMI.Servidor.Clase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Usuario implements Serializable {
    @Getter @Setter private int clave;
    @Getter @Setter private String nombre;
    @Getter @Setter private String correo;
    //@Getter @Setter private String cargo;
    //@Getter @Setter private double sueldo;
    public Usuario(int clave, String nombre, String correo) {
        this.clave = clave;
        this.nombre = nombre;
        this.correo = correo;
        //this.cargo = cargo;
        //this.sueldo = sueldo;
    }
    @Override
    public String toString() {
        return
                "ID: " + clave +
                ", Nombre: " + nombre +
                ", Correo: " + correo;
    }
}
