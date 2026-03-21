<%-- 
Este archivo contiene el menú del sistema
Se separa en un archivo independiente para poder reutilizarlo
en todas las páginas del sistema
--%>

<!-- Contenedor principal del menú -->
<nav class="bg-slate-800">

    <!-- Contenedor interno para centrar contenido -->
    <div class="max-w-7xl mx-auto px-4">

        <!-- Contenedor flex para alinear elementos -->
        <div class="flex justify-between items-center h-16">

            <!-- Nombre del sistema -->
            <div class="text-white text-xl font-bold">

                <!-- Texto del sistema -->
               Ecommers Store Shoe

            </div>
            <%
String msg = request.getParameter("msg");

if(msg != null){
%>

<div class="max-w-5xl mx-auto mt-4">

    <% if (msg.equals("creado")) { %>

    <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded">
        Usuario creado correctamente
    </div>

    <% } %>

    <% if (msg.equals("actualizado")) { %>

    <div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded">
        Usuario actualizado correctamente
    </div>

    <% } %>

    <% if (msg.equals("eliminado")) { %>

    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded">
        Usuario eliminado correctamente
    </div>

    <% } %>

</div>

<%
    }
%>
            <!-- Contenedor de enlaces -->
            <div class="flex space-x-6">

                <!-- Link hacia la página de inicio -->
                <a href="../EcommerceStoreShoe/index.jsp"
                   class="text-gray-300 hover:text-white">

                    Inicio

                </a>

                <!-- Link hacia la lista de usuarios -->
                <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar"
                   class="text-gray-300 hover:text-white">

                    Usuarios

                </a>
            
            </div>

        </div>

    </div>

</nav>