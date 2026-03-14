<%-- Importar la clase UsuarioDAO que permite acceder a la base de datos --%>
<%@ page import="dao.UsuarioDAO" %>

<%-- Importar el modelo Usuario --%>
<%@ page import="modelo.Usuario" %>

<%-- Configuración de la página y codificación de caracteres --%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <%-- Título de la página --%>
    <title>Editar Usuario</title>

    <%-- Importar Tailwind CSS --%>
    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-100">


<%-- ============================= --%>
<%-- INCLUIR MENÚ DEL SISTEMA --%>
<%-- ============================= --%>

<jsp:include page="../includes/menu.jsp" />


<%
    // Obtener el id enviado en la URL
    // Ejemplo: editar.jsp?id=3
    int id = Integer.parseInt(request.getParameter("id"));

    // Crear objeto DAO
    UsuarioDAO dao = new UsuarioDAO();

    // Buscar el usuario en la base de datos usando el id
    Usuario u = dao.buscar(id);
%>


<%-- ============================= --%>
<%-- CONTENEDOR PRINCIPAL --%>
<%-- ============================= --%>

<div class="max-w-xl mx-auto mt-10 bg-white p-8 rounded shadow">

<h1 class="text-2xl font-bold mb-6">

Editar Usuario

</h1>


<%-- ============================= --%>
<%-- FORMULARIO DE EDICIÓN --%>
<%-- ============================= --%>

<form action="../UsuarioServlet" method="POST">


<%-- Campo oculto para enviar el id del usuario --%>
<input type="hidden" name="id" value="<%=u.getId()%>">


<%-- Campo oculto que indica al servlet que debe ejecutar "actualizar" --%>
<input type="hidden" name="accion" value="actualizar">


<%-- ============================= --%>
<%-- CAMPO NOMBRE --%>
<%-- ============================= --%>

<label class="block mb-2">

Nombre

</label>

<input
    type="text"
    name="nombre"
    value="<%=u.getNombre()%>"
    class="w-full border p-2 rounded mb-4"
>


<%-- ============================= --%>
<%-- CAMPO CORREO --%>
<%-- ============================= --%>

<label class="block mb-2">

Correo

</label>

<input
    type="email"
    name="correo"
    value="<%=u.getCorreo()%>"
    class="w-full border p-2 rounded mb-4"
>


<%-- ============================= --%>
<%-- CAMPO CLAVE --%>
<%-- ============================= --%>

<label class="block mb-2">

Clave

</label>

<input
    type="text"
    name="clave"
    value="<%=u.getClave()%>"
    class="w-full border p-2 rounded mb-6"
>


<%-- ============================= --%>
<%-- BOTONES --%>
<%-- ============================= --%>

<div class="flex gap-4">

<button
    type="submit"
    class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
>

Actualizar

</button>


<a href="usuarios.jsp">

<button
    type="button"
    class="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500"
>

Cancelar

</button>

</a>

</div>


</form>

</div>


<%-- ============================= --%>
<%-- FOOTER --%>
<%-- ============================= --%>

<jsp:include page="../includes/footer.jsp" />


</body>

</html>