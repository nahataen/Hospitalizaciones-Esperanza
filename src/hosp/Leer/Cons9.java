package hosp.Leer;
import java.sql.*;

public class Cons9 {
  
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

    public void info() {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";

       // colores color = new colores();
  

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL =     "SELECT CONCAT(IFNULL(CONCAT(m.nombre_doctor, ' '), ' '), " +
            "IFNULL(CONCAT(m.apellidoP, ' '), ' '), " +
            "IFNULL(CONCAT(m.apellidoM, ' '), ' ')) AS 'Nombre medico', " +
            "COUNT(e.paciente) AS 'Pacientes atendidos' " +
            "FROM medicos AS m " +
            "INNER JOIN expedientes AS e ON m.numero = e.medico " +
            "GROUP BY m.numero";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        
            ResultSet resultSet = statement.executeQuery();
                
            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

            while (resultSet.next()) {

                printBlue("Nombre medico:       ");
                printGreen(resultSet.getString("Nombre medico"));
                System.out.println();

                printBlue("Pacientes atendidos: ");
                printGreen(resultSet.getString("Pacientes atendidos"));
                System.out.println();

                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
