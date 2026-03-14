package dao; // Indica el paquete donde está la clase

// Importa la clase de conexión
import conexion.ConexionDB;

// Importa el modelo Usuario
import modelo.Usuario;

// Importaciones necesarias para SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Importaciones para manejar listas
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // ==============================
    // MÉTODO INSERTAR USUARIO
    // ==============================
    public void insertar(Usuario u) throws Exception {

        // Consulta SQL para insertar datos
        String sql = "INSERT INTO usuarios(nombre,correo,clave) VALUES(?,?,?)";

        // Obtiene la conexión a la base de datos
        Connection conn = ConexionDB.conectar();

        // Prepara la consulta SQL
        PreparedStatement ps = conn.prepareStatement(sql);

        // Asigna los valores a los signos ?
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getClave());

        // Ejecuta la consulta
        ps.executeUpdate();
    }

    // ==============================
    // MÉTODO LISTAR USUARIOS
    // ==============================
    public List<Usuario> listar() throws Exception {

        // Lista donde se guardarán los usuarios
        List<Usuario> lista = new ArrayList<>();

        // Consulta SQL
        String sql = "SELECT * FROM usuarios";

        // Conexión
        Connection conn = ConexionDB.conectar();

        // Preparar consulta
        PreparedStatement ps = conn.prepareStatement(sql);

        // Ejecutar consulta
        ResultSet rs = ps.executeQuery();

        // Recorrer los resultados
        while (rs.next()) {

            // Crear objeto usuario
            Usuario u = new Usuario();

            // Asignar valores del ResultSet al objeto
            u.setId(rs.getInt("id"));
            u.setNombre(rs.getString("nombre"));
            u.setCorreo(rs.getString("correo"));
            u.setClave(rs.getString("clave"));

            // Agregar usuario a la lista
            lista.add(u);
        }

        // Retorna la lista
        return lista;
    }

// ==============================
// MÉTODO ACTUALIZAR USUARIO
// ==============================

// Este método recibe un objeto Usuario con los nuevos datos
// y actualiza el registro correspondiente en la base de datos
public void actualizar(Usuario u) throws Exception {

    // Consulta SQL para actualizar los datos del usuario
    // Se utilizan signos ? para evitar inyección SQL
    String sql = "UPDATE usuarios SET nombre=?, correo=?, clave=? WHERE id=?";

    // Crear conexión a la base de datos
    Connection conn = ConexionDB.conectar();

    // Preparar la consulta SQL
    PreparedStatement ps = conn.prepareStatement(sql);

    // Asignar los valores del objeto Usuario a la consulta

    // 1 = nombre
    ps.setString(1, u.getNombre());

    // 2 = correo
    ps.setString(2, u.getCorreo());

    // 3 = clave
    ps.setString(3, u.getClave());

    // 4 = id del usuario que se actualizará
    ps.setInt(4, u.getId());

    // Ejecutar la actualización en la base de datos
    ps.executeUpdate();
}

// ==============================
// MÉTODO BUSCAR USUARIO POR ID
// ==============================

// Este método recibe el id del usuario
// y devuelve el objeto Usuario con sus datos
public Usuario buscar(int id) throws Exception {

    // Crear objeto Usuario vacío
    Usuario u = new Usuario();

    // Consulta SQL
    String sql = "SELECT * FROM usuarios WHERE id=?";

    // Crear conexión a la base de datos
    Connection conn = ConexionDB.conectar();

    // Preparar la consulta
    PreparedStatement ps = conn.prepareStatement(sql);

    // Enviar el id a la consulta
    ps.setInt(1, id);

    // Ejecutar consulta
    ResultSet rs = ps.executeQuery();

    // Si encuentra el usuario
    if(rs.next()){

        // Guardar datos en el objeto Usuario
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setCorreo(rs.getString("correo"));
        u.setClave(rs.getString("clave"));

    }

    // Cerrar conexión
    rs.close();
    ps.close();
    conn.close();

    // Retornar el usuario encontrado
    return u;
}
    // ==============================
    // MÉTODO ELIMINAR USUARIO
    // ==============================
    public void eliminar(int id) throws Exception {

        // Consulta SQL para eliminar
        String sql = "DELETE FROM usuarios WHERE id=?";

        // Conexión
        Connection conn = ConexionDB.conectar();

        // Preparar consulta
        PreparedStatement ps = conn.prepareStatement(sql);

        // Asignar id
        ps.setInt(1, id);

        // Ejecutar eliminación
        ps.executeUpdate();
    }
}