package RMI.Servidor.Clase;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
public class Consulta{
    private static final String rutaBDD="C:/Users/APP DISTRIBUIDAS/IdeaProjects/Servidor_RMI/usuarios.db";
    private static Connection conectar() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("No se pudo cargar el driver JDBC de SQLite");
        }
        return DriverManager.getConnection("jdbc:sqlite:"+rutaBDD);
    }
    //obtener todos los usuarios
    public static ArrayList<Usuario> getPersonas() {
        ArrayList<Usuario> lista = new ArrayList<>();
        //Connection conn = null;
        //Statement stmt = null;
        //ResultSet rs = null;
        try (Connection connection=conectar();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT id, nombre, correo FROM usuarios")){
            while (resultSet.next()) {
                lista.add(new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("correo")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    //obtener usuario por correo

    public static Usuario buscarCorreo(String correo){
        try(Connection connection=conectar();
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM usuarios WHERE correo=?")){
            statement.setString(1, correo);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                return new Usuario(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("correo"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //agregar un nuevo usuario a la base de datos

    private static String generarUsername() {
        String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder username = new StringBuilder("user");

        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * caracteres.length());
            username.append(caracteres.charAt(index));
        }

        return username.toString();
    }



    public static boolean insertar(Usuario usuario) {
        String username=generarUsername();
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios (id, nombre, correo, username) VALUES (?, ?, ?, ?)")) {
            stmt.setInt(1, usuario.getClave());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, username);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




    //actualizar un usuario de la base de datos
    public static boolean actualizar(Usuario usuario) {
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement("UPDATE usuarios SET nombre=?, correo=? WHERE id=?")) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());

            stmt.setInt(5, usuario.getClave());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //eliminar un usuario de la base de datos
    public static boolean eliminar(int id) {
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
