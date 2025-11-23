import java.sql.Date;
public class Pruebas {
    public static void main(String[] args){
        // Creacion de un objeto de la clase ConexionDB
        //ConexionDB conexion = new ConexionDB();
        //conexion.conectar();

        // Creacion de un objeto por medio de la clase CRUD
        Crud crear = new Crud();
        //crear.crearUsuario("Reo", "Gonzalez", "Rodriguez", (byte) 21, 20879223,"shola@gmail.com", "Femboy", "https//documento.pdf", "3003367605", Date.valueOf("2004-10-09"), (float)4.8, "[\"Bogota\", \"Medellin\"]");
        crear.leerDatosUsuario(2);

    }
}
