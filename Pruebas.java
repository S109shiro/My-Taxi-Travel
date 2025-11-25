import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Pruebas {
    public static void main(String[] args){
        // Creacion de un objeto por medio de la clase CRUD
        Crud manipulacion = new Crud();

        // Inicio de la interfaz por consola para controlar el CRUD
        System.out.println("Bienvenido al sistema de gestion de My Taxi Travel - Modo Administrador \n¿Que desea realizar?");
        while(true){
            byte opcion;
            System.out.println("1. Crear un nuevo usuario \n2. Ver los registros ya ingresados \n3. Modificar los registros de un usuario \n4. Eliminar un usuario \n0. Salir del programa");
            Scanner num = new Scanner(System.in);
            opcion = num.nextByte();
            // Toma de decisiones por parte del Administrador
            if(opcion == 0){
                System.out.println("Gracias por usar este sistema \n¡Hasta luego!");
                break;
            }
            // Creacion de un usuario
            else if(opcion == 1){
                String nombre, primer_apellido, segundo_apellido, email, sexo, documento_identidad, numero_telefono;
                byte edad;
                int numero_identificacion;
                Date fecha_nacimientoSQL;

                // Obtener nombre del nuevo usuario
                System.out.println("Estas a punto de crear un nuevo usuario. \nIngresa el nombre: ");
                Scanner nm = new Scanner(System.in);
                nombre = nm.next();

                //Obtener apellidos del nuevo usuario
                System.out.println("Ingresa el primer apellido: ");
                Scanner ap1 = new Scanner(System.in);
                primer_apellido = ap1.next();

                System.out.println("Ingresa el segundo apellido: ");
                Scanner ap2 = new Scanner(System.in);
                segundo_apellido = ap2.nextLine();

                // Obtener la edad del nuevo usuario
                System.out.println("Ingresa la edad: ");
                Scanner age = new Scanner(System.in);
                edad = age.nextByte();

                // Obtener numero de identificacion del nuevo usuario
                System.out.println("Ingresa el numero de identificacion: ");
                Scanner nid = new Scanner(System.in);
                numero_identificacion = nid.nextInt();

                // Obtener el email del nuevo usuario
                System.out.println("Ingrese el email: ");
                Scanner mail = new Scanner(System.in);
                email = mail.next();

                // Obtener sexo del nuevo usuario
                System.out.println("Ingrese su respectivo sexo: ");
                Scanner sx = new Scanner(System.in);
                sexo = sx.next();

                // Obtener url del documento de identidad del nuevo usuario
                System.out.println("Ingrese el adjunto de su documento de identidad (url): ");
                Scanner doc = new Scanner(System.in);
                documento_identidad = doc.next();

                // Obtener numero de telefono del nuevo usuario
                System.out.println("Ingrese su numero de telefono: ");
                Scanner tel = new Scanner(System.in);
                numero_telefono = tel.next();

                // Obtener fecha de nacimiento del nuevo usuario, convertirla a sql
                System.out.println("Ingrese su fecha de nacimiento (AAAA-MM-DD): ");
                Scanner fn = new Scanner(System.in);
                try{
                    LocalDate fechaSimple = LocalDate.parse(fn.next());
                    fecha_nacimientoSQL = Date.valueOf(fechaSimple);
                    manipulacion.crearUsuario(nombre, primer_apellido, segundo_apellido, edad, numero_identificacion, email, sexo, documento_identidad, numero_telefono, fecha_nacimientoSQL);
                }catch (DateTimeParseException e){
                    System.out.println("Error: Formato inválido (use AAAA-MM-DD).");
                }
                
            }
            // Lectura de los registros
            else if (opcion == 2) {
                manipulacion.leerDatosUsuario();
            }
            // Modificar un registro usuario
            else if (opcion == 3) {
                int id;
                String columna;

                System.out.print("Ingrese el id del registro que desea editar: ");
                Scanner idU = new Scanner(System.in);
                id = idU.nextInt();

                System.out.println("Escriba que atributo desea modificar. Recuerde que solo puede editar los siguientes campos: \n(Nombre, Primer o segundo apellido, Edad, Numero identificacion, Email, Sexo, Documento identidad, Numero telefono o Fecha nacimiento)");
                Scanner col = new Scanner(System.in);
                columna = col.nextLine().toLowerCase().replace(" ", "_").trim();  // Tratamiento del input para que concuerde con la bd

                System.out.print(String.format("Ingresa el nuevo valor para %s del id %d: ", columna, id));
                Scanner nv = new Scanner(System.in);
                String nuevoValor = nv.nextLine();
                manipulacion.modificarDatosUsuario(columna, nuevoValor, id);
            }
            // Eliminar un usuario
            else if(opcion == 4){
                int id;
                System.out.println("Ingrese el id del registro que desea eliminar: ");
                Scanner del = new Scanner(System.in);
                id = del.nextInt();
                manipulacion.eliminarUsuario(id);
            }
            else {
                System.out.println("Ingresaste una opcion invalida. Vuelve a intentarlo.");
            }

        }

    }
}
