// Paquete donde se encuentra el servlet
package principal;

// Importa la clase UsuarioDAO que contiene los métodos para trabajar con la base de datos
import dao.UsuarioDAO;

// Importa el modelo Usuario que representa la tabla usuarios
import modelo.Usuario;

// Importaciones necesarias para trabajar con Servlets
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Importación para manejar errores de entrada/salida
import java.io.IOException;

// Define la URL con la que se llamará el servlet desde el navegador o formulario
@WebServlet("/UsuarioServlet")

// La clase UsuarioServlet hereda de HttpServlet
// Esto permite que funcione como un servlet web
public class UsuarioServlet extends HttpServlet {

    // Se crea un objeto del DAO para poder usar los métodos de la base de datos
    UsuarioDAO dao = new UsuarioDAO();

    // Método que se ejecuta cuando el formulario envía datos con método POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Se obtiene el parámetro "accion" enviado desde el formulario
            // Este parámetro indica qué operación se quiere realizar
            String accion = request.getParameter("accion");

            // Si no viene ninguna acción, el método termina
            if (accion == null) {
                return;
            }

            // Estructura switch para evaluar qué acción se va a ejecutar
            switch (accion) {

                // ==========================================
                // ACCIÓN: GUARDAR USUARIO
                // ==========================================
                case "guardar":

                    // Se crea un objeto del modelo Usuario
                    Usuario u1 = new Usuario();

                    // Se obtiene el valor del campo "nombre" enviado desde el formulario
                    // y se guarda en el objeto Usuario
                    u1.setNombre(request.getParameter("nombre"));

                    // Se obtiene el valor del campo "correo"
                    u1.setCorreo(request.getParameter("correo"));

                    // Se obtiene el valor del campo "clave"
                    u1.setClave(request.getParameter("clave"));

                    // Se llama al método insertar del DAO para guardar el usuario en la base de datos
                    dao.insertar(u1);

                    // Se redirige al archivo listar.jsp donde se muestran los usuarios
                    response.sendRedirect("usuarios/usuarios.jsp?msg=creado");

                    // break evita que el programa siga ejecutando otros casos del switch
                    break;

                // ==========================================
                // ACCIÓN: ACTUALIZAR USUARIO
                // ==========================================
                case "actualizar":

                    // Se crea otro objeto Usuario
                    Usuario u2 = new Usuario();

                    // Se obtiene el id enviado desde el formulario
                    // Integer.parseInt convierte el texto a número
                    u2.setId(Integer.parseInt(request.getParameter("id")));

                    // Se obtiene el nuevo nombre
                    u2.setNombre(request.getParameter("nombre"));

                    // Se obtiene el nuevo correo
                    u2.setCorreo(request.getParameter("correo"));

                    // Se obtiene la nueva clave
                    u2.setClave(request.getParameter("clave"));

                    // Se llama al método actualizar del DAO
                    dao.actualizar(u2);

                    // Se redirige nuevamente a la lista de usuarios
                    response.sendRedirect("usuarios/usuarios.jsp?msg=actualizado");
                    // Termina este caso
                    break;

                // ==========================================
                // ACCIÓN: ELIMINAR USUARIO
                // ==========================================
                case "eliminar":

                    // Se obtiene el id del usuario que se quiere eliminar
                    int id = Integer.parseInt(request.getParameter("id"));

                    // Se llama al método eliminar del DAO
                    dao.eliminar(id);

                    // Se vuelve a cargar la lista de usuarios
                    response.sendRedirect("usuarios/usuarios.jsp?msg=eliminado");

                    // Termina este caso
                    break;
            }

        } catch (Exception e) {

            // Si ocurre algún error se muestra en la consola
            e.printStackTrace();
        }
    }
}