<%-- Importa la clase Usuario para poder usar sus atributos y métodos --%>
<%@ page import="modelo.Usuario" %>

<%-- Importa la interfaz List para manejar listas de usuarios --%>
<%@ page import="java.util.List" %>

<%-- Configura el tipo de contenido y la codificación de la página --%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <%-- Título de la pestaña del navegador --%>
    <title>Usuarios</title>

    <%-- Importa Tailwind CSS para estilos visuales --%>
    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-100">

<%-- ============================= --%>
<%-- INCLUIR MENÚ DEL SISTEMA --%>
<%-- ============================= --%>

<jsp:include page="../includes/menu.jsp" />



<%-- Contenedor principal de la página --%>
<div class="max-w-5xl mx-auto mt-10 bg-white p-8 rounded shadow">


<%-- Título principal de la sección --%>
<h1 class="text-3xl font-bold text-blue-600 mb-6">
    Usuarios
</h1>


<%-- Botón que redirige al formulario para agregar un nuevo usuario --%>
<a href="${pageContext.request.contextPath}/UsuarioServlet?accion=nuevo"

<button class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
    Crear nuevo usuario
</button>

</a>

<br><br>


<%
    // Obtiene la lista de usuarios enviada desde el servlet mediante request
    List<Usuario> lista = (List<Usuario>) request.getAttribute("usuarios");

    // Verifica si la lista es nula o está vacía
    if (lista == null || lista.isEmpty()) {
%>

    <%-- Mensaje que se muestra cuando no hay usuarios registrados --%>
    <p class="text-gray-600">
        No hay usuarios registrados
    </p>

<%
    // Si la lista tiene datos, se ejecuta este bloque
    } else {
%>


<table class="min-w-full border border-gray-200">

<tr class="bg-gray-200">

    <%-- Encabezado de la columna ID --%>
    <th class="px-4 py-2 text-left">ID</th>

    <%-- Encabezado de la columna Nombre --%>
    <th class="px-4 py-2 text-left">Nombre</th>

    <%-- Encabezado de la columna Correo --%>
    <th class="px-4 py-2 text-left">Correo</th>

    <%-- Encabezado de la columna Clave --%>
    <th class="px-4 py-2 text-left">Clave</th>

    <%-- Encabezado de la columna Acciones --%>
    <th class="px-4 py-2 text-left">Acciones</th>

</tr>


<%
    // Recorre la lista de usuarios enviada por el servlet
    for (Usuario u : lista) {
%>

<tr class="border-t">

    <%-- Muestra el ID del usuario --%>
    <td class="px-4 py-2"><%= u.getId() %></td>

    <%-- Muestra el nombre del usuario --%>
    <td class="px-4 py-2"><%= u.getNombre() %></td>

    <%-- Muestra el correo del usuario --%>
    <td class="px-4 py-2"><%= u.getCorreo() %></td>

    <%-- Muestra la clave del usuario --%>
    <td class="px-4 py-2"><%= u.getClave() %></td>

    <td class="px-4 py-2">

        <%-- Enlace para editar el usuario enviando el id por la URL --%>
        <a href="/EcommerceStoreShoe/usuarios/editar.jsp?id=<%=u.getId()%>"
           class="text-blue-500 hover:underline">
            Editar
        </a>

        |

        <%-- Formulario para eliminar un usuario --%>
        <form action="${pageContext.request.contextPath}/UsuarioServlet" method="POST" style="display:inline">

            <%-- Campo oculto que envía el id del usuario --%>
            <input type="hidden" name="id" value="<%=u.getId()%>">

            <%-- Campo oculto que indica que la acción es eliminar --%>
            <input type="hidden" name="accion" value="eliminar">

            <%-- Botón para ejecutar la eliminación del usuario --%>
            <input 
                type="submit" 
                value="Eliminar"
                class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                onclick="return confirm('¿Seguro que deseas eliminar este usuario?')">

        </form>

    </td>

</tr>

<%
    // Fin del ciclo for
    }
%>

</table>

<%
    // Fin del bloque else
    }
%>

</div>


<%-- Incluye el footer del sistema --%>
<jsp:include page="../includes/footer.jsp" />


</body>

</html>