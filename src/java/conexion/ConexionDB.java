// Indica que esta clase pertenece al paquete conexion
package conexion;

// Importa la clase Connection para manejar conexiones a la base de datos
import java.sql.Connection;

// Importa DriverManager que permite crear conexiones a la base de datos
import java.sql.DriverManager;

// Importa SQLException para manejar errores de conexión
import java.sql.SQLException;

// Clase encargada de conectar Java con la base de datos
public class ConexionDB {

    // Método público y estático que devuelve una conexión
    public static Connection conectar() throws SQLException {

        // Variable donde se guardará la conexión
        Connection conn = null;

        try {

            // Registrar el driver de MySQL
            // Esto permite que Java reconozca el tipo de base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL de conexión a la base de datos
            // localhost = servidor local
            // 3306 = puerto de MySQL
            // eco_store = nombre de la base de datos
            String url = "jdbc:mysql://localhost:3306/eco_store";

            // Usuario de la base de datos
            String user = "root";

            // Contraseña de la base de datos
            String password = "C1n3c0l0r";

            // Se crea la conexión utilizando DriverManager
            conn = DriverManager.getConnection(url, user, password);

            // Mensaje para verificar en consola que la conexión funciona
            System.out.println("Conexion exitosa a la base de datos");

        } catch (Exception e) {

            // Si ocurre un error se muestra en la consola
            e.printStackTrace();

        }

        // Retorna la conexión creada
        return conn;
    }
}