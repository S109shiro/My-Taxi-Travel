import java.sql.*;
public class Crud {
    // Crea un registro en la tabla de usuarios pasando parametros al metodo.
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

    // Consulta un registro de un usuario por medio de un ID pasado por parametro.
    public void leerDatosUsuario(int id_usuario){
        String salida = "ID: %d, Nombre: %s, Primer apellido: %s, Segundo apellido: %s, Edad: %d, Numero identificacion: %d, Email: %s, Sexo: %s, Documento de identidad: %s, Numero de telefono: %s, Fecha de nacimiento: %s, Calificacion media: %f, Historial de viajes: %s";
        String query = "select * from usuario where id_usuario =" + id_usuario;
        boolean exist = false;
        try{
            Connection conexion = ConexionDB.conectar();
            PreparedStatement preparacion = conexion.prepareStatement(query);
            ResultSet resultado = preparacion.executeQuery();

            while(resultado.next()){
                exist = true;
                int id = resultado.getInt("id_usuario");
                String nombre = resultado.getString("nombre");
                String primer_apellido = resultado.getString("primer_apellido");
                String segundo_apellido = resultado.getString("segundo_apellido");
                byte edad = resultado.getByte("edad");
                int numero_identificacion = resultado.getInt("numero_identificacion");
                String email = resultado.getString("email");
                String sexo = resultado.getString("sexo");
                String documento_identidad = resultado.getString("documento_identidad");
                String numero_telefono = resultado.getString("numero_telefono");
                String fecha_nacimiento = resultado.getString("fecha_nacimiento");
                float calificacion_media = resultado.getFloat("calificacion_media");
                String historial_viajes = resultado.getString("historial_viajes");

                System.out.println(String.format(salida, id, nombre, primer_apellido, segundo_apellido, edad, numero_identificacion, email, sexo, documento_identidad, numero_telefono, fecha_nacimiento, calificacion_media , historial_viajes));
            }
            if(!exist){
                System.err.println("No existe un usuario con ese ID");
            }
        }catch (SQLException ex){
            System.err.println("Error al mostrar los datos");
            ex.printStackTrace();
        }
    }
}
