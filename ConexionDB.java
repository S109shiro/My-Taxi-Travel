import java.sql.*;
public class ConexionDB {
    // Se agrega el static para que sean variables de la clase solamente
    private static String url = "jdbc:mysql://localhost:3306/my_taxi_travel";
    private static String user = "root";
    private static String pass = "2004248";

    // Creamos un metodo para conectarnos a la db
    public static Connection conectar(){
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("La conexion ha sido exitosa");
        }catch (SQLException e){
            // Nos ayuda a ver los registros donde le programa tuvo un error.
            e.printStackTrace();
            System.err.println("Hubo un error en la conexion");
        }
        return conexion;
    }

}
