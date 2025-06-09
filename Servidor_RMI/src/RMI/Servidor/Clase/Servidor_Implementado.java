package RMI.Servidor.Clase;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
public class Servidor_Implementado extends UnicastRemoteObject implements Servidor{
        public Servidor_Implementado() throws Exception {
            super();
        }
        @Override
        public String consultar(String correo) throws Exception {
            Usuario usuario=Consulta.buscarCorreo(correo);
            return (usuario!=null)? usuario.toString(): "Usuario con correo " + correo + " no encontrado.";
        }

        @Override
        public boolean agregar(Usuario usuario) {
            return Consulta.insertar(usuario);
        }
        @Override
        public boolean editar(Usuario usuario) {
            return Consulta.actualizar(usuario);
        }
        @Override
        public boolean eliminar(int id) {
            return Consulta.eliminar(id);
        }

    @Override
    public ArrayList<Usuario> listarTodos() {
        return Consulta.getPersonas();
    }
}










    /*private static ArrayList<Persona> listaPersonas(){
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona(1, "Juan Perez", "jp@empresa.com", "administrador", 2500));
        listaPersonas.add(new Persona(2, "Luis Medina", "lm@empresa.com", "técnico", 1500));
        listaPersonas.add(new Persona(3, "Jorge Yánez", "jy@empresa.com", "jefe de sistemas", 2000));
        listaPersonas.add(new Persona(4, "Jorge Flores", "jf@empresa.com", "técnico", 1500));
        listaPersonas.add(new Persona(5, "Pedro Álvarez", "pa@empresa.com", "colaborador", 900));
        listaPersonas.add(new Persona(6, "Carlos López", "cl@empresa.com", "colaborador", 900));
        listaPersonas.add(new Persona(7, "Silvia Muñoz", "sm@empresa.com", "secretaria", 800));
        return listaPersonas;
    }
    private static String getPersona(int id){
        return
                "Nombre: "+listaPersonas().get(id-1).getNombre()+"\n"
                +"Correo: "+listaPersonas().get(id-1).getCorreo()+"\n"
                +"Cargo: "+listaPersonas().get(id-1).getCargo()+"\n"
                +"Sueldo: "+listaPersonas().get(id-1).getSueldo();
    }
    public Servidor_Implementado() throws RemoteException {
        super();
    }
    @Override
    public String consultar(int id) throws Exception {
        if (id<listaPersonas().size()+1){
            return getPersona(id);
        } else{
            return "No existen datos del empleado";
        }
    }*/
