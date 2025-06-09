package RMICliente.Cliente;

import RMI.Servidor.Clase.Servidor;
import RMI.Servidor.Clase.Usuario;

import java.rmi.Naming;
import java.util.ArrayList;

public class Cliente {

    // Obtener la referencia al servicio RMI
    private static Servidor getServicio() throws Exception {
        String rmiObjectName = "rmi://172.31.116.88:1099/Datos";
        return (Servidor) Naming.lookup(rmiObjectName);
    }

    // Consultar un usuario por ID
    public static String consultar(String correo) throws Exception {
        return getServicio().consultar(correo);
    }

    // Listar todos los usuarios
    public static ArrayList<Usuario> listarTodos() throws Exception {
        return getServicio().listarTodos();
    }

    // Actualizar los datos de un usuario
    public static boolean editar(int id, String nombre, String correo) throws Exception {
        Usuario us = new Usuario(id, nombre, correo);
        return getServicio().editar(us);
    }

    // Eliminar un usuario por ID
    public static boolean eliminar(int id) throws Exception {
        return getServicio().eliminar(id);
    }

    // Agregar un nuevo usuario
    public static boolean agregar(int id, String nombre, String correo) throws Exception {
        Usuario us = new Usuario(id, nombre, correo);
        return getServicio().agregar(us);
    }
}