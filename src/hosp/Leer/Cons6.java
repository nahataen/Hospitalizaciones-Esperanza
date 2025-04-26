package hosp.Leer;
import java.sql.*;


public class Cons6 {
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
     
        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL  =   "SELECT concat(IFNULL(concat (p.nombre,' '),' '), IFNULL(concat(p.apellido_paterno,' '),' ') , IFNULL(concat(p.apellido_materno,' '),' ')) as 'Nombre del paciente', "+
                                    "e.folio as 'Codigo de ingreso', "+
                                    "e.sintomas as Sintoma, "+
                                    "e.diagnosticos as Diagnostico, "+
                                    "es.nombre as Especialidad, "+
                                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) as Medico, "+
                                    "date_format(e.fecha_de_ingreso, '%d-%m-%y') as 'fecha de ingreso', "+
                                    "IFNULL (date_format(e.fecha_de_alta, '%d-%m-%y'), ' ') as 'fecha de alta' "+
                                    "FROM expedientes as e "+
                                    "inner join pacientes as p on p.numero = e.paciente "+
                                    "inner join expedientes_especialidad as ee on e.folio = ee.expediente "+
                                    "inner join especialidades as es on ee.especialidad = es.codigo "+
                                    "inner join medicos as m on e.medico = m.numero "+
                                    "WHERE p.numero = ?";
                                    




            PreparedStatement statement = conexion.prepareStatement(consultaSQL);                        
            statement.setString(1, numeroPaciente);            
            ResultSet resultSet = statement.executeQuery();

            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
            if(resultSet.next()){

                printBlue("Nombre del paciente: ");
                printGreen(resultSet.getString("Nombre del paciente"));
                System.out.println();

                do{

                

                printBlue("Codigo de ingreso: ");
                printGreen(resultSet.getString("Codigo de ingreso"));
                System.out.println();

                printBlue("Sintomas:          ");
                printGreen(resultSet.getString("Sintoma"));
                System.out.println();

                printBlue("Diagnostico:       ");
                printGreen(resultSet.getString("Diagnostico"));
                System.out.println();

                printBlue("Nombre del médico: ");
                printGreen(resultSet.getString("Medico"));
                System.out.println();

                printBlue("Especialidad       ");
                printGreen(resultSet.getString("Especialidad"));
                System.out.println();

                printBlue("Fecha de ingreso   ");
                printGreen(resultSet.getString("fecha de ingreso"));
                System.out.println();

                 printBlue("Fecha de alta     ");
                printGreen(resultSet.getString("fecha de alta"));
                System.out.println("\n");
                
                
               
                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m"); // cambia el color del texto a rosita
                System.out.println("\n");
              
       
                }while (resultSet.next());
            }else{System.out.println("No se encontro al paciente");}

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
