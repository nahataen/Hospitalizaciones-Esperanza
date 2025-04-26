package hosp.Escribir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Eres un nuevo paciente? (SI/NO)");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("SI")) {
            // Desplegar lista de servicios
            
       

                    // Seleccionar servicio y validar
                    //   int servicioElegido;
                    //  do {
                    //System.out.println("Ingresa el número del servicio que deseas seleccionar:");
                    //  servicioElegido = scanner.nextInt();
                    //} while (!validarServicio(servicioElegido));

            // Objeto para Ingresar datos del paciente
            MetodosPacienteInsert metodopacienteinsert = new MetodosPacienteInsert();
            //manda a llamar al metodo para registrar datos del paciente nuevo
            metodopacienteinsert.registrarPaciente();
        
            // Generar expediente llamando al método insertarPaciente
      
            

            // Tratamiento (solicitud de medicamentos)
            // tratamiento();
        } else if (respuesta.equalsIgnoreCase("NO")) {
            System.out.println("Ingresa el número de paciente existente: ");
            String numeroPaciente = scanner.nextLine();

            // Validar si el número de paciente es correcto en la base de datos
            boolean pacienteExiste = validarPacienteExistente(numeroPaciente);

            if (pacienteExiste) {
                // Generar expediente para el paciente existente
                generarExpediente(/* parámetros necesarios */);

                // Tratamiento (solicitud de medicamentos)
              //----  tratamiento();
            } else {
                System.out.println("El número de paciente ingresado no es correcto.");
            }
        } else {
            System.out.println("Respuesta inválida. Por favor, responde SI o NO.");
        }


    }

    // Métodos de despliegue, selección y validación de datos igual que antes...

    // Método para insertar un nuevo paciente en la base de datos
    public static void insertarPaciente(/* parámetros necesarios */) {
        // Lógica para insertar el paciente en la base de datos
    }

    // Método para generar el expediente para un paciente existente
    public static void generarExpediente(/* parámetros necesarios */) {
        // Lógica para generar el expediente en la base de datos
    }

        // Método para validar si el servicio seleccionado es correcto
        //  public static boolean validarServicio(int servicioElegido) {
        // Lógica para validar si el servicio existe y es válido en la base de datos
        // Devuelve true si el servicio es válido, y false si no lo es
        //}

    // Método para validar si un paciente existe en la base de datos
    public static boolean validarPacienteExistente(String numeroPaciente) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones"; // 
        String username = ""; // 
        String password = ""; //

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL = "SELECT COUNT(*) AS count FROM pacientes WHERE numero = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, numeroPaciente);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0; // Si count es mayor a 0, significa que el paciente existe en la base de datos
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Si hay un error o no se encontró al paciente, se retorna false
    }

    // Resto del código...
}
