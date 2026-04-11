package principal;

import modelo.Producto;
import modelo.Categoria;
import service.ProductoService;
import service.CategoriaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    ProductoService productoService = new ProductoService();
    CategoriaService categoriaService = new CategoriaService();

    // ================= VALIDACIÓN CENTRAL =================
    private String validar(String nombre, String descripcion, String precioStr) {

        // 1. TEXTO
        if (nombre == null || nombre.trim().isEmpty() ||
            descripcion == null || descripcion.trim().isEmpty()) {
            return "Nombre y descripción obligatorios";
        }

        if (nombre.trim().length() < 3 || nombre.trim().length() > 50) {
            return "Nombre entre 3 y 50 caracteres";
        }

        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .,_-]+$")) {
            return "Nombre con caracteres inválidos";
        }

        // 2. SEGURIDAD
        if (nombre.toLowerCase().contains("<script>") ||
            descripcion.toLowerCase().contains("<script>") ||
            nombre.contains("<") || descripcion.contains("<")) {
            return "Entrada no permitida (HTML/script)";
        }

        // 3. PRECIO
        try {
            double precio = Double.parseDouble(precioStr);
            if (precio <= 0) return "Precio debe ser mayor a 0";
        } catch (Exception e) {
            return "Precio inválido";
        }

        return null;
    }

    // ================= GET =================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "listar":
                listarCatalogo(request, response);
                break;

            case "listarAdmin":
                request.setAttribute("productos", productoService.listar());
                request.getRequestDispatcher("/productos/productos.jsp").forward(request, response);
                break;

            case "nuevo":
                request.setAttribute("categorias", categoriaService.listar());
                request.getRequestDispatcher("/productos/agregar.jsp").forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("producto", productoService.obtenerPorId(id));
                request.setAttribute("categorias", categoriaService.listar());
                request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
                break;

            default:
                listarCatalogo(request, response);
        }
    }

    // ================= POST =================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            guardarProducto(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarProducto(request, response);
        } else if ("eliminar".equals(accion)) {
            eliminarProducto(request, response);
        }
    }

    // ================= LISTAR =================
    private void listarCatalogo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("productos", productoService.listar());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    // ================= GUARDAR =================
    private void guardarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nombre = request.getParameter("nombre_producto");
        String descripcion = request.getParameter("descripcion_producto");
        String precioStr = request.getParameter("precio_producto");

        String error = validar(nombre, descripcion, precioStr);

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/productos/agregar.jsp").forward(request, response);
            return;
        }

        try {
            Producto p = new Producto();
            p.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            p.setNombreProducto(nombre.trim());
            p.setMarcaProducto(request.getParameter("marca_producto"));
            p.setDescripcionProducto(descripcion.trim());
            p.setPrecioProducto(Double.parseDouble(precioStr));
            p.setStockProducto(Integer.parseInt(request.getParameter("stock_producto")));

            Part filePart = request.getPart("imagen_producto");
            if (filePart != null && filePart.getSize() > 0) {
                String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String path = getServletContext().getRealPath("/img/productos");
                new File(path).mkdirs();
                filePart.write(path + File.separator + nombreArchivo);
                p.setImagenProducto("img/productos/" + nombreArchivo);
            }

            productoService.guardar(p);
            response.sendRedirect("ProductoServlet?accion=listarAdmin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ACTUALIZAR =================
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String nombre = request.getParameter("nombre_producto");
        String descripcion = request.getParameter("descripcion_producto");
        String precioStr = request.getParameter("precio_producto");

        String error = validar(nombre, descripcion, precioStr);

        if (error != null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
            return;
        }

        try {
            Producto p = new Producto();
            p.setIdProducto(Integer.parseInt(request.getParameter("id_producto")));
            p.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            p.setNombreProducto(nombre.trim());
            p.setMarcaProducto(request.getParameter("marca_producto"));
            p.setDescripcionProducto(descripcion.trim());
            p.setPrecioProducto(Double.parseDouble(precioStr));
            p.setStockProducto(Integer.parseInt(request.getParameter("stock_producto")));

            Part filePart = request.getPart("imagen_producto");

            if (filePart != null && filePart.getSize() > 0) {
                String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String path = getServletContext().getRealPath("/img/productos");
                new File(path).mkdirs();
                filePart.write(path + File.separator + nombreArchivo);
                p.setImagenProducto("img/productos/" + nombreArchivo);
            } else {
                Producto existente = productoService.obtenerPorId(p.getIdProducto());
                p.setImagenProducto(existente.getImagenProducto());
            }

            productoService.actualizar(p);
            response.sendRedirect("ProductoServlet?accion=listarAdmin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ELIMINAR =================
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        productoService.eliminar(id);
        response.sendRedirect("ProductoServlet?accion=listarAdmin");
    }
}