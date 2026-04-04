package principal;

// Modelos y servicios
import modelo.Producto;
import modelo.Categoria;
import service.ProductoService;
import service.CategoriaService;

// Librerías Servlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

// Para listas y manejo de archivos
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

// Configuración para subir archivos
@MultipartConfig
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    ProductoService productoService = new ProductoService();
    CategoriaService categoriaService = new CategoriaService();

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
                List<Producto> lista = productoService.listar();
                request.setAttribute("productos", lista);
                request.getRequestDispatcher("/productos/productos.jsp").forward(request, response);
                break;

            case "nuevo":
                List<Categoria> categorias = categoriaService.listar();
                request.setAttribute("categorias", categorias);
                request.getRequestDispatcher("/productos/agregar.jsp").forward(request, response);
                break;

            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Producto producto = productoService.obtenerPorId(id);
                List<Categoria> categoriasEdit = categoriaService.listar();
                request.setAttribute("producto", producto);
                request.setAttribute("categorias", categoriasEdit);
                request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
                break;

            default:
                listarCatalogo(request, response);
                break;
        }
    }

    // ================= POST =================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "guardar";

        if ("guardar".equals(accion)) {
            guardarProducto(request, response);
        } else if ("eliminar".equals(accion)) {
            eliminarProducto(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarProducto(request, response);
        }
    }

    // ================= LISTAR CATÁLOGO =================
    private void listarCatalogo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Producto> lista = productoService.listar();
        request.setAttribute("productos", lista);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    // ================= GUARDAR PRODUCTO =================
    private void guardarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            Producto producto = new Producto();
            producto.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            producto.setNombreProducto(request.getParameter("nombre_producto"));
            producto.setMarcaProducto(request.getParameter("marca_producto"));
            producto.setDescripcionProducto(request.getParameter("descripcion_producto"));
            producto.setPrecioProducto(Double.parseDouble(request.getParameter("precio_producto")));
            producto.setStockProducto(Integer.parseInt(request.getParameter("stock_producto")));

            // ================= IMAGEN =================
            Part filePart = request.getPart("imagen_producto");
            if (filePart != null && filePart.getSize() > 0) {
                String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/img/productos");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(uploadPath + File.separator + nombreArchivo);

                // Guardar ruta completa en la BD
                producto.setImagenProducto("img/productos/" + nombreArchivo);
            }

            productoService.guardar(producto);
            response.sendRedirect("ProductoServlet?accion=listarAdmin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ELIMINAR PRODUCTO =================
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        productoService.eliminar(id);
        response.sendRedirect("ProductoServlet?accion=listarAdmin");
    }

    // ================= ACTUALIZAR PRODUCTO =================
    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Producto producto = new Producto();
            producto.setIdProducto(Integer.parseInt(request.getParameter("id_producto")));
            producto.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            producto.setNombreProducto(request.getParameter("nombre_producto"));
            producto.setMarcaProducto(request.getParameter("marca_producto"));
            producto.setDescripcionProducto(request.getParameter("descripcion_producto"));
            producto.setPrecioProducto(Double.parseDouble(request.getParameter("precio_producto")));
            producto.setStockProducto(Integer.parseInt(request.getParameter("stock_producto")));

            // ================= IMAGEN =================
            Part filePart = request.getPart("imagen_producto");
            if (filePart != null && filePart.getSize() > 0) {
                // Subió nueva imagen → guardar ruta completa
                String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/img/productos");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(uploadPath + File.separator + nombreArchivo);
                producto.setImagenProducto("img/productos/" + nombreArchivo);

            } else {
                // No subió imagen → mantener la existente
                Producto existente = productoService.obtenerPorId(producto.getIdProducto());
                producto.setImagenProducto(existente.getImagenProducto());
            }

            productoService.actualizar(producto);
            response.sendRedirect("ProductoServlet?accion=listarAdmin");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}