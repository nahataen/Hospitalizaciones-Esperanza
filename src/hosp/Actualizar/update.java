package hosp.Actualizar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class update {
    public static void alta(){
    String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
    String username = "root";
    String password ="";
    boolean tiempo=false;
    String NumHabi="";
    Scanner scan = new Scanner(System.in);

    System.out.println("|----------------------------------|");
    System.out.println("| Ingrese el numero de el ingreso  |");
    System.out.println("|      que desee dar de alta       |");
    System.out.println("|----------------------------------|");
    System.out.print("| folio: ");

    String folio = scan.nextLine();
        
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                    String consultaSQL  =   "select * from expedientes WHERE folio = ? and fecha_de_alta is null";
                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, folio);
                    ResultSet resultSet = statement.executeQuery();
                    
                    if (!resultSet.isBeforeFirst()) {
                        System.out.println("| No se encontró los tratamientos  |");
                        System.out.println("| en el expediente "+folio+".              |");
                        System.out.println("|----------------------------------|");                       
                            } 
                    else {
                        tiempo=true;
                        }     
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                if(tiempo==true){
                    String formatofecha = "";
                    LocalDate FActual = LocalDate.now();
                    int DIA = FActual.getDayOfMonth();
        
                    int MES = FActual.getMonthValue();
        
                    int AN = FActual.getYear();
        
                    formatofecha = AN+"-"+MES+"-"+DIA;
                    
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {

                    String consultaSQL = "UPDATE expedientes " +
                            "SET fecha_de_alta = ? " +
                            "WHERE folio = ?;";

                    PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                    statement.setString(1, formatofecha);
                    statement.setString(2, folio);
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Tratamiento eliminado correctamente.");
                    } else {
                        System.out.println("El código de tratamiento no se encontró en la base de datos.");
                    }
                } catch (SQLException e) {
                e.printStackTrace();
            }}

            if(tiempo==true){
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                String consultaSQL = "SELECT habitacion as numeroHABITACION FROM expedientes WHERE folio=1";
    
                PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                statement.setString(1, folio);
                ResultSet resultSet = statement.executeQuery();
    
                if (resultSet.next()) {
                    NumHabi = resultSet.getString("folio");                   
                    
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if(tiempo==true){
                try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                String consultaSQL = "UPDATE habitaciones SET numero_de_cama = numero_de_cama + 1 WHERE numero = ?";
    
                PreparedStatement statement = conexion.prepareStatement(consultaSQL);
                statement.setString(1, NumHabi);
                ResultSet resultSet = statement.executeQuery();
    
                if (resultSet.next()) {
                System.out.println("");                   
                    
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            
        }
    }

