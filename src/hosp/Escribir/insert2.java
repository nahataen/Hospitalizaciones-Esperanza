package hosp.Escribir;

import java.sql.*;  
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insert2 {

    // URL de la base de datos MySQL
    private String url = "jdbc:mysql://localhost:3306/hospitalizaciones";    
    private String usuario = "root";    
    private String contrasena = ""; 

    public void tratamientos(String codigo, String dosis, int tiempoDias, int expediente, String medicamento) {

        try {
            // Conectarse a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            String sql = "INSERT INTO tratamientos " +
                         "(codigo, dosis, tiempo_dias, expediente, medicamento) " +  
                         "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, codigo); 
            preparedStatement.setString(2, dosis);    
            preparedStatement.setInt(3, tiempoDias);
            preparedStatement.setInt(4, expediente);
            preparedStatement.setString(5, medicamento);

            // Ejecutar la sentencia SQL
            int filasAfectadas = preparedStatement.executeUpdate();  

            // Mostrar el número de filas afectadas
            System.out.println("Filas afectadas: " + filasAfectadas);

            // Cerrar recursos
            preparedStatement.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}