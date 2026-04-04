package service;

import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class ProductoService {

    // ==============================
    // INSTANCIA DEL DAO
    // ==============================
    // Se encarga de comunicarse con la base de datos
    ProductoDAO productoDAO = new ProductoDAO();


    // ==============================
    // 🔹 GUARDAR PRODUCTO
    // ==============================
    // Recibe un objeto Producto desde el Servlet
    // y lo envía al DAO para insertarlo en la BD
    public void guardar(Producto producto) {
        productoDAO.agregarProducto(producto);
    }


    // ==============================
    // 🔹 LISTAR PRODUCTOS
    // ==============================
    // Obtiene todos los productos desde el DAO
    // y los retorna al Servlet
    public List<Producto> listar() {
        return productoDAO.listarProductos();
    }


    // ==============================
    // 🔹 ELIMINAR PRODUCTO
    // ==============================
    // Recibe el ID desde el Servlet
    // y llama al DAO para eliminar el registro en la BD
    public void eliminar(int id) {
        productoDAO.eliminarProducto(id);
    }


    // ==============================
    // 🔹 OBTENER PRODUCTO POR ID
    // ==============================
    // Se utilizará para EDITAR:
    // Busca un producto específico en la BD
    // y lo retorna al Servlet
    public Producto obtenerPorId(int id) {
        return productoDAO.obtenerProductoPorId(id);
    }


    // ==============================
    // 🔹 ACTUALIZAR PRODUCTO
    // ==============================
    // Recibe un objeto Producto modificado
    // y lo envía al DAO para hacer UPDATE en la BD
    public void actualizar(Producto producto) {
        productoDAO.actualizarProducto(producto);
    }
}