package RMI.Servidor.Clase;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
public interface Servidor extends Remote {

    public String consultar(String correo) throws Exception;
    ArrayList <Usuario> listarTodos() throws RemoteException;
    boolean agregar(Usuario usuario) throws RemoteException;
    boolean editar (Usuario usuario) throws RemoteException;
    boolean eliminar (int id) throws RemoteException;
}
