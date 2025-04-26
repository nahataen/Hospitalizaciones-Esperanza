package hosp.Escribir;


import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class ConsultarListaDeHabitaciones {
  
    // Método para imprimir texto en azul
    public static void printBlue(String text) {
        System.out.print("\u001B[34m" + text + "\u001B[0m");
    }

    // Método para imprimir texto en verde
    public static void printGreen(String text) {
        System.out.print("\u001B[32m" + text + "\u001B[0m");
    }
    
    // Método para borrar pantalla
    public static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void DesplegarMetodoHabitaciones() {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL = "SELECT * FROM habitaciones";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                printBlue("Número: ");
                printGreen(resultSet.getString("numero"));
                System.out.println();

                printBlue("Nombre: ");
                printGreen(resultSet.getString("nombre"));
                System.out.println();

            
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
