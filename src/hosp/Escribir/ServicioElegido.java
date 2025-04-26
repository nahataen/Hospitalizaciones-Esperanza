package hosp.Escribir;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class ServicioElegido {
  
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

    public void consultarServicioPorNumero(int servicioElegido) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            // Obtener el máximo número de servicios
            String maxNumeroSQL = "SELECT MAX(numero) AS max_numero FROM servicios";
            PreparedStatement maxNumeroStmt = conexion.prepareStatement(maxNumeroSQL);
            ResultSet maxNumeroResult = maxNumeroStmt.executeQuery();
            int maxNumero = 0;
            if (maxNumeroResult.next()) {
                maxNumero = maxNumeroResult.getInt("max_numero");
            }
            
            if (servicioElegido <= maxNumero) {
                String consultaSQL = "SELECT numero, nombre, descripcion FROM servicios WHERE numero = ?";
                PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                statement.setInt(1, servicioElegido);

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    printBlue("Número: ");
                    printGreen(resultSet.getString("numero"));
                    System.out.println();

                    printBlue("Nombre: ");
                    printGreen(resultSet.getString("nombre"));
                    System.out.println();

                    printBlue("Descripción: ");
                    printGreen(resultSet.getString("descripcion"));
                    System.out.println();

                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("El número ingresado es mayor al máximo de servicios.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
