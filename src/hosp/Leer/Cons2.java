package hosp.Leer;
//import hosp.colores.colores;
import java.sql.*;

public class Cons2 {
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
        int valida=0;

        if(valida==0){
            try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                String consultaSQL =    "SELECT t.numero as numero from pacientes as p "+
                                        "inner join expedientes as e on e.paciente = p.numero "+
                                        "inner join tratamientos as t on t.expediente = e.folio "+
                                        "WHERE p.numero= ? ";
            
                        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                        statement.setString(1, numeroPaciente);
                        ResultSet resultSet = statement.executeQuery();
                
                        if(resultSet.next()){
                            
                            valida=1;
            
                        }else{valida=2;}
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }      
            }


        if (valida==1){ 
        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL  =   "SELECT concat(IFNULL(concat (p.nombre,' '),' '), IFNULL(concat(p.apellido_paterno,' '),' ') , IFNULL(concat(p.apellido_materno,' '),' ')) as 'Nombre del paciente', "+
                                    "e.folio as 'Codigo de ingreso', "+
                                    "date_format(e.fecha_de_ingreso, '%d-%m-%y') as 'Fecha de ingreso', "+
                                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) as 'Nombre del medico', "+
                                    "es.nombre as 'Especialidad', "+
                                    "e.sintomas as 'Sintoma', " +
                                    "e.diagnosticos as 'Diagnostico', "+
                                    "Max(t.tiempo_dias) as 'Dias de hospitalizacio' "+
                                    "FROM expedientes as e "+
                                    "inner join tratamientos as t on t.expediente = e.folio "+
                                    "inner join pacientes as p on p.numero = e.paciente "+
                                    "inner join medicos as m on m.numero = e.medico "+
                                    "inner join expedientes_especialidad as ee on e.folio = ee.expediente "+
                                    "inner join especialidades as es on es.codigo = ee.especialidad "+
                                    "WHERE p.numero = ? "+
                                    "GROUP BY e.folio";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, numeroPaciente);

            ResultSet resultSet = statement.executeQuery();
                
            if (resultSet.next()) {
                
                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                printBlue("Nombre del paciente:       ");
                printGreen(resultSet.getString("Nombre del paciente"));
                System.out.println();

                do{

                printBlue("Codigo de ingeso:          ");
                printGreen(resultSet.getString("Codigo de ingreso"));
                System.out.println();

                printBlue("Fecha de ingreso:          ");
                printGreen(resultSet.getString("Fecha de ingreso"));
                System.out.println();

                printBlue("Nombre del medico:         ");
                printGreen(resultSet.getString("Nombre del medico"));
                System.out.println();

                printBlue("Nombre de la Especialidad: ");
                printGreen(resultSet.getString("Especialidad"));
                System.out.println();

                printBlue("Sintoma:                   ");
                printGreen(resultSet.getString("Sintoma"));
                System.out.println();

                printBlue("Diagnostico:               ");
                printGreen(resultSet.getString("Diagnostico"));
                System.out.println();

                printBlue("Dias de hospitalizacion:   ");
                printGreen(resultSet.getString("Dias de hospitalizacio"));
                System.out.println(" es la candiad de dias");
                System.out.println();
                }while(resultSet.next());
     System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                System.out.println("");
                System.out.println("|-------------------------------------------|");
                System.out.println("|    En el caso que no se encontro algun    |");
                System.out.println("|    ingreso es porque no tiene  ningun     |");
                System.out.println("|        tratamiento o prescripción.        |");
                System.out.println("|-------------------------------------------|");
            }else {
                System.out.println("No se encontró al paciente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (valida==2){ 
        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL  =   "concat(IFNULL(concat (p.nombre,' '),' '), IFNULL(concat(p.apellido_paterno,' '),' ') , IFNULL(concat(p.apellido_materno,' '),' ')) as 'Nombre del paciente', "+
                                    "e.folio as 'Codigo de ingreso', "+
                                    "date_format(e.fecha_de_ingreso, '%d-%m-%y') as 'Fecha de ingreso', "+
                                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) as 'Nombre del medico', "+
                                    "es.nombre as 'Especialidad', "+
                                    "e.sintomas as 'Sintoma', " +
                                    "e.diagnosticos as 'Diagnostico' "+
                                    "FROM expedientes as e "+
                                    "inner join pacientes as p on p.numero = e.paciente "+
                                    "inner join medicos as m on m.numero = e.medico "+
                                    "inner join expedientes_especialidad as ee on e.folio = ee.expediente "+
                                    "inner join especialidades as es on es.codigo = ee.especialidad "+
                                    "WHERE p.numero = ? "+
                                    "GROUP BY e.folio";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, numeroPaciente);

            ResultSet resultSet = statement.executeQuery();
                
            if (resultSet.next()) {
                
                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                printBlue("Nombre del paciente:       ");
                printGreen(resultSet.getString("Nombre del paciente"));
                System.out.println();

                do{

                printBlue("Codigo de ingeso:          ");
                printGreen(resultSet.getString("Codigo de ingreso"));
                System.out.println();

                printBlue("Fecha de ingreso:          ");
                printGreen(resultSet.getString("Fecha de ingreso"));
                System.out.println();

                printBlue("Nombre del medico:         ");
                printGreen(resultSet.getString("Nombre del medico"));
                System.out.println();

                printBlue("Nombre de la Especialidad: ");
                printGreen(resultSet.getString("Especialidad"));
                System.out.println();

                printBlue("Sintoma:                   ");
                printGreen(resultSet.getString("Sintoma"));
                System.out.println();

                printBlue("Diagnostico:               ");
                printGreen(resultSet.getString("Diagnostico"));
                System.out.println();
                }while(resultSet.next());
                
                 
     System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                System.out.println("");
                System.out.println("|-------------------------------------------|");
                System.out.println("|      No tienen asignados los Dias de      |");
                System.out.println("|      hospitalizacio porque no tiene       |");
                System.out.println("|     ningun tratamiento o prescripcion     |");
                System.out.println("|                  asignada.                |");
                System.out.println("|-------------------------------------------|");
            }else {
                System.out.println("No se encontró al paciente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    }
}
