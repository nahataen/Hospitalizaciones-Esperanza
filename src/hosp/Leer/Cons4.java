package hosp.Leer;
//import hosp.colores.colores;
//import hosp.Leer.Cons1;
import java.sql.*;
import java.util.Scanner;

public class Cons4 {
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
        String NombreEspecialidad="";
        String selectinter;
        boolean menuinter = true;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println("|-----------------------------------------|");
            System.out.println("|1 | Cardiología                          |");
            System.out.println("|2 | Pediatría                            |");
            System.out.println("|3 | Oncología                            |"); 
            System.out.println("|4 | Ginecología                          |"); 
            System.out.println("|5 | Neurología                           |");  
            System.out.println("|6 | Dermatología                         |");  
            System.out.println("|7 | Psiquiatría                          |");
            System.out.println("|8 | Medicina Interna                     |");
            System.out.println("|9 | Oftalmología                         |");  
            System.out.println("|10| Ortopedia                            |");
            System.out.println("|11| Volver                               |");
            System.out.println("|-----------------------------------------|");
            System.out.println("| Ingrese el numero de la especialialidad |");
            System.out.print("| ");
            selectinter = scan.next();
        switch (selectinter) {
            case "1": {
                NombreEspecialidad="Cardiología";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();
                    
                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                 
                }
                    break;
                case "2": {
                NombreEspecialidad="Pediatría";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();
                    
                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "3": {
                NombreEspecialidad="Oncología";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();
            
                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "4": {
                NombreEspecialidad="Ginecología";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();

                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "5": {
                NombreEspecialidad="Neurología";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();
                        
                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "6": {
                NombreEspecialidad="Dermatología";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();

                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "7": {
                NombreEspecialidad="Psiquiatría";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();

                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "8": {
                NombreEspecialidad="Medicina Interna";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();

                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "9": {
                NombreEspecialidad="Oftalmología";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();

                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                         menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "10": {
                NombreEspecialidad="Ortopedia";
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "SELECT es.nombre AS 'Nombre especialidad', "+ 
                    "concat(IFNULL(concat (m.nombre_doctor,' '),' '),  IFNULL(concat(m.apellidoP,' '),' ') ,IFNULL(concat(m.apellidoM,' '),' ')) AS 'Nombre del médico' "+
                    "FROM medicos AS m "+
                    "INNER JOIN medicos_especialidad AS me ON m.numero = me.medico "+
                    "INNER JOIN especialidades AS es ON me.especialidad = es.codigo "+
                    "WHERE es.nombre = ?";


                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, NombreEspecialidad);
                    ResultSet resultSet = statement.executeQuery();

                    System.out.println(" ");
                    if(resultSet.next()){
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

                        printBlue("Nombre especialidad: ");
                        printGreen(resultSet.getString("Nombre especialidad"));
                        System.out.println();
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita


                        do{
                        printBlue("Nombre del médico:   ");
                        printGreen(resultSet.getString("Nombre del médico"));
                        System.out.println();

                        
                        }while (resultSet.next());
                        System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                        menuinter = false;

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                    break;
                case "11": {
                    //cierra el ciclo
                    menuinter = false;
                }
                    break;

                default: {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("|-------------------------------------------|");
                    System.out.println("|-----------seleccion incorrecta------------|");
                }
        }
    }while(menuinter);
    }
}
