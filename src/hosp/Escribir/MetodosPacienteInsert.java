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

public class MetodosPacienteInsert extends ExpedientesInsert  {

    private static String FechaDEnacimientoMTP;

    public MetodosPacienteInsert(String fnaciento) {
        super(fnaciento); // Llamada al constructor de la clase padre para inicializar la variable heredada
        this.FechaDEnacimientoMTP = fnaciento; // Asignar la variable heredada a la variable local
    }



public MetodosPacienteInsert() {
    }


    // metodo captura datos del paciente para insertarlos en la tabla paciente
    public void registrarPaciente() {
        // objetos creados
        insertar1 Datos_paciente = new insertar1();
        //scanner creado
        Scanner scan = new Scanner(System.in);
        //variables de apoyo
        String nombre, apellidoP, apellidoM, telefono, correo, codigoPostal, colonia, calle, numeroCasa;
        int anio, mes, dia;
        //solicita que ingrese datos al paciente
        System.out.println("Ingresa los datos del paciente por favor \n");
        do {
            System.out.println("Ingresa el nombre");
            nombre = scan.nextLine();
        } while (nombre.isEmpty() || nombre.length() >= 30);

       
        // Capturar nombre
    
        //capturar apellido paterno
        System.out.println("Ingresa el apellido paterno de no tener uno  [dejar en blanco  y presionar enter");
        apellidoP = scan.nextLine();
        if (apellidoP.isEmpty()){
            apellidoP=null;
        }
        //capturar apellido materno
        System.out.println("Ingresa el apellido materno");
        apellidoM = scan.nextLine();
        if (apellidoM.isEmpty()){
            apellidoM=null;
        }
        //capturar numero de telefono
        System.out.println("Ingresa el número de teléfono ");
        telefono = scan.nextLine();
        //validar si no ingreso un enter o dato vacio de ser asi que almacene un null telefono
        
        //solicita correo electronico 
        System.out.println("Ingresa el correo electrónico (si no tiene, deja en blanco)");
        //de no tener uno e ingresar enter el correo se quedara como null
        correo = scan.nextLine();
        if (correo.isEmpty()) {
            correo = null;
        }

        // Validar y capturar el codigo postal de no ser valido se repite hasta que ingreses uno valido que sea numerico entero
        do {
            //Capturar codigo postal
            System.out.println("Ingresa el c�digo postal (debe contener solo n�meros)");
            codigoPostal = scan.next();
        } while (!esCodigoPostalValido(codigoPostal));
        scan.nextLine(); // Consume el caracter de nueva linea

        //captura la colonia en la que vive el cliente
        System.out.println("Ingresa la colonia");
        colonia = scan.nextLine();
        // captura la calle
        System.out.println("Ingresa la calle");
        calle = scan.nextLine();

        //Capturar el numero de casa
            System.out.println("Ingresa el n�mero de casa (debe contener solo n�meros)");
            numeroCasa = scan.next();
     

        // Obtener y validar el año de nacimiento (falta que valide que no ingresen letras)
        do {
            System.out.println("Ingresa el año de nacimiento (entre 1900 y " + java.time.Year.now().getValue() + "):");
            if (!scan.hasNextInt()) {
                System.out.println("Debes ingresar un valor numérico.");
            
            }
            anio = scan.nextInt();
        } while (anio < 1900 || anio > java.time.Year.now().getValue());

        // Obtener y validar el mes de nacimiento (falta que valide que no ingresen letras)
        do {
            System.out.println("Ingresa el mes de nacimiento (entre 1 y 12):");
            mes = scan.nextInt();  
            scan.nextLine();
        } while (mes < 1 || mes > 12);

        // Obtener y validar el d�a de nacimiento (falta que valide que no ingresen letras)
        do {
            System.out.println("Ingresa el d�a de nacimiento:");
            dia = scan.nextInt();
            scan.nextLine();
        } while (!esDiaValido(dia, mes, anio));

        // Formatear la fecha de nacimiento
        String fechaNacimiento= anio+"-";

        if(mes>=1 && mes <=9){
             fechaNacimiento = fechaNacimiento +"0"+ mes; 

        }else{fechaNacimiento=fechaNacimiento+mes;}

        if(dia>=1 && dia <=9){
            fechaNacimiento = fechaNacimiento +"-0"+ dia;
        }else{fechaNacimiento=fechaNacimiento+"-"+dia;}
        
        

        // Obtener el ultimo n�mero de paciente actual en la base de datos
        int ultimoNumero = obtenerUltimoNumeroPaciente();
        int ultimoNumeroExpe = obtenerUltimoNumeroFolio();
        // Incrementar el nuero de paciente para el proximo registro
        int nuevoNumero = ultimoNumero + 1;
        int nuevonumeroExpe=ultimoNumeroExpe+1;
     
        String fechaActual = obtenerFechaActualEnFormatoMySQL();
        int edadPACI = obtenerEdadDesdeFechaDeNacimiento(fechaNacimiento);

        // Insertar el nuevo paciente con el nuevo numero y la fecha de nacimiento
        // validada
        Datos_paciente.insertarPaciente(nuevoNumero, nombre, apellidoP, apellidoM, telefono, correo,codigoPostal, colonia, calle, numeroCasa, fechaNacimiento);
        System.out.println("Paciente registrado exitosamente.");
        System.out.println("Desea generar expediente? 1.-si 2.-no");
        System.out.println("tu numero de paciente es:"+nuevoNumero);

        int opcionGenerarExpedientesiono=scan.nextInt();
        if(opcionGenerarExpedientesiono==1){
            System.out.println("------Creacion de expediente-----");
            // Insertar valores en el metodo expediente consumir caracter nextline donde lo
            // requiera
            // Obtener la fecha actual del sistema
        
            int edad = obtenerEdadDesdeFechaDeNacimiento(fechaNacimiento);

            ExpedientesInsert gennuevoExpyNuevoPaciente = new ExpedientesInsert();
        
            System.out.println("Ingresa los sintomas");
            String sintomasExpe = leerSintomas(280);
            System.out.println("Ingresa el peso");
            double peso=scan.nextDouble();
            
            scan.nextLine(); // Consumir el caracter de nueva linea

            System.out.println("Ingresa altura");
            double altura = scan.nextDouble();
        
            scan.nextLine(); // Consumir el carater de nueva l�nea
            System.out.println("ingresa diagnostico");
            String diagnosticoExpe = leerDiagnosticoConLimiteCaracteres(200);
            
            //mandar a llamar imprimir la lista de habitacion
            ConsultarListaDeHabitaciones consulHabitaciones=new ConsultarListaDeHabitaciones();
            //metodo que despliga habitaciones
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
                    ingresarPaciente(nhabi,ncamadisexp,nuevoNumero);
                }else{
                    System.out.println("ingresa otra habitacion la habitacion: "+" "+nhabi+" "+ "tiene todas las camas ocupadas");
                }
            }while(ncamadisexp<=0);
            
            ///-.
            ConsultarServicios cs= new ConsultarServicios();
            //despliega lista de servicios

            cs.DesplegarMetodoServicio();

            System.out.println("ingresa la cantidad de servicios que necesita");
            int arreglo= scan.nextInt();

            int[] arregloServiciosCantidad = new int [arreglo];
            for(int i=0; i<arreglo;i++){
                System.out.println("ingresa el servicio");
                arregloServiciosCantidad[i]=scan.nextInt();

            }
   

            gennuevoExpyNuevoPaciente.MetodoGenExpediente( nuevonumeroExpe, edadPACI, sintomasExpe, peso, altura, diagnosticoExpe,fechaActual, ncamadisexp, null,nuevoNumero,nhabi,arregloServiciosCantidad[0]);
        

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
            
            // insertar valores en el metodo tratamiento

            // Cerrar el Scanner
            // scan.close();
            System.out.println("Tu folio es:"+" "+nuevonumeroExpe);
    
        }else if (opcionGenerarExpedientesiono==2){
            Menu regreso = new Menu();
            regreso.Mostrar();
            
        }
    }



    ///metodo de fecha de nacimiento para calcular la edad obtiene su fecha de nacimiento y le resta al a�o actual del sistema
    private static int obtenerEdadDesdeFechaDeNacimiento(String fechaNacimiento) {
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
        LocalDate fechaActual = LocalDate.now();
        
        Period periodo = Period.between(fechaNac, fechaActual);
        return periodo.getYears();
    }

    // Metodo para validar si un valor contiene solo caracteres num�ricos en codigo postal
    private static boolean esCodigoPostalValido(String codigoPostal) {
        return codigoPostal.matches("[0-9]+");
    }

    
   /* Metodo para validar si un valor contiene solo caracteres num�ricos para el n�mero de casa se quita porque tambien puede tener letras el numero de casa
   lo deje comentado para tenerlo de apoyo por si se ocupa
     private static boolean esNumeroCasaValido(String numeroCasa) {
        return numeroCasa.matches("[0-9]+");
    }*/

    // Metodo para validar si un d�a es v�lido para el mes y año dados
    private static boolean esDiaValido(int dia, int mes, int anio) {
        if (dia < 1 || dia > 31) {
            return false;
        }

        // Verificar si el mes es valido
        if (mes < 1 || mes > 12) {
            return false;
        }

        // Verificar si el d�a es valido para febrero (considerando a�os bisiestos)
        if (mes == 2) {
            if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
                return dia <= 29;
            } else {
                return dia <= 28;
            }
        }

        // Verificar si el dia es valido para meses con 30 dias
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return dia <= 30;
        }

        // Para los demas meses con 31 dias
        return dia <= 31;
    }

    // Metodo para obtener el n�mero de paciente m�s alto actual en la base de datos
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




//metodo parecido al de paciente pero con expediente
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


    //metodo para limitar caracteres de sintomas
    public static String leerSintomas(int limiteCaracteres) {
        Scanner scanner = new Scanner(System.in);
        String sintomasExpe;

        do {
            System.out.println("Ingresa los s�ntomas (m�ximo " + limiteCaracteres + " caracteres):");
            sintomasExpe = scanner.nextLine();

            if (sintomasExpe.length() > limiteCaracteres) {
                System.out.println(
                        "Los s�ntomas no pueden exceder " + limiteCaracteres + " caracteres. Ingresa nuevamente:");
            }
        } while (sintomasExpe.length() > limiteCaracteres);

        return sintomasExpe;
    }
   
   
  /*  //metodo para validar peso de un paciente !!!corregir--------------
    public static void validarPeso(double pesoparametro) {

        do {

            if (!esPesoValido(pesoparametro)) {
                if (pesoparametro < 2.0) {
                    System.out.println("Hasta la fecha no existe un ser humano que pese menos de 2 kilos.");
                } else if (pesoparametro > 150.0) {
                    System.out.println("Hasta ahora no existe un ser humano que pese m�s de 150 kilos.");
                } else {
                    System.out.println("El peso ingresado no es v�lido.");
                }
            } else {
                System.out.println("Peso valido para un beb� reci�n nacido o para un adulto.");
            }
        } while (!esPesoValido(pesoparametro));

    }
   
   
    //metodo para validar peso de un paciente !!!corregir--------------
    public static boolean esPesoValido(double pesoval) {
        double pesoMinimoBebe = 2.0;
        double pesoMinimoAdulto = 45.0;
        double pesoMaximoAdulto = 150.0;

        return (pesoval >= pesoMinimoBebe && pesoval <= pesoMinimoAdulto) || pesoval > pesoMaximoAdulto;
    }
 */ 
 
   /*  //metodo para validar altura de un paciente !!!corregir--------------
    public static void validarAltura(Scanner scan, double alturaa, int edadd) {
        if (edadd < 2) {
            // Si es un bebe menor de 2 años, la altura debe estar dentro del rango de 30 a 100 cm
            if (alturaa < 30 || alturaa > 100) {
                System.out.println("La altura para un beb� menor de 2 a�os debe estar entre 30 cm y 100 cm.");
                // Volver a pedir la altura hasta que ingrese un valor dentro del rango
                do {
                    System.out.println("Ingresa la altura (debe estar entre 30 cm y 100 cm):");
                    alturaa = scan.nextDouble();
                } while (alturaa < 30 || alturaa > 100);
            }
        } else {
            // Si es un ni�o mayor de 2 años o un adulto, la altura debe estar dentro del rango de 100 a 250 cm
            if (alturaa < 100 || alturaa > 250) {
                System.out.println("La altura para un ni�o mayor de 2 a�os o un adulto debe estar entre 100 cm y 250 cm.");
                // Volver a pedir la altura hasta que ingrese un valor dentro del rango
                do {
                    System.out.println("Ingresa la altura (debe estar entre 100 cm y 250 cm):");
                    alturaa = scan.nextDouble();
                } while (alturaa < 100 || alturaa > 250);
            }
        }
        
    
        // Si la altura ingresada cumple con las validaciones, no se hace nada
        // El metodo simplemente se termina sin imprimir mensajes adicionales
    }
 
 */
 
 
    //metodo para limitar caracteres de diagnostico
    public static String leerDiagnosticoConLimiteCaracteres(int limiteCaracteres) {
        Scanner scanner = new Scanner(System.in);
        String diagnosticoExpe;
        do {
            System.out.println("Ingresa el diagn�stico (m�ximo " + limiteCaracteres + " caracteres):");
            diagnosticoExpe = scanner.nextLine();
            if (diagnosticoExpe.length() > limiteCaracteres) {
                System.out.println("El diagn�stico no puede exceder " + limiteCaracteres + " caracteres. Ingresa nuevamente:");
            }
        } while (diagnosticoExpe.length() > limiteCaracteres);
        return diagnosticoExpe;
    }


 //metodo para elegir un numero valido entre 1 y 15 
    public static int leerEnteroValidoEnRango(int numerovalidado) {
        Scanner scanner = new Scanner(System.in);
    
        int min = 1;
        int max = 15;

        do {
            System.out.println("Ingresa un n�mero entero entre " + min + " y " + max + ":");
            while (!scanner.hasNextInt()) {
                System.out.println("Error: Debes ingresar un n�mero entero v�lido.");
                scanner.next(); // Consumir el token no valido para evitar un bucle infinito
            }
            numerovalidado = scanner.nextInt();
        } while (numerovalidado < min || numerovalidado> max);

        return numerovalidado;
    }










    // Metodo para obtener el numero de camas disponibles en la habitacion seleccionada
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

        return -1; // Valor negativo para indicar que no se encontro la habitacion o hubo un error
    }

    

    public static void ingresarPaciente(int numerohabitacionValidado, int numerocamasdisponibles,int nuevonumeroExpe) {
        Scanner scanner = new Scanner(System.in);

        // Pedir al usuario que ingrese una habitacion y validar que sea valida
        do {
               //aqui se obtine el numero de camas disponibles en este codigo de cada habitacion
            numerocamasdisponibles = obtenerCamasDisponibles(numerohabitacionValidado);
            
            if (numerocamasdisponibles >=1 && numerocamasdisponibles <= 3) {
                try {
                    // Establecer la conexi�n a la base de datos
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitalizaciones", "root", "");
            
                    // Crear la consulta SQL con par�metros
                    String sql = "UPDATE habitaciones SET numero_de_cama = numero_de_cama - 1 WHERE numero = ?";
                    String consulta = "UPDATE expedientes SET numero_de_cama = ? WHERE folio = ?";
            
                    // Preparar la declaraci�n SQL
                    PreparedStatement statement2 = connection.prepareStatement(consulta);
                    PreparedStatement statement = connection.prepareStatement(sql);
            
                    // Configurar los parametros para la primera consulta
                    statement.setInt(1, numerohabitacionValidado);
            
                    // Ejecutar la primera actualizacion
                    int rowsAffected = statement.executeUpdate();
                    System.out.println("Filas afectadas en habitaciones: " + rowsAffected);
            
                    // Configurar los parametros para la segunda consulta
                    statement2.setInt(1, nuevonumeroExpe);
                    statement2.setInt(2, numerohabitacionValidado);
            
                    // Ejecutar la segunda actualizacion
                    int rowsAffected2 = statement2.executeUpdate();
                    System.out.println("Filas afectadas en expedientes: " + rowsAffected2);
            
                    // Cerrar la conexion y los recursos
                    statement2.close();
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println(
                        "Lo sentimos, no hay camas disponibles en la habitaci�n seleccionada. Por favor, elige otra habitaci�n.");
            }
        } while (numerocamasdisponibles <= 0);

    }



    // Metodo para obtener el m�dico disponible para un servicio especifico
    public static int obtenerMedicoParaServicio() {
        Scanner scanner = new Scanner(System.in);

        // Leer la eleccion del usuario
        System.out.print("Ingresa el n�mero del servicio: ");
        int numeroServicio = scanner.nextInt();

        // Realizar la consulta para obtener el medico disponible
        String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
        String username = "root";
        String password = "";
        int numeroMedico = -1; // Variable para almacenar el numero de medico encontrado

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

                System.out.println("El m�dico disponible para el servicio elegido es:");
                System.out.println(nombreDoctor + " " + apellidoPaterno + " " + apellidoMaterno);
            } else {
                System.out.println("No se encontr� un m�dico disponible para el servicio elegido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Cerrar el Scanner

        // Retornar el numero de medico encontrado (-1 si no se encontro ninguno)
        return numeroMedico;
    }



    public static String obtenerFechaActualEnFormatoMySQL() {
        // Obtener la fecha actual del sistema
        LocalDate fechaActual = LocalDate.now();

        // Formatear la fecha de actual en el formato deseado para MySQL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaActualFormateada = fechaActual.format(formatter);

        return fechaActualFormateada;
    }


    //----------metodo para servicios_exp
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
                    System.out.println("Inserci�n exitosa");
                } else {
                    System.out.println("No se pudo insertar el registro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones temporal, considera mejor manejo
        }
    }
    

    //metodo para especialidad_expediente
    
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
                    System.out.println("Inserci�n exitosa");
                } else {
                    System.out.println("No se pudo insertar el registro");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones temporal, considera mejor manejo
        }
    }


}


    
    //folio aparte y numero de paciente aparte