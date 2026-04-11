package service;

import dao.ProductoDAO;
import modelo.Producto;
import java.util.List;

public class ProductoService {

    ProductoDAO productoDAO = new ProductoDAO();

    // ==============================
    // 🔴 VALIDACIÓN INTERNA
    // ==============================
    private void validarProducto(Producto p) {

        if (p == null) {
            throw new IllegalArgumentException("Producto inválido");
        }

        // TEXTO
        if (p.getNombreProducto() == null || p.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre obligatorio");
        }

        if (p.getDescripcionProducto() == null || p.getDescripcionProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("Descripción obligatoria");
        }

        // SEGURIDAD
        String nombre = p.getNombreProducto().toLowerCase();
        String descripcion = p.getDescripcionProducto().toLowerCase();

        if (nombre.contains("<script") || descripcion.contains("<script") ||
            nombre.contains("<") || descripcion.contains("<")) {

            throw new IllegalArgumentException("Contenido no permitido (HTML/script)");
        }

        // PRECIO
        if (p.getPrecioProducto() <= 0) {
            throw new IllegalArgumentException("Precio inválido");
        }

        // STOCK
        if (p.getStockProducto() < 0) {
            throw new IllegalArgumentException("Stock inválido");
        }
    }

    // ==============================
    // 🔹 GUARDAR PRODUCTO
    // ==============================
    public void guardar(Producto producto) {

        validarProducto(producto); // 🔥 VALIDACIÓN
        productoDAO.agregarProducto(producto);
    }

    // ==============================
    // 🔹 LISTAR PRODUCTOS
    // ==============================
    public List<Producto> listar() {
        return productoDAO.listarProductos();
    }

    // ==============================
    // 🔹 ELIMINAR PRODUCTO
    // ==============================
    public void eliminar(int id) {
        productoDAO.eliminarProducto(id);
    }

    // ==============================
    // 🔹 OBTENER PRODUCTO POR ID
    // ==============================
    public Producto obtenerPorId(int id) {
        return productoDAO.obtenerProductoPorId(id);
    }

    // ==============================
    // 🔹 ACTUALIZAR PRODUCTO
    // ==============================
    public void actualizar(Producto producto) {

        validarProducto(producto); // 🔥 VALIDACIÓN
        productoDAO.actualizarProducto(producto);
    }
}