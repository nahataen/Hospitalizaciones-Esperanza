package hosp.Leer;

import java.sql.*;

public class Cons1 {

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

    public void info(String nuemerofolio) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";
        String codigos ="";
        int valida=0;
        if(valida==0){
        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL =    "SELECT t.numero as numero from expedientes as e \n"+
                                    "inner join tratamientos as t on t.expediente = e.folio \n"+
                                    "WHERE folio= ? ";
        
                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, nuemerofolio);
                    ResultSet resultSet = statement.executeQuery();
            
                    if(resultSet.next()){
                        System.out.println();
                        valida=1;
        
                    }else{
                        System.out.println();
                        valida=2;}
                }catch (SQLException e) {
                    e.printStackTrace();
                }      
        }

        if(valida==1){
        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                String consultaSQL =    "SELECT p.numero AS 'Numero del paciente', "
                                    + " CONCAT(IFNULL(CONCAT(p.nombre, ' '), ''), "
                                    + " IFNULL(CONCAT(p.apellido_paterno, ' '), ''), "
                                    + " IFNULL(CONCAT(p.apellido_materno, ' '), '')) AS 'Nombre del paciente', "
                                    + " DATE_FORMAT(e.fecha_de_ingreso, '%d-%m-%y') AS 'Fecha de ingreso', "
                                    + " e.habitacion AS 'Numero de habitacion', "
                                    + " e.numero_de_cama AS 'Numero de cama', "
                                    + " CONCAT(IFNULL(CONCAT(m.nombre_doctor, ' '), ''), "
                                    + " IFNULL(CONCAT(m.apellidoP, ' '), ''), "
                                    + " IFNULL(CONCAT(m.apellidoM, ' '), '')) AS 'Nombre del medico', "
                                    + " e.sintomas AS Sintoma, "
                                    + " e.diagnosticos AS Diagnostico, "
                                    + " es.nombre AS Especialidad, "
                                    + " md.nombre AS Medicamentos, "
                                    + " t.dosis AS 'Dosis', "
                                    + " t.tiempo_dias AS 'Duracion' "
                                    + " FROM expedientes AS e "
                                    + " INNER JOIN pacientes AS p ON p.numero = e.paciente "
                                    + " INNER JOIN medicos AS m ON m.numero = e.medico "
                                    + " INNER JOIN expedientes_especialidad AS ee ON e.folio = ee.expediente "
                                    + " INNER JOIN especialidades AS es ON es.codigo = ee.especialidad "
                                    + " INNER JOIN tratamientos AS t ON t.expediente = e.folio "
                                    + " INNER JOIN medicamentos AS md ON md.codigo = t.medicamento "
                                    + " WHERE e.folio = ?";
    
                PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                statement.setString(1, nuemerofolio);
    
                ResultSet resultSet = statement.executeQuery();
              
                if (resultSet.next()) {
                    
                    System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
    
                    printBlue("Numero del paciente: ");
                    printGreen(String.valueOf(resultSet.getInt("Numero del paciente")));
                    System.out.println();
    
                    printBlue("Nombre del paciente: ");
                    printGreen(resultSet.getString("Nombre del paciente"));
                    System.out.println();
    
                    printBlue("Fecha de ingreso:    ");
                    printGreen(resultSet.getString("Fecha de ingreso"));
                    System.out.println();

                    printBlue("Numero de habitación:      ");
                    printGreen(String.valueOf(resultSet.getInt("Numero de habitacion")));
                    System.out.println();

                    printBlue("Numero de cama:      ");
                    printGreen(String.valueOf(resultSet.getInt("Numero de cama")));
                    System.out.println();
    
                    printBlue("Nombre del medico:   ");
                    printGreen(resultSet.getString("Nombre del medico"));
                    System.out.println();
    
                    printBlue("Sintoma:             ");
                    printGreen(resultSet.getString("Sintoma"));
                    System.out.println();
    
                    printBlue("Diagnostico:         ");
                    printGreen(resultSet.getString("Diagnostico"));
                    System.out.println();
                    do{

                    System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                    printBlue("Especialidad:        ");
                    printGreen(resultSet.getString("Especialidad"));
                    System.out.println();
                    
                    printBlue("Dosis:        ");
                    printGreen(resultSet.getString("Dosis"));
                    System.out.println();

                    printBlue("Duracion:        ");
                    printGreen(resultSet.getString("Duracion"));
                    System.out.println();
                    }while(resultSet.next());
    
                    System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    System.out.println("");
                    System.out.println("|-------------------------------------------|");
                    System.out.println("|        Si no aparece el ingreso que       |");
                    System.out.println("|          busca intente buscarlo           |");
                    System.out.println("|              individualmente.             |");
                    System.out.println("|-------------------------------------------|");
                    System.out.println(codigos);
    
                } else {
                    System.out.println("No se encontró el numero de ingreso.");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }}

        if (valida==2){
            try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                String consultaSQL =   "SELECT p.numero AS 'Numero del paciente', "
                                    + "CONCAT(IFNULL(CONCAT(p.nombre, ' '), ''), "
                                    + "IFNULL(CONCAT(p.apellido_paterno, ' '), ''), "
                                    + "IFNULL(CONCAT(p.apellido_materno, ' '), '')) AS 'Nombre del paciente', "
                                    + "DATE_FORMAT(e.fecha_de_ingreso, '%d-%m-%y') AS 'Fecha de ingreso', "
                                    + "e.habitacion AS 'Numero de habitacion', "
                                    + "e.numero_de_cama AS 'Numero de cama', "
                                    + "CONCAT(IFNULL(CONCAT(m.nombre_doctor, ' '), ''), "
                                    + "IFNULL(CONCAT(m.apellidoP, ' '), ''), "
                                    + "IFNULL(CONCAT(m.apellidoM, ' '), '')) AS 'Nombre del medico', "
                                    + "e.sintomas AS Sintoma, "
                                    + "e.diagnosticos AS Diagnostico, "
                                    + "es.nombre AS Especialidad, "
                                    + "FROM expedientes AS e "
                                    + "INNER JOIN pacientes AS p ON p.numero = e.paciente "
                                    + "INNER JOIN medicos AS m ON m.numero = e.medico "
                                    + "INNER JOIN expedientes_especialidad AS ee ON e.folio = ee.expediente "
                                    + "INNER JOIN especialidades AS es ON es.codigo = ee.especialidad "
                                    + "INNER JOIN tratamientos AS t ON t.expediente = e.folio "
                                    + "INNER JOIN medicamentos AS md ON md.codigo = t.medicamento "
                                    + "WHERE e.folio = ?";
    
                PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                statement.setString(1, nuemerofolio);
    
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    
                    System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
    
                    printBlue("Numero del paciente: ");
                    printGreen(String.valueOf(resultSet.getInt("Numero del paciente")));
                    System.out.println();
    
                    printBlue("Nombre del paciente: ");
                    printGreen(resultSet.getString("Nombre del paciente"));
                    System.out.println();
    
                    printBlue("Fecha de ingreso:    ");
                    printGreen(resultSet.getString("Fecha de ingreso"));
                    System.out.println();

                    printBlue("Numero de habitación:      ");
                    printGreen(String.valueOf(resultSet.getInt("Numero de habitacion")));
                    System.out.println();

                    printBlue("Numero de cama:      ");
                    printGreen(String.valueOf(resultSet.getInt("Numero de cama")));
                    System.out.println();
    
                    printBlue("Nombre del medico:   ");
                    printGreen(resultSet.getString("Nombre del medico"));
                    System.out.println();
    
                    printBlue("Sintoma:             ");
                    printGreen(resultSet.getString("Sintoma"));
                    System.out.println();
    
                    printBlue("Diagnostico:         ");
                    printGreen(resultSet.getString("Diagnostico"));
                    System.out.println();
                    do{

                    System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                    printBlue("Especialidad:        ");
                    printGreen(resultSet.getString("Especialidad"));
                    System.out.println();
                    }while(resultSet.next());
                    
    
                    System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    System.out.println("");
                    System.out.println("|-------------------------------------------|");
                    System.out.println("|        No tiene ningun tratamiento        |");
                    System.out.println("|         ni ninguna prescripción.          |");
                    System.out.println("|                 asignada.                 |");
                    System.out.println("|-------------------------------------------|");
                    System.out.println(codigos);
    
                } else {
                    System.out.println("No se encontró el numero de ingreso.");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }



    }
}
