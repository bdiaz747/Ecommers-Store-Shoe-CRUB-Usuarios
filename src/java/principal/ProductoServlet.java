package principal;

// Modelo y service
import modelo.Producto;
import modelo.Categoria;
import service.ProductoService;
import service.CategoriaService;

// Librerías servlet
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

            System.out.println("ENTRANDO A GUARDAR");

            Producto producto = new Producto();

            producto.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            producto.setNombreProducto(request.getParameter("nombre_producto"));
            producto.setMarcaProducto(request.getParameter("marca_producto"));
            producto.setDescripcionProducto(request.getParameter("descripcion_producto"));
            producto.setPrecioProducto(Double.parseDouble(request.getParameter("precio_producto")));
            producto.setStockProducto(Integer.parseInt(request.getParameter("stock_producto")));

            System.out.println("DATOS OK");

            // Imagen
            Part filePart = request.getPart("imagen_producto");
            if (filePart != null && filePart.getSize() > 0) {

                String nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                String uploadPath = getServletContext().getRealPath("/img/productos");

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                filePart.write(uploadPath + File.separator + nombreArchivo);

                producto.setImagenProducto(nombreArchivo);
            }

            System.out.println("ANTES DE GUARDAR");

            productoService.guardar(producto);

            System.out.println("GUARDADO EN BD");

            request.getSession().setAttribute("mensaje", "Producto guardado exitosamente");
            response.sendRedirect("ProductoServlet?accion=listarAdmin");

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 CLAVE: ver el error real
        }
    }
}