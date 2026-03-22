// EDitado por Brahian Díaz G, 21-MAR-2026

package principal;
// Define el paquete del servlet

import service.UsuarioService;
// Importa la capa de servicio

import modelo.Usuario;
// Importa el modelo Usuario

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioServlet")
// Define la URL del servlet

public class UsuarioServlet extends HttpServlet {

    UsuarioService service = new UsuarioService();
    // Instancia del servicio


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String accion = request.getParameter("accion");
            // Obtiene la acción

            if (accion == null) {
                accion = "listar";
            }

            switch (accion) {

                case "listar":

                    List<Usuario> lista = service.listarUsuarios();
                    // Obtiene la lista de usuarios

                    request.setAttribute("usuarios", lista);
                    // Envía la lista a la vista

                    request.getRequestDispatcher("usuarios/usuarios.jsp")
                            .forward(request, response);
                    break;

                case "nuevo":

                    request.getRequestDispatcher("usuarios/agregar.jsp")
                            .forward(request, response);
                    break;

                case "editar":

                    int id = Integer.parseInt(request.getParameter("id"));
                    // Obtiene el ID

                    Usuario usuario = service.obtenerUsuario(id);
                    // Busca el usuario

                    request.setAttribute("usuario", usuario);
                    // Envía el usuario a la vista

                    request.getRequestDispatcher("usuarios/editar.jsp")
                            .forward(request, response);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error en doGet: " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String accion = request.getParameter("accion");
            // Obtiene la acción

            if (accion == null) {
                return;
            }

            switch (accion) {

                case "guardar":

    Usuario u1 = new Usuario();

    u1.setNombre(request.getParameter("nombre"));
    u1.setCorreo(request.getParameter("correo"));
    u1.setClave(request.getParameter("clave"));

    service.guardarUsuario(u1);
    // Inserta usuario

    request.getSession().setAttribute("mensaje", "Usuario guardado correctamente");
    // Guarda el mensaje en sesión

    response.sendRedirect("UsuarioServlet");
    break;

                case "actualizar":

                    Usuario u2 = new Usuario();

                    u2.setId(Integer.parseInt(request.getParameter("id")));
                    u2.setNombre(request.getParameter("nombre"));
                    u2.setCorreo(request.getParameter("correo"));
                    u2.setClave(request.getParameter("clave"));

                    service.guardarUsuario(u2);
                    // Actualiza usuario

                    request.getSession().setAttribute("mensaje", "Usuario actualizado correctamente");
                    // Guarda el mensaje en sesión

                    response.sendRedirect("UsuarioServlet");
                    break;

                case "eliminar":

                    int id = Integer.parseInt(request.getParameter("id"));
                    // Obtiene el ID

                    service.eliminarUsuario(id);
                    // Elimina usuario

                    request.getSession().setAttribute("mensaje", "Usuario eliminado correctamente");
                    // Guarda el mensaje en sesión

                    response.sendRedirect("UsuarioServlet");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}