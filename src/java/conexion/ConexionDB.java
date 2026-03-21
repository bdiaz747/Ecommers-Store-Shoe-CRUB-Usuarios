// Indica que la clase pertenece al paquete conexion
package conexion;

// Importa la clase Connection para manejar la conexión a la base de datos
import java.sql.Connection;

// Importa DriverManager para crear conexiones
import java.sql.DriverManager;

// Importa SQLException para manejar errores de SQL
import java.sql.SQLException;

// Declara la clase como pública para que pueda ser usada desde otros paquetes
public class ConexionDB {

    // Método público y estático que devuelve una conexión
    public static Connection conectar() throws SQLException {

        // Variable donde se guardará la conexión
        Connection conn = null;

        try {

            // Registra manualmente el driver de MySQL
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // URL de conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/eco_store";

            // Usuario de la base de datos
            String user = "root";

            // Contraseña de la base de datos
            String password = "C1n3c0l0r";

            // Establece la conexión con la base de datos
            conn = DriverManager.getConnection(url, user, password);

            // Muestra mensaje en consola si la conexión fue exitosa
            System.out.println("Conexion exitosa a la base de datos");

        } catch (Exception e) {

            // Muestra el error en consola si ocurre algún problema
            e.printStackTrace();
        }

        // Retorna la conexión creada
        return conn;
    }
}