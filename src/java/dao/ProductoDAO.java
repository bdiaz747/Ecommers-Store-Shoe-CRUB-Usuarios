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
    // Inserta un nuevo producto en la base de datos
    public void agregarProducto(Producto producto) {

        String sql = "INSERT INTO producto (id_categoria, nombre_producto, marca_producto, descripcion_producto, precio_producto, stock_producto, imagen_producto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, producto.getIdCategoria());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getMarcaProducto());
            ps.setString(4, producto.getDescripcionProducto());
            ps.setDouble(5, producto.getPrecioProducto());
            ps.setInt(6, producto.getStockProducto());
            ps.setString(7, producto.getImagenProducto());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // 🔹 LISTAR PRODUCTOS
    // =====================================================
    // Obtiene todos los productos de la base de datos
    public List<Producto> listarProductos() {

        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto();

                p.setIdProducto(rs.getInt("id_producto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNombreProducto(rs.getString("nombre_producto"));
                p.setMarcaProducto(rs.getString("marca_producto"));
                p.setDescripcionProducto(rs.getString("descripcion_producto"));
                p.setPrecioProducto(rs.getDouble("precio_producto"));
                p.setStockProducto(rs.getInt("stock_producto"));
                p.setImagenProducto(rs.getString("imagen_producto"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    // =====================================================
    // 🔹 ELIMINAR PRODUCTO
    // =====================================================
    // Elimina un producto de la base de datos por su ID
    public void eliminarProducto(int id) {

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    // 🔹 OBTENER PRODUCTO POR ID
    // =====================================================
    // Busca un producto específico por su ID
    // Se utiliza para cargar datos en el formulario de edición
    public Producto obtenerProductoPorId(int id) {

        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {

                producto = new Producto();

                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                producto.setNombreProducto(rs.getString("nombre_producto"));
                producto.setMarcaProducto(rs.getString("marca_producto"));
                producto.setDescripcionProducto(rs.getString("descripcion_producto"));
                producto.setPrecioProducto(rs.getDouble("precio_producto"));
                producto.setStockProducto(rs.getInt("stock_producto"));
                producto.setImagenProducto(rs.getString("imagen_producto"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto;
    }


    // =====================================================
    // 🔹 ACTUALIZAR PRODUCTO
    // =====================================================
    // Actualiza los datos de un producto existente en la BD
    public void actualizarProducto(Producto producto) {

        String sql = "UPDATE producto SET id_categoria = ?, nombre_producto = ?, marca_producto = ?, descripcion_producto = ?, precio_producto = ?, stock_producto = ?, imagen_producto = ? WHERE id_producto = ?";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, producto.getIdCategoria());
            ps.setString(2, producto.getNombreProducto());
            ps.setString(3, producto.getMarcaProducto());
            ps.setString(4, producto.getDescripcionProducto());
            ps.setDouble(5, producto.getPrecioProducto());
            ps.setInt(6, producto.getStockProducto());
            ps.setString(7, producto.getImagenProducto());
            ps.setInt(8, producto.getIdProducto());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}