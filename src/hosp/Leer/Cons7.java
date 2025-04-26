package hosp.Leer;
import java.sql.*;


public class Cons7 {
  
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

    public void info(String fechaI) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";



        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL =    "SELECT e.folio as Codigo, "+ 
                                    "date_format(e.fecha_de_ingreso, '%d-%m-%y') as Fecha, "+
                                    "concat(IFNULL(concat (p.nombre,' '),' '), IFNULL(concat(p.apellido_paterno,' '),' ') , IFNULL(concat(p.apellido_materno,' '),' '))as 'Nombre del paciente', "+
                                    "e.diagnosticos as Diagnostico, "+
                                    "h.numero as 'Numero de habitaciones', "+
                                    "e.numero_de_cama as 'Numero de cama', "+
                                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico', "+
                                    "es.nombre as Especialidad "+
                                    "FROM expedientes as e "+
                                    "inner join habitaciones as h on h.numero = e.habitacion "+
                                    "inner join pacientes as p on p.numero = e.paciente "+
                                    "inner join expedientes_especialidad as ee on e.folio = ee.expediente "+
                                    "inner join medicos as m on e.medico = m.numero "+
                                    "inner join especialidades as es on ee.especialidad = es.codigo "+
                                    "WHERE e.fecha_de_ingreso = ?";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, fechaI);

            ResultSet resultSet = statement.executeQuery();
            
            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
            if (resultSet.next()) {

                printBlue("La fecha de igreso: ");
                printGreen(resultSet.getString("Fecha"));
                System.out.println();
                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                do{
                                
                printBlue("Codigo de ingreso:         ");
                printGreen(resultSet.getString("Codigo"));
                System.out.println();

                printBlue("Nombre del paciente:       ");
                printGreen(resultSet.getString("Nombre del paciente"));
                System.out.println();

                printBlue("Diagnostico:               ");
                printGreen(resultSet.getString("Diagnostico"));
                System.out.println();

                printBlue("Numero de habitaciones:    ");
                printGreen(resultSet.getString("Numero de habitaciones"));
                System.out.println();

                printBlue("Numero de cama:           ");
                printGreen(resultSet.getString("Numero de cama"));
                System.out.println();

                printBlue("Nombre del médico:         ");
                printGreen(resultSet.getString("Nombre del médico"));
                System.out.println();

                printBlue("Nombre de la especialidad: ");
                printGreen(resultSet.getString("Especialidad"));
                System.out.println();
                
                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                }while(resultSet.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
