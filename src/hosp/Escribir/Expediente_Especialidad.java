package hosp.Escribir;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class Expediente_Especialidad {
    private String url = "jdbc:mysql://localhost:3306/hospitalizaciones";
    private String usuario = "root";
    private String contrasena = "";

    public void MetodoGenExpediente_Especialidad(int especialidad, int expediente) {
        try {
            // Establecer la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
    
            String sql = "INSERT INTO expedientes_especialidad (especialidad, expediente) " +
                         "VALUES (?, ?)";
    
            // Preparar la consulta SQL
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
            // Establecer los valores de los parámetros en la consulta SQL
            preparedStatement.setInt(1, especialidad);
            preparedStatement.setInt(2, expediente);
    
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
