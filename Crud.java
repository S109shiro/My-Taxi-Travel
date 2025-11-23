import java.sql.*;
public class Crud {
    public void crearUsuario(String nombre, String primer_apellido, String segundo_apellido, byte edad, int numero_identificacion, String email, String sexo, String documento_identidad, String numero_telefono, java.sql.Date fecha_nacimiento, float calificacion_media, String historial_viajes){
        String query = "insert into usuario (nombre, primer_apellido, segundo_apellido, edad, numero_identificacion, email, sexo, documento_identidad, numero_telefono, fecha_nacimiento, calificacion_media, historial_viajes) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection conexion = ConexionDB.conectar();
            PreparedStatement preparacion = conexion.prepareStatement(query);
            preparacion.setString(1, nombre);
            preparacion.setString(2, primer_apellido);
            preparacion.setString(3, segundo_apellido);
            preparacion.setByte(4, edad);
            preparacion.setInt(5, numero_identificacion);
            preparacion.setString(6, email);
            preparacion.setString(7, sexo);
            preparacion.setString(8, documento_identidad);
            preparacion.setString(9, numero_telefono);
            preparacion.setDate(10, fecha_nacimiento);
            preparacion.setFloat(11, calificacion_media);
            preparacion.setString(12, historial_viajes);
            preparacion.executeUpdate();
            System.out.println("Datos Insertados");
        }catch (SQLException ex){
            System.err.println("Error al insertar datos");
            ex.printStackTrace();
        }
    }

    public void modificarUsuario(){

    }
}
