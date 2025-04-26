package hosp.Leer;
import java.sql.*;


public class Cons8 {
  
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

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL = "SELECT e.folio as Codigo, "+
                    "concat(IFNULL(concat (p.nombre,' '),' '), IFNULL(concat(p.apellido_paterno,' '),' ') , IFNULL(concat(p.apellido_materno,' '),' ')) as 'Nombre del paciente', "+
                    "date_format(p.fecha_de_nacimiento , '%d-%m-%y') as 'Fecha de nacimiento', "+
                    "e.edad as Edad, "+
                    "date_format(e.fecha_de_ingreso , '%d-%m-%y') as 'Fecha de ingreso', "+
                    "e.sintomas as Sintomas, "+
                    "e.diagnosticos as Diagnosticos, "+
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) as 'Nombre del médico', "+
                    "es.nombre as Especialidad "+
                    "FROM expedientes as e "+
                    "inner join habitaciones as h on h.numero = e.habitacion "+
                    "inner join pacientes as p on p.numero = e.paciente "+
                    "inner join expedientes_especialidad as ee on e.folio = ee.expediente "+
                    "inner join medicos as m on e.medico = m.numero "+
                    "inner join especialidades as es on ee.especialidad = es.codigo "+
                    "WHERE e.edad<13 and e.fecha_de_alta is null";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);


            ResultSet resultSet = statement.executeQuery();

            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

            while (resultSet.next()) {

                printBlue("Codigo:               ");
                printGreen(resultSet.getString("Codigo"));
                System.out.println();

                printBlue("Nombre del paciente: ");
                printGreen(resultSet.getString("Nombre del paciente"));
                System.out.println();

                printBlue("Fecha de nacimiento: ");
                printGreen(resultSet.getString("Fecha de nacimiento"));
                System.out.println();

                printBlue("Edad:                ");
                printGreen(String.valueOf(resultSet.getInt("Edad")));
                System.out.println();

                printBlue("Fecha de ingreso:    ");
                printGreen(resultSet.getString("Fecha de ingreso"));
                System.out.println();

                printBlue("Sintomas:            ");
                printGreen(resultSet.getString("Sintomas"));
                System.out.println();

                printBlue("Diagnosticos:        ");
                printGreen(resultSet.getString("Diagnosticos"));
                System.out.println();

                printBlue("Nombre del médico:    ");
                printGreen(resultSet.getString("Nombre del médico"));
                System.out.println();

                printBlue("Especialidad:         ");
                printGreen(resultSet.getString("Especialidad"));
                System.out.println();

                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
