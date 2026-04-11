package dao;

import conexion.ConexionDB;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    // ================= INSERTAR =================
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
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // ================= LISTAR =================
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
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return lista;
    }

    // ================= ELIMINAR =================
    public void eliminarProducto(int id) {

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try {
            conn = ConexionDB.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    // ================= OBTENER POR ID =================
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
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return producto;
    }

    // ================= ACTUALIZAR =================
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
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}