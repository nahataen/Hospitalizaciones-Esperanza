package hosp.Leer;
//import hosp.colores.colores;
import java.sql.*;
//import java.util.Scanner;

public class Cons3 {
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

    public void info(String numeroPaciente) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";
        boolean validacion = false;

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL  =   "SELECT e.folio  as 'Codigo de ingreso',"+
                                    "concat(IFNULL(concat (p.nombre,' '),' '), IFNULL(concat(p.apellido_paterno,' '),' ') , IFNULL(concat(p.apellido_materno,' '),' ')) as 'Nombre del paciente', "+
                                    "date_format(e.fecha_de_ingreso, '%d-%m-%y')"+ "as 'fecha de ingreso', "+
                                    "e.diagnosticos as Diagnostico,"+ "h.numero"+" as 'numero de habitacion', "+
                                    "e.numero_de_cama "+
                                    "FROM expedientes as e "+
                                    "inner join pacientes as p on p.numero = e.paciente "+
                                    "inner join habitaciones as h on h.numero = e.habitacion "+
                                    "WHERE h.numero = ?";


            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            
            statement.setString(1, numeroPaciente);

            ResultSet resultSet = statement.executeQuery();

            
            while (resultSet.next()) {
                validacion = true;
                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                printBlue("Codigo de ingeso:      ");
                printGreen(String.valueOf(resultSet.getInt("Codigo de ingreso")));
                System.out.println();

                printBlue("Nombre del paciente:   ");
                printGreen(resultSet.getString("Nombre del paciente"));
                System.out.println();

                printBlue("Fecha de ingreso:     ");
                printGreen(resultSet.getString("Fecha de ingreso").toString());
                System.out.println();
               
                printBlue("Diagnostico:          ");
                printGreen(String.valueOf(resultSet.getString("Diagnostico")));
                System.out.println();

                
                printBlue("Numero de habitacion: ");
                printGreen(String.valueOf(resultSet.getInt("Numero de habitacion")));
                System.out.println();

                printBlue("Numero de cama:       ");
                printGreen(String.valueOf(resultSet.getInt("numero_de_cama")));
                System.out.println();

                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                }
            
                if(validacion==false){
                    System.out.println("No se encontró de la habitacion.");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
