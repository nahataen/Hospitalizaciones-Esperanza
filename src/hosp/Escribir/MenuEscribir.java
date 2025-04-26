package hosp.Escribir;
/*import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException; */
import java.sql.*;
import java.util.Scanner;

import hosp.MenuMain;

public class MenuEscribir {
    // Variables
    String nombre;
    String apellidoP;
    String apellidoM;
    String telefono;
    String correo;
    int opc;

    // Creación de objeto para inserción de datos
    insertar1 Datos_paciente = new insertar1();

    // Conexión a la base de datos
    private String url = "jdbc:mysql://localhost:3306/hospitalizaciones?useUnicode";
    private String usuario = "root";
    private String contrasena = "";

    Scanner scan = new Scanner(System.in);



MetodosPacienteInsert Met= new MetodosPacienteInsert();

    public void insersion() {
        
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("\u001B[35m    |-------------------------------------------|" + "\u001B[0m");
            System.out.println("\u001B[35m    |" + "\u001B[32m    Ingresa qué registro quieres hacer     " + "\u001B[0m" + "\u001B[35m|" + "\u001B[0m");
            System.out.println("\u001B[35m    |-------------------------------------------|" + "\u001B[0m");
            System.out.println("\u001B[35m    |" + "\u001B[0m" + "\u001B[32m    [1] " + "\u001B[0m" + "\u001B[34m" + "Pacientes                          " + "\u001B[0m" + "\u001B[35m|" + "\u001B[0m");
            System.out.println("\u001B[35m    |" + "\u001B[0m" + "\u001B[32m    [2] " + "\u001B[0m" + "\u001B[34m" + "Otra opción                        " + "\u001B[0m" + "\u001B[35m|" + "\u001B[0m");
            System.out.println("\u001B[35m    |-------------------------------------------|" + "\u001B[0m");
            System.out.print("\u001B[33m" + "    | Seleccione un numero:");

            String input = scan.nextLine().trim(); // Leer la línea y eliminar espacios en blanco

            // Verifica si la entrada es un número
            if (!input.isEmpty() && input.matches("\\d{1,10}")) {
                opc = Integer.parseInt(input);

                // Verifica si el número es 1 o 2
                if (opc == 1 || opc == 2) {
                    break; // Sale del bucle si el número es válido
                } else {
                    System.out.println("    | ¡Opción inválida! Ingrese 1 o 2.");
                }
            } else {
                System.out.println("    | ¡Entrada inválida! Ingrese un número válido de hasta 10 dígitos.");
            }
        } while (true);
    

        switch (opc) {
            case 1:
                Met.registrarPaciente(); //trae el metodo que da insersion a paciente
               
                break;

            //segundo caso
            case 2:
                

                break;

        }
    }

}
