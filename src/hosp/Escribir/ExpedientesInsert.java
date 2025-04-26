package hosp.Escribir;
import java.time.LocalDate;
import java.sql.*;

public class ExpedientesInsert extends insertar1 {
     private String feshanaci;
 //
    public ExpedientesInsert(String fshn) {
        super(fshn); // Llamada al constructor de la clase padre para inicializar la variable heredada
        this.feshanaci = fshn; // Asignar la variable heredada a la variable local
    }//

    

    public ExpedientesInsert() {
    }



    private String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
    private String usuario = "root";
    private String contrasena = "";

    public void MetodoGenExpediente(int nuevoNumero, int Edad, String Sintomas , double Peso, double Altura,String Diagnosticos, String fechaActual,int Numero_de_cama,String Fecha_de_alta,int Paciente_n,int Habitacion_asig, int Medico_atiende) {
        try {
            // Establecer la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            // Definir la consulta SQL para insertar un nuevo expediente
            String sql = "INSERT INTO expedientes (folio, edad, sintomas, peso, altura, diagnosticos, fecha_de_ingreso, numero_de_cama, fecha_de_alta, paciente, habitacion, medico) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Preparar la consulta SQL
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            // Establecer los valores de los parámetros en la consulta SQL
            preparedStatement.setInt(1, nuevoNumero);
            preparedStatement.setInt(2, Edad);// esto <--
            preparedStatement.setString(3, Sintomas);
            preparedStatement.setDouble(4, Peso);
            preparedStatement.setDouble(5, Altura);
            preparedStatement.setString(6, Diagnosticos);
            preparedStatement.setString(7, fechaActual); //con esto se puede calcular la edad se captura en una variable y luego se compara con la de la query fecha de nacimiento antes de pasarle el parametro y si concuerdan ya se pasa para que la query la ejecute
            preparedStatement.setInt(8, Numero_de_cama);
            preparedStatement.setString(9, Fecha_de_alta);
            preparedStatement.setInt(10, Paciente_n);
            preparedStatement.setInt(11, Habitacion_asig);
            preparedStatement.setInt(12, Medico_atiende);

            // Ejecutar la consulta SQL y obtener el número de filas afectadas
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

            // Cerrar el PreparedStatement y la conexión
            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
