package hosp.Leer;
//import hosp.colores.colores;
import java.sql.*;
//import java.util.Scanner;

public class Cons5 {
  
    // Método para imprimir texto en azul
    public static void printBlue(String text) {
        System.out.print("\u001B[34m" + text + "\u001B[0m");
    }

    // Método para imprimir texto en verde
    public static void printGreen(String text) {
        System.out.print("\u001B[32m" + text + "\u001B[0m");
    }
    //Método para borrar pantalla
    public static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void info(String numeroMedico) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";

       // colores color = new colores();
  

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL =    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico', "+
                                    "es.nombre AS 'Nombre especialidad' "+
                                    "FROM medicos AS m "+
                                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                                    "WHERE m.numero = ?";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, numeroMedico);
            ResultSet resultSet = statement.executeQuery();
            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

            if(resultSet.next()){
                printBlue("Nombre del medico:  ");
                printGreen(resultSet.getString("Nombre del médico"));
                System.out.println();
                
                do{
                
                printBlue("Especialidad:       ");
                printGreen(resultSet.getString("Nombre especialidad"));
                System.out.println();

                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
            }while(resultSet.next()); 

            }else{System.out.println("No se encontro al medico");}

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
