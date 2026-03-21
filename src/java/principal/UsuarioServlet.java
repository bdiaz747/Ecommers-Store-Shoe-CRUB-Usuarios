// Paquete donde se encuentra el servlet
package principal;

// Importa la clase UsuarioDAO
import dao.UsuarioDAO;

// Importa el modelo Usuario
import modelo.Usuario;

// Importaciones de servlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importación de IOException
import java.io.IOException;

// Importación para listas
import java.util.List;

// URL del servlet
@WebServlet("/UsuarioServlet")

public class UsuarioServlet extends HttpServlet {

    // Instancia del DAO
    UsuarioDAO dao = new UsuarioDAO();

    // ==========================================
    // MÉTODO GET (LISTAR USUARIOS Y NUEVO USUARIO)
    // ==========================================
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String accion = request.getParameter("accion");

            if (accion == null) {
                accion = "listar";
            }

            switch (accion) {

                case "listar":

                    List<Usuario> lista = dao.listar();

                    request.setAttribute("usuarios", lista);

                    request.getRequestDispatcher("usuarios/usuarios.jsp")
                            .forward(request, response);
                    break;

                case "nuevo":

                    // Solo abre el formulario
                    request.getRequestDispatcher("usuarios/agregar.jsp")
                            .forward(request, response);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error en doGet: " + e.getMessage());
        }
    }

    // ==========================================
    // MÉTODO POST (CRUD)
    // ==========================================
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Obtiene la acción
            String accion = request.getParameter("accion");

            if (accion == null) {
                return;
            }

            switch (accion) {

                case "guardar":

                    Usuario u1 = new Usuario();

                    u1.setNombre(request.getParameter("nombre"));
                    u1.setCorreo(request.getParameter("correo"));
                    u1.setClave(request.getParameter("clave"));

                    dao.insertar(u1);

                    response.sendRedirect("UsuarioServlet");
                    break;

                case "actualizar":

                    Usuario u2 = new Usuario();

                    u2.setId(Integer.parseInt(request.getParameter("id")));
                    u2.setNombre(request.getParameter("nombre"));
                    u2.setCorreo(request.getParameter("correo"));
                    u2.setClave(request.getParameter("clave"));

                    dao.actualizar(u2);

                    response.sendRedirect("UsuarioServlet");
                    break;

                case "eliminar":

                    int id = Integer.parseInt(request.getParameter("id"));

                    dao.eliminar(id);

                    response.sendRedirect("UsuarioServlet");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}