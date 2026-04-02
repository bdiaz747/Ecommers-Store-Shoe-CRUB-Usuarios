package dao;

// Conexión a la base de datos
import conexion.ConexionDB;

// Modelo Producto
import modelo.Producto;

// Librerías JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Listas
import java.util.ArrayList;
import java.util.List;

// DAO: maneja acceso a datos (BD)
public class ProductoDAO {

    Connection conn;            // conexión a la BD
    PreparedStatement ps;       // consultas preparadas
    ResultSet rs;               // resultados de SELECT

    // =====================================================
    // 🔹 INSERTAR PRODUCTO
    // =====================================================
    public void agregarProducto(Producto producto) {

       
        String sql = "INSERT INTO producto (id_categoria, nombre_producto, marca_producto, descripcion_producto, precio_producto, stock_producto, imagen_producto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Conexión
            conn = ConexionDB.conectar();

            // Preparar consulta
            ps = conn.prepareStatement(sql);

            // Asignar valores
            ps.setInt(1, producto.getIdCategoria());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getMarcaProducto());
            ps.setString(4, producto.getDescripcionProducto());
            ps.setDouble(5, producto.getPrecioProducto());
            ps.setInt(6, producto.getStockProducto());

            // 🔥 NUEVO: guardar ruta de imagen
            ps.setString(7, producto.getImagenProducto());

            // Ejecutar
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =====================================================
    // 🔹 LISTAR PRODUCTOS 
    // =====================================================
    public List<Producto> listarProductos() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM producto";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();

                // Mapear datos de la BD al objeto
                p.setIdProducto(rs.getInt("id_producto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNombreProducto(rs.getString("nombre_producto"));
                p.setMarcaProducto(rs.getString("marca_producto"));
                p.setDescripcionProducto(rs.getString("descripcion_producto"));
                p.setPrecioProducto(rs.getDouble("precio_producto"));
                p.setStockProducto(rs.getInt("stock_producto"));

                // 🔥 NUEVO: obtener ruta de imagen
                p.setImagenProducto(rs.getString("imagen_producto"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}