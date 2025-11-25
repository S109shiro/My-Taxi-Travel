import java.sql.*;
import java.util.Scanner;

public class Crud {
    // Metodo que crea un registro en la tabla de usuarios pasando parametros al metodo.
    public void crearUsuario(String nombre, String primer_apellido, String segundo_apellido, byte edad, int numero_identificacion, String email, String sexo, String documento_identidad, String numero_telefono, Date fecha_nacimiento){
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
            preparacion.setFloat(11, 0);
            preparacion.setString(12, "[]");
            preparacion.executeUpdate();

            ResultSet obtenerId = preparacion.executeQuery("select id_usuario from usuario where numero_identificacion = " + numero_identificacion);
            while (obtenerId.next()){
                System.out.println(String.format("Datos Insertados con el ID: %d \n", obtenerId.getInt("id_usuario")));
            }
        }catch (SQLException ex){
            System.err.println("Error al insertar datos");
            ex.printStackTrace();
        }
    }

    // Metodo que consulta todos los registros de la tabla usuario.
    public void leerDatosUsuario(){
        // Definicion de la salida y query de consulta
        String salida = "ID: %d, Nombre: %s, Primer apellido: %s, Segundo apellido: %s, Edad: %d, Numero identificacion: %d, Email: %s, Sexo: %s, Documento de identidad: %s, Numero de telefono: %s, Fecha de nacimiento: %s, Calificacion media: %f, Historial de viajes: %s \n";
        String query = "select * from usuario";
        try{
            Connection conexion = ConexionDB.conectar();
            PreparedStatement preparacion = conexion.prepareStatement(query);
            ResultSet resultado = preparacion.executeQuery();

            if(!resultado.next()){
                System.err.print("No hay datos que mostrar en la tabla usuario");
            }
            else {
                // Obtenemos los datos del query con el resultado
                do{
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
                    // Salida de cada dato por consola
                    System.out.print(String.format(salida, id, nombre, primer_apellido, segundo_apellido, edad, numero_identificacion, email, sexo, documento_identidad, numero_telefono, fecha_nacimiento, calificacion_media , historial_viajes));
                }
                while(resultado.next()); // True si existen datos
                System.out.println(); // Espacio
            }
        }catch (SQLException ex){
            System.err.println("Error al mostrar los datos");
            ex.printStackTrace();
        }
    }

    // Metodo que realiza actualizaciones a la table usuario e indica si hubo un cambio o no
    public void modificarDatosUsuario(String columna_actualizar, String nuevo_valor, int id){
        String query = "Update usuario set " + columna_actualizar + " = ? where id_usuario = ?";
        byte changes;
        try{
            Connection conexion = ConexionDB.conectar();
            PreparedStatement preparacion = conexion.prepareStatement(query);
            preparacion.setString(1, nuevo_valor);
            preparacion.setInt(2, id);

            changes = (byte) preparacion.executeUpdate();
            System.out.println(String.format("Se han realizado cambios a: %d fila \n", changes));

        } catch (SQLException e) {
            System.err.println("Error al actualizar los datos");
            e.printStackTrace();
        }

    }

    // Metodo para eliminar a un usuario de la tabla por medio de su id
    public void eliminarUsuario(int id){
        // Establecemos dos querys
        String query = "delete from usuario where id_usuario = ?";
        String queryValidacionID = "select * from usuario where id_usuario = ?";
        // Definicion de una variable para la confirmacion del delete del usuario
        char decision;
        try{
            Connection conexion = ConexionDB.conectar();

            // Validacion del id en la tabla usuarios
            PreparedStatement validacionID = conexion.prepareStatement(queryValidacionID);
            validacionID.setInt(1, id);
            ResultSet existenciaId = validacionID.executeQuery();

            // Si no existe un usuario cn ese ID entra aca
            if(!existenciaId.next()){
                System.out.println("Error. Ese registro no existe en esta tabla.\n");
            }
            // Caso contrario entra en este bloque
            else{
                // Obtencion del id existente en la tabla
                PreparedStatement preparar = conexion.prepareStatement(query);
                preparar.setInt(1, id);

                while(true){
                    // Obtenemos confirmacion por teclado
                    Scanner opcion = new Scanner(System.in);
                    System.out.print(String.format("¿Estas seguro de eliminar a %s relacionado con el id %d? (y/n): ", existenciaId.getString("nombre"), id));
                    decision = Character.toLowerCase(opcion.next().charAt(0));  // Transformar en minusculas

                    //Confirmamos si quiere eliminar el usuario
                    if(decision == 'y'){
                        System.out.println(String.format("Se han eliminado: %d registros\n", preparar.executeUpdate()));
                        break;
                    }else if(decision == 'n'){
                        System.out.println("Datos no eliminados\n");
                        break;
                    }
                    else{
                        System.err.println("Ingresa una opcion valida.");
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
