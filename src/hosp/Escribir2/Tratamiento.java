package hosp.Escribir2;
import java.sql.*;
import java.util.Scanner;

public class Tratamiento {

    public static void printBlue(String text) {
        System.out.print("\u001B[34m" + text + "\u001B[0m");
    }

    // Método para imprimir texto en verde
    public static void printGreen(String text) {
        System.out.print("\u001B[32m" + text + "\u001B[0m");
    }


    public static String Tratlimite(int limiteCaracteres) {
        Scanner scanner = new Scanner(System.in,"cp850");
        String Tratamiento;

        do {
            
            
           
            System.out.println("|-------------------------------------------|");
            System.out.println("| Ingresa la docis (maximo " + limiteCaracteres + " caracteres)   |");
            System.out.println("|-------------------------------------------|");
            System.out.print("| ");

            Tratamiento = scanner.nextLine();

            if (Tratamiento.length() > limiteCaracteres) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("|-------------------------------------------|");
                System.out.println("| La docis no pueden exceder " + limiteCaracteres + " caracteres. |");
                System.out.println("|           Ingresa nuevamente.             |");
                System.out.println("|-------------------------------------------|");
            }
        } while (Tratamiento.length() > limiteCaracteres);

        return Tratamiento;
    }

    public void MetodoGenTratamiento() {
    String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
    String usuario = "root";
    String contrasena = ""; 
    String folio="";
    String ScanCodMed="";
    String CodMed="";
    String tiempoDias="";
    String Dosis="";
    int dias=0;
    int Validacion1=0;
    int Validacion2=0;
    int Validacion3=0;
    int numtrat=0;
    boolean ciclo1=true;
    boolean ciclo2=false;

    Scanner scan = new Scanner(System.in);

    // Expediente Entrada
    do{
    System.out.println("|-------------------------------------------|");
    System.out.println("|            Ingrese el folio               |");
    System.out.println("|-------------------------------------------|");
    System.out.print("| folio: ");
    folio = scan.nextLine();
    try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
    String consultaSQL =    "SELECT edad as edad from expedientes WHERE folio= ?";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, folio);
            ResultSet resultSet = statement.executeQuery();
    // Expediente Valida
            if(resultSet.next()){
                Validacion1=1;
                ciclo1=false;

            }else{
            System.out.println("|-------------------------------------------|");
            System.out.println("|             No se encontro                |");
            System.out.println("|-------------------------------------------|");
            System.out.println("|      Presione enter para continuar         |");
            System.out.print("| ");
            String Cnt = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }while(ciclo1);
    // se muestran los medicamentos

    while(ciclo1==false){
    if (Validacion1==1){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            String consultaSQL =    "SELECT codigo as codigo, "+
                                    "nombre AS nombre "+
                                    "FROM medicamentos";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            ResultSet resultSet = statement.executeQuery();
            
            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

            while(resultSet.next()){
                printGreen(resultSet.getString("codigo"));
                System.out.print(" ");
                printGreen(resultSet.getString("nombre"));
                System.out.println("");

                System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
                    }            

            
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }



    // corroborrar e ingresar medicamento
    if (Validacion1==1){
        System.out.println("|-------------------------------------------|");
        System.out.println("|     Ingrese el codigo del medicamento     |");
        System.out.println("|-------------------------------------------|");
        System.out.print("| ");            
        ScanCodMed= scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        
        
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            String consultaSQL =    "SELECT codigo as codigo "+
                                    "FROM medicamentos where codigo = ?";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, ScanCodMed);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                CodMed=resultSet.getString("codigo");
                Validacion2=1;
                ciclo1=true;
                ciclo2=true;


                }else{
            System.out.println("|-------------------------------------------|");
            System.out.println("|             No se encontro                |");
            System.out.println("|-------------------------------------------|");
            System.out.println("|      Presione enter para continuar        |");
            System.out.print("| ");
            String Cnt = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
                    }            

            
            } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    }


    // ingresar tiempo dias 
    do{
   
    if (Validacion2==1){

        System.out.println("|-------------------------------------------|");
        System.out.println("| Ingrese el numero de dias del tratamiento |");
        System.out.println("|-------------------------------------------|");
        System.out.print("| ");                   
        tiempoDias=scan.nextLine();
        try{
            ciclo2=false;
            dias=Integer.parseInt(tiempoDias);
            
            
            
           } catch (NumberFormatException error)  {
            System.out.println("|-------------------------------------------|");
            System.out.println("|             texto invalido                |");
            System.out.println("|-------------------------------------------|");
            ciclo2=true;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            }
            
            
        }
        }while(ciclo2);

    //Ingreso de docis
    
    if (Validacion2==1){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Dosis=Tratlimite(35);
        Validacion3=1;
    }
    
    if(Validacion3==1){
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            String sql = "INSERT INTO tratamientos (numero, dosis, tiempo_dias , expediente, medicamento) VALUES (NULL, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setString(1, Dosis);
            preparedStatement.setInt(2,dias);
            preparedStatement.setString(3, folio);
            preparedStatement.setString(4, CodMed);
           

            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("|-------------------------------------------|");
            System.out.println("|          Se agrego exitosamente           |");
            System.out.println("|-------------------------------------------|");

            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if(Validacion3==1){
    
    try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
        String consultaSQL  =   "SELECT MAX(numero) AS numero "+
        "FROM tratamientos";


        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        ResultSet resultSet = statement.executeQuery();
        
        System.out.println(" ");
        if(resultSet.next()){
            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita

            printBlue("El Numero del tratamiento es: ");
            printGreen(resultSet.getString("Numero"));
            System.out.println();
            System.out.print("\u001B[35m" + "============================================================ \u00BB " + "\u001B[0m \n"); // cambia el color del texto a rosita
            System.out.println("|-------------------------------------------|");
            System.out.println("|      Presione enter para continuar        |");
            System.out.print("| ");
            String Cnt = scan.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }}



    }
}

