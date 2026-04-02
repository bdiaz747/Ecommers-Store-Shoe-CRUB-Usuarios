<%-- 
    Menú principal del sistema

    Este archivo se incluye en todas las vistas JSP para reutilizar
    la navegación del sistema y mantener una estructura organizada.
--%>

<!-- Barra de navegación -->
<nav class="bg-slate-800">

    <!-- Contenedor centrado -->
    <div class="max-w-7xl mx-auto px-4">

        <!-- Contenedor flex para distribución horizontal -->
        <div class="flex justify-between items-center h-16">

            <!-- Nombre del sistema -->
            <div class="text-white text-xl font-bold">
                Ecommers Store Shoe
            </div>

            <!-- Enlaces de navegación -->
            <div class="flex space-x-6">

                <!-- Enlace a la página de inicio -->
                <a href="${pageContext.request.contextPath}/ProductoServlet"
                   class="text-gray-300 hover:text-white">
                    Inicio
                </a>

                <!-- Enlace al módulo de administración de productos -->
                <a href="${pageContext.request.contextPath}/ProductoServlet?accion=listarAdmin"
                   class="text-gray-300 hover:text-white">
                    Productos
                </a>

                <!-- Enlace al módulo de usuarios -->
                <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar"
                   class="text-gray-300 hover:text-white">
                    Usuarios
                </a>

            </div>

        </div>

    </div>

</nav>

<%-- 
    Mensajes de estado del sistema

    Se utilizan para mostrar notificaciones al usuario después de realizar
    acciones como crear, actualizar o eliminar registros.
--%>

<%
    String msg = request.getParameter("msg");
    if (msg != null) {
%>

<div class="max-w-5xl mx-auto mt-4">

    <%-- Mensaje: Usuario creado --%>
    <% if (msg.equals("creado")) { %>
        <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded">
            Usuario creado correctamente
        </div>
    <% } %>

    <%-- Mensaje: Usuario actualizado --%>
    <% if (msg.equals("actualizado")) { %>
        <div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded">
            Usuario actualizado correctamente
        </div>
    <% } %>

    <%-- Mensaje: Usuario eliminado --%>
    <% if (msg.equals("eliminado")) { %>
        <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
            Usuario eliminado correctamente
        </div>
    <% } %>

</div>

<%
    }
%>