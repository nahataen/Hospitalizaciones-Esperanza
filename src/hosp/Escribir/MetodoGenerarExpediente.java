package hosp.Escribir;

// crear paciente epxediente tratamiento y validar si existe paciente o no si no que haga una query que jale los datos solo ingresamos de nuevo lo que es la dolencia y otras cosas que tenga nuevas
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hosp.Menu;
import hosp.MenuMain;

//CLASE QUE TIENE METODOS PARA GENERAR EXPEDIENTE APARTE , HEREDA DE EXPEDIENTE INSERT
public class MetodoGenerarExpediente extends ExpedientesInsert {
    //---CONSTRUCTOR PARA BORRAR LA CLASE NO BORRAR!!!-----
    public MetodoGenerarExpediente() {

    }

    // VARIABLE PARA OBTENER FECHA DE NACIMIENTO
    private static String FechaDEnacimientoMTP;

    // constructor que asigna variable heradad a FechaDEnacimientoMTP;
    public MetodoGenerarExpediente(String fnaciento) {
        super(fnaciento); // Llamada al constructor de la clase padre para inicializar la variable
                          // heredada
        this.FechaDEnacimientoMTP = fnaciento; // Asignar la variable heredada a la variable local
    }

    // metodo captura datos para generar solo el expediente
    public static void CreacionDeExpedientePArticular() {
        // objetos creados
        insertar1 Datos_paciente = new insertar1();

        // capturar numero de paciente y validarlo en la base de datos

        // scanner creado
        Scanner scan = new Scanner(System.in);
        // variables de apoyo
        
     
      

        // Obtener el último número de paciente actual en la base de datos

        int ultimoNumeroExpe = obtenerUltimoNumeroFolio();
        // Incrementar el número de paciente para el próximo registro

        int nuevonumeroExpe = ultimoNumeroExpe + 1;

        System.out.println("------Creacion de expediente-----");
        // Insertar valores en el método expediente consumir caracter nextline donde lo
        // requiera
        // Obtener la fecha actual del sistema

        ExpedientesInsert gennuevoExpyNuevoPaciente = new ExpedientesInsert();

        System.out.println("Ingresa los sintomas");
        String sintomasExpe = leerSintomas(280);
        System.out.println("Ingresa el peso");
        double peso = scan.nextDouble();

        scan.nextLine(); // Consumir el carácter de nueva línea

        System.out.println("Ingresa altura");
        double altura = scan.nextDouble();

        scan.nextLine(); // Consumir el carácter de nueva línea
        System.out.println("ingresa diagnostico");
        String diagnosticoExpe = leerDiagnosticoConLimiteCaracteres(200);

        // mandar a llamar imprimir la lista de habitacion
        ConsultarListaDeHabitaciones consulHabitaciones = new ConsultarListaDeHabitaciones();
        // metodo que despliga habitaciones
        consulHabitaciones.DesplegarMetodoHabitaciones();
        int ncamadisexp;
        int nhabi;

        do{ 
            System.out.println("Ingresa el numero de habitacion ");
            nhabi=scan.nextInt();  
        //metodo que permite obtener el nuero de camas disponibles
        //obtienes la cantidad de camas existetes y lo almacena en camafinal
            int camafinal =obtenerCamasDisponibles(nhabi);
        // System.out.println("ingresa una cama del las que se encuentran disponibles:");
        
            ncamadisexp=camafinal;
            if(ncamadisexp>=1 && ncamadisexp<=3){
            ingresarPaciente(nhabi,ncamadisexp,nuevonumeroExpe);
            }else{
                System.out.println("ingresa otra habitacion la habitacion: "+" "+nhabi+" "+ "tiene todas las camas ocupadas");
            }
        }while(ncamadisexp<=0);
  
        ConsultarServicios cs = new ConsultarServicios();
        // despliega lista de servicios
        cs.DesplegarMetodoServicio();
        System.out.println("ingresa la cantidad de servicios que necesita");
        int arreglo= scan.nextInt();

        int[] arregloServiciosCantidad = new int [arreglo];
        for(int i=0; i<arreglo;i++){
            System.out.println("ingresa el servicio");
            arregloServiciosCantidad[i]=scan.nextInt();

        }
      

        System.out.println("ingrese su numero de paciente existente");
        int numeroPaciente = scan.nextInt();
        int numeroEncontrado = verificarNumeroEnBaseDeDatos(numeroPaciente);
            String fechaActual = obtenerFechaActualEnFormatoMySQL();
        int edadexpe = obtenerEdadDesdeFechaDeNacimiento(metodoparafechanaci(numeroPaciente));

        if (numeroEncontrado != -1) {
            gennuevoExpyNuevoPaciente.MetodoGenExpediente(nuevonumeroExpe, edadexpe, sintomasExpe,peso, altura,
                    diagnosticoExpe, fechaActual, ncamadisexp, null, numeroEncontrado, nhabi, arregloServiciosCantidad[0]);   

            for(int j=0;j<arreglo;j++){
                    servicio_expediente(arregloServiciosCantidad[j],nuevonumeroExpe);
                if (arregloServiciosCantidad[j]<=10 && arregloServiciosCantidad[j]>=1){
                    esp_expediente(arregloServiciosCantidad[j],nuevonumeroExpe);
                }else{
                    System.out.println("ingresaste un numero de especialidad inexistente");
                }
            }


           
           // e(String Folio, int Edad, String Sintomas , double Peso, double Altura,String
            // Diagnosticos, String Fecha_de_ingreso,int Numero_de_cama,String
            // Fecha_de_alta,int Paciente_n,int Habitacion_asig, int Medico_atiende)

            // insertar valores en el método tratamiento

            // Cerrar el Scanner
            // scan.close();
            System.out.println("Tu folio es:" + " " + nuevonumeroExpe);

        } else {
            System.out.println("el paciente no existe quieres crear uno 1-si 2.-no?");
            MetodosPacienteInsert mtd = new MetodosPacienteInsert();
            String validarSinoRegistrarPaciente = scan.nextLine();
            switch (validarSinoRegistrarPaciente) {
                case "1":
                    mtd.registrarPaciente();
                    break;
                case "2":
                    System.out.println("no esta registrado en la base de datos y no quiere crear un nuevo paciente");
                    break;
                default:
                    break;
            }

        }

    }

    /// metodo de fecha de nacimiento para calcular la edad obtiene su fecha de
    /// nacimiento y le resta al año actual del sistema
 
    private static int obtenerEdadDesdeFechaDeNacimiento(String fechaNacimiento) {
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
        LocalDate fechaActual = LocalDate.now();
        
        Period periodo = Period.between(fechaNac, fechaActual);
        return periodo.getYears();
    }

    // Método para validar si un valor contiene solo caracteres numéricos en codigo
    // postal
    private static boolean esCodigoPostalValido(String codigoPostal) {
        return codigoPostal.matches("[0-9]+");
    }

    /*
     * Método para validar si un valor contiene solo caracteres numéricos para el
     * número de casa se quita porque tambien puede tener letras el numero de casa
     * lo deje comentado para tenerlo de apoyo por si se ocupa
     * private static boolean esNumeroCasaValido(String numeroCasa) {
     * return numeroCasa.matches("[0-9]+");
     * }
     */

    // Método para validar si un día es válido para el mes y año dados
    private static boolean esDiaValido(int dia, int mes, int anio) {
        if (dia < 1 || dia > 31) {
            return false;
        }

        // Verificar si el mes es válido
        if (mes < 1 || mes > 12) {
            return false;
        }

        // Verificar si el día es válido para febrero (considerando años bisiestos)
        if (mes == 2) {
            if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
                return dia <= 29;
            } else {
                return dia <= 28;
            }
        }

        // Verificar si el día es válido para meses con 30 días
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return dia <= 30;
        }

        // Para los demás meses con 31 días
        return dia <= 31;
    }

    // Método para obtener el número de paciente más alto actual en la base de datos
    private static int obtenerUltimoNumeroPaciente() {
        int ultimoNumero = 0;
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String usuario = "root";
        String contrasena = "";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            String sql = "SELECT MAX(numero) AS ultimo_numero FROM pacientes";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ultimoNumero = resultSet.getInt("ultimo_numero");
            }

            resultSet.close();
            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ultimoNumero;
    }

    // metodo parecido al de paciente pero con expediente
    private static int obtenerUltimoNumeroFolio() {
        int ultimoNumeroexpe = 0;
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String usuario = "root";
        String contrasena = "";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            String sql = "SELECT MAX(folio) AS ultimo_numero_expe FROM expedientes";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ultimoNumeroexpe = resultSet.getInt("ultimo_numero_expe");
            }

            resultSet.close();
            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ultimoNumeroexpe;
    }

    // metodo para limitar caracteres de sintomas
    public static String leerSintomas(int limiteCaracteres) {
        Scanner scanner = new Scanner(System.in);
        String sintomasExpe;

        do {
            System.out.println("Ingresa los síntomas (máximo " + limiteCaracteres + " caracteres):");
            sintomasExpe = scanner.nextLine();

            if (sintomasExpe.length() > limiteCaracteres) {
                System.out.println(
                        "Los síntomas no pueden exceder " + limiteCaracteres + " caracteres. Ingresa nuevamente:");
            }
        } while (sintomasExpe.length() > limiteCaracteres);

        return sintomasExpe;
    }

    // metodo para limitar caracteres de diagnostico
    public static String leerDiagnosticoConLimiteCaracteres(int limiteCaracteres) {
        Scanner scanner = new Scanner(System.in);
        String diagnosticoExpe;
        do {
            System.out.println("Ingresa el diagnóstico (máximo " + limiteCaracteres + " caracteres):");
            diagnosticoExpe = scanner.nextLine();
            if (diagnosticoExpe.length() > limiteCaracteres) {
                System.out.println(
                        "El diagnóstico no puede exceder " + limiteCaracteres + " caracteres. Ingresa nuevamente:");
            }
        } while (diagnosticoExpe.length() > limiteCaracteres);
        return diagnosticoExpe;
    }

    // metodo para elegir un numero valido entre 1 y 15
    public static int leerEnteroValidoEnRango(int numerovalidado) {
        Scanner scanner = new Scanner(System.in);

        int min = 1;
        int max = 15;

        do {
            System.out.println("Ingresa un número entero entre " + min + " y " + max + ":");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Debes ingresar un número entero válido.");
                scanner.next(); // Consumir el token no válido para evitar un bucle infinito
            }
            numerovalidado = scanner.nextInt();
        } while (numerovalidado < min || numerovalidado > max);

        return numerovalidado;
    }

    // Método para obtener el número de camas disponibles en la habitación
    // seleccionada
    public static int obtenerCamasDisponibles(int numeroHabitacion) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = ""; //

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL = "SELECT numero_de_cama FROM habitaciones WHERE numero = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setInt(1, numeroHabitacion);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("numero_de_cama");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Valor negativo para indicar que no se encontró la habitación o hubo un error
    }

    // Método para ingresar un paciente y asignar una cama
    public static void ingresarPaciente(int numerohabitacionValidado, int numerocamasdisponibles,int nuevonumeroExpe) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese una habitación y validar que sea válida
        do {
            //aqui se obtine el numero de camas disponibles en este codigo de cada habitacion
            numerocamasdisponibles = obtenerCamasDisponibles(numerohabitacionValidado);
            
            if (numerocamasdisponibles >=1 && numerocamasdisponibles <= 3) {
                try {
                    // Establecer la conexión a la base de datos
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalizaciones", "root", "");
            
                    // Crear la consulta SQL con parámetros
                    String sql = "UPDATE habitaciones SET numero_de_cama = numero_de_cama - 1 WHERE numero = ?";
                    String consulta = "UPDATE expedientes SET numero_de_cama = ? WHERE folio = ?";
            
                    // Preparar la declaración SQL
                    PreparedStatement statement2 = connection.prepareStatement(consulta);
                    PreparedStatement statement = connection.prepareStatement(sql);
            
                    // Configurar los parámetros para la primera consulta
                    statement.setInt(1, numerohabitacionValidado);
            
                    // Ejecutar la primera actualización
                    int rowsAffected = statement.executeUpdate();
                    System.out.println("Filas afectadas en habitaciones: " + rowsAffected);
            
                    // Configurar los parámetros para la segunda consulta
                    statement2.setInt(1, nuevonumeroExpe);
                    statement2.setInt(2, numerohabitacionValidado);
            
                    // Ejecutar la segunda actualización
                    int rowsAffected2 = statement2.executeUpdate();
                    System.out.println("Filas afectadas en expedientes: " + rowsAffected2);
            
                    // Cerrar la conexión y los recursos
                    statement2.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println(
                        "Lo sentimos, no hay camas disponibles en la habitación seleccionada. Por favor, elige otra habitación.");
            }
        } while (numerocamasdisponibles <= 0);

    }

    // Método para obtener el médico disponible para un servicio específico
    public static int obtenerMedicoParaServicio() {
        Scanner scanner = new Scanner(System.in);

        // Leer la elección del usuario
        System.out.print("Ingresa el número del servicio: ");
        int numeroServicio = scanner.nextInt();

        // Realizar la consulta para obtener el médico disponible
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";
        int numeroMedico = -1; // Variable para almacenar el número de médico encontrado

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            String consultaSQL = "SELECT numero, nombre_doctor, apellidoP, apellidoM FROM medicos WHERE numero = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setInt(1, numeroServicio);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                numeroMedico = resultSet.getInt("numero");
                String nombreDoctor = resultSet.getString("nombre_doctor");
                String apellidoPaterno = resultSet.getString("apellidoP");
                String apellidoMaterno = resultSet.getString("apellidoM");

                System.out.println("El médico disponible para el servicio elegido es:");
                System.out.println(nombreDoctor + " " + apellidoPaterno + " " + apellidoMaterno);
            } else {
                System.out.println("No se encontró un médico disponible para el servicio elegido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retornar el número de médico encontrado (-1 si no se encontró ninguno)
        return numeroMedico;
    }

    public static int verificarNumeroEnBaseDeDatos(int numeroPaciente) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT numero FROM pacientes WHERE numero = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numeroPaciente);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("numero");
            } else {
                return -1; // Valor para indicar que el paciente no existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // En caso de error también puedes retornar -1
        }
    }

    public static String obtenerFechaActualEnFormatoMySQL() {
        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha de actual en el formato deseado para MySQL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatter);

        return fechaActualFormateada;
    }
    public static void servicio_expediente(int numeroMedicoDisponible, int nuevonumeroExpe) {
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";
    
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO exped_servi (servicio, expediente) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, numeroMedicoDisponible);
                preparedStatement.setInt(2, nuevonumeroExpe);
                int rowsAffected = preparedStatement.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Inserción exitosa");
                } else {
                    System.out.println("No se pudo insertar el registro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones temporal, considera mejor manejo
        }
    }
    


  public static void esp_expediente(int numeroservicioseleccion, int nuevonumeroExpe) {
    String especialidadExpe= String.valueOf(numeroservicioseleccion);    
    String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";
    
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO expedientes_especialidad (especialidad, expediente) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
              if(numeroservicioseleccion<=9 && numeroservicioseleccion>=1){
                preparedStatement.setString(1, "ESP0"+especialidadExpe);

              }else if(numeroservicioseleccion==10){
                preparedStatement.setString(1, "ESP"+especialidadExpe);
              }else{
                System.out.println("no existen mas especialidades.");
              }
                
                preparedStatement.setInt(2, nuevonumeroExpe);
                int rowsAffected = preparedStatement.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Inserción exitosa");
                } else {
                    System.out.println("No se pudo insertar el registro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones temporal, considera mejor manejo
        }
    }
    

    private static String metodoparafechanaci(int pacienteNumero) {
        String fechaNacimientoP =""; // Inicializar la variable para la fecha
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String usuario = "root";
        String contrasena = "";
    
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            String sql = "SELECT date_format(fecha_de_nacimiento, '%Y-%m-%d') AS fecha_nacimiento FROM pacientes WHERE numero = ?";
    
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, pacienteNumero);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                fechaNacimientoP = resultSet.getString("fecha_nacimiento");
            }
    
            resultSet.close();
            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fechaNacimientoP;
    }
    

}