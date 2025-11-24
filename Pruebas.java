import java.sql.Date;
public class Pruebas {
    public static void main(String[] args){
        // Creacion de un objeto de la clase ConexionDB
        //ConexionDB conexion = new ConexionDB();
        //conexion.conectar();

        // Creacion de un objeto por medio de la clase CRUD
        Crud crear = new Crud();
        //crear.crearUsuario("ReoNa", "Mendoza", "Torres", (byte) 33, 23249223,"hola@gmail.com", "Masculino", "https//documento.pdf", "3003367605", Date.valueOf("2004-10-09"), (float)4.8, "[\"Bogota\", \"Medellin\"]");
        //crear.leerDatosUsuario();
        //crear.modificarDatosUsuario("primer_apellido", "Nankatsu", 12);
        crear.eliminarUsuario(14);


    }
}
