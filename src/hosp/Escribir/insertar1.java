package hosp.Escribir;
// esto es para asgeurar al paciente y asignarle un numero de seguro
import java.sql.*;

public class insertar1 {

   String Fnn;
    public insertar1(String fn){
        this.Fnn=fn;
    }


    public insertar1() {
    }


    private String url = "jdbc:mysql://localhost:3306/hospitalizaciones?characterEncoding=latin1";
    private String usuario = "root";
    private String contrasena = "";

    public void insertarPaciente(int numero, String nombre, String apellidoPaterno, String apellidoMaterno,
            String numeroTelefono, String correoElectronico, String dirCP, String dirColonia,
            String dirCalle, String dirNumCasa, String fechaNacimiento) {
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            String sql = "INSERT INTO pacientes (numero, nombre, apellido_paterno, apellido_materno, numero_telefono, "
                    +
                    "correo_electronico, dir_cp, dir_colonia, dir_calle, dir_num_casa, fecha_de_nacimiento) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            preparedStatement.setInt(1, numero);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellidoPaterno);
            preparedStatement.setString(4, apellidoMaterno);
            preparedStatement.setString(5, numeroTelefono);
            preparedStatement.setString(6, correoElectronico);
            preparedStatement.setString(7, dirCP);
            preparedStatement.setString(8, dirColonia);
            preparedStatement.setString(9, dirCalle);
            preparedStatement.setString(10, dirNumCasa);
            preparedStatement.setString(11, fechaNacimiento);

            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
