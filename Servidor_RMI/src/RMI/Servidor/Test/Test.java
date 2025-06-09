package RMI.Servidor.Test;
import RMI.Servidor.Clase.Consulta;
import RMI.Servidor.Clase.Servidor;
import RMI.Servidor.Clase.Servidor_Implementado;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Test {
    public static void main(String[] args) throws Exception {

        System.setProperty("java.rmi.server.hostname", "host.docker.internal");
        LocateRegistry.createRegistry(2099);
        Servidor_Implementado servidor=new Servidor_Implementado();
        String rmiObjectName="rmi://host.docker.internal:2099/Datos";
        //String rmiObjectName="rmi://172.141.116.88:1099/Datos";
        Naming.rebind(rmiObjectName, servidor);
        System.out.println("Servidor remoto corriendo");
    }
}
