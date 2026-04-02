package service;

import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class ProductoService {

    // Instancia del DAO
    ProductoDAO productoDAO = new ProductoDAO();

    // ==============================
    // Guardar producto
    // ==============================
    public void guardar(Producto producto) {
        productoDAO.agregarProducto(producto);
    }

    // ==============================
    // Listar productos
    // ==============================
    public List<Producto> listar() {
        return productoDAO.listarProductos();
    }
}