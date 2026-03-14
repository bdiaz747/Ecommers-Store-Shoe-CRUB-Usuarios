<%-- Importa la clase UsuarioDAO que permite acceder a la base de datos --%>
<%@ page import="dao.UsuarioDAO" %>

<%-- Importa el modelo Usuario --%>
<%@ page import="modelo.Usuario" %>

<%-- Importa la clase List para guardar la lista de usuarios --%>
<%@ page import="java.util.List" %>

<%-- Configuración de la página y codificación de caracteres --%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <%-- Título que aparece en la pestaña del navegador --%>
    <title>Usuarios</title>

    <%-- Importar Tailwind CSS --%>
    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-100">

<%-- ============================= --%>
<%-- INCLUIR MENÚ DEL SISTEMA --%>
<%-- ============================= --%>

<jsp:include page="../includes/menu.jsp" />


<%-- ============================= --%>
<%-- CONTENEDOR PRINCIPAL --%>
<%-- ============================= --%>

<div class="max-w-5xl mx-auto mt-10 bg-white p-8 rounded shadow">


<%-- Título principal de la página --%>
<h1 class="text-3xl font-bold text-blue-600 mb-6">

Usuarios

</h1>


<%
    // Crear un objeto del DAO
    // Este objeto permitirá consultar la base de datos
    UsuarioDAO dao = new UsuarioDAO();

    // Obtener la lista de usuarios desde la base de datos
    // El método listar() devuelve una lista de tipo Usuario
    List<Usuario> lista = dao.listar();
%>


<%-- ============================= --%>
<%-- BOTÓN CREAR USUARIO --%>
<%-- ============================= --%>

<a href="agregar.jsp">

<button class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">

Crear nuevo usuario

</button>

</a>

<br><br>


<%
    // Verificar si la lista está vacía
    // isEmpty() devuelve true si no hay elementos en la lista
    if(lista.isEmpty()){
%>

    <%-- Mensaje que se muestra cuando no hay usuarios en la base de datos --%>
    <p class="text-gray-600">

    No hay usuarios registrados

    </p>

<%
    // Si la lista NO está vacía se ejecuta el bloque else
    } else {
%>


<table class="min-w-full border border-gray-200">

<tr class="bg-gray-200">

    <%-- Encabezado de la columna ID --%>
    <th class="px-4 py-2 text-left">ID</th>

    <%-- Encabezado de la columna nombre --%>
    <th class="px-4 py-2 text-left">Nombre</th>

    <%-- Encabezado de la columna correo --%>
    <th class="px-4 py-2 text-left">Correo</th>

    <%-- Encabezado de la columna clave --%>
    <th class="px-4 py-2 text-left">Clave</th>

    <%-- Encabezado de la columna acciones --%>
    <th class="px-4 py-2 text-left">Acciones</th>

</tr>


<%
    // Recorrer la lista de usuarios usando un ciclo for
    for(Usuario u : lista){
%>

<tr class="border-t">

    <%-- Mostrar el id del usuario --%>
    <td class="px-4 py-2"><%= u.getId() %></td>

    <%-- Mostrar el nombre del usuario --%>
    <td class="px-4 py-2"><%= u.getNombre() %></td>

    <%-- Mostrar el correo del usuario --%>
    <td class="px-4 py-2"><%= u.getCorreo() %></td>

    <%-- Mostrar la clave del usuario --%>
    <td class="px-4 py-2"><%= u.getClave() %></td>

    <td class="px-4 py-2">

        <%-- Enlace para editar usuario enviando el id --%>
        <a href="editar.jsp?id=<%=u.getId()%>"
           class="text-blue-500 hover:underline">

            Editar

        </a>

        |

        <%-- Formulario para eliminar usuario --%>
        <form action="../UsuarioServlet" method="POST" style="display:inline">

            <%-- Campo oculto que envía el id del usuario al servlet --%>
            <input type="hidden" name="id" value="<%=u.getId()%>">

            <%-- Campo oculto que indica la acción eliminar --%>
            <input type="hidden" name="accion" value="eliminar">

            <%-- Botón para eliminar el usuario --%>
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


<%-- ============================= --%>
<%-- FOOTER DEL SISTEMA --%>
<%-- ============================= --%>

<jsp:include page="../includes/footer.jsp" />


</body>

</html>