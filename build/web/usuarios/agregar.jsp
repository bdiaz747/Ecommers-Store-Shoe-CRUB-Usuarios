<%-- Configuración de la página y codificación de caracteres --%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>

    <%-- Título que aparece en la pestaña del navegador --%>
    <title>Crear Usuario</title>

    <%-- Importar Tailwind CSS para los estilos --%>
    <script src="https://cdn.tailwindcss.com"></script>

</head>


<body class="bg-gray-100">


<%-- =============================== --%>
<%-- INCLUIR MENÚ DEL SISTEMA --%>
<%-- =============================== --%>

<jsp:include page="../includes/menu.jsp" />


<%-- =============================== --%>
<%-- CONTENEDOR PRINCIPAL --%>
<%-- =============================== --%>

<div class="max-w-xl mx-auto mt-10 bg-white p-8 rounded shadow">


<%-- Título del formulario --%>
<h1 class="text-2xl font-bold text-gray-700 mb-6">

Crear Nuevo Usuario

</h1>


<%-- =============================== --%>
<%-- FORMULARIO DE REGISTRO --%>
<%-- =============================== --%>

<form action="../UsuarioServlet" method="POST">


<%-- Campo oculto que indica al servlet qué acción ejecutar --%>
<input type="hidden" name="accion" value="guardar">


<%-- =============================== --%>
<%-- CAMPO NOMBRE --%>
<%-- =============================== --%>

<label class="block text-gray-700 mb-2">

Nombre

</label>

<input
    type="text"
    name="nombre"
    class="w-full border border-gray-300 p-2 rounded mb-4"
    required
>


<%-- =============================== --%>
<%-- CAMPO CORREO --%>
<%-- =============================== --%>

<label class="block text-gray-700 mb-2">

Correo

</label>

<input
    type="email"
    name="correo"
    class="w-full border border-gray-300 p-2 rounded mb-4"
    required
>


<%-- =============================== --%>
<%-- CAMPO CLAVE --%>
<%-- =============================== --%>

<label class="block text-gray-700 mb-2">

Clave

</label>

<input
    type="password"
    name="clave"
    class="w-full border border-gray-300 p-2 rounded mb-6"
    required
>


<%-- =============================== --%>
<%-- BOTONES DEL FORMULARIO --%>
<%-- =============================== --%>

<div class="flex space-x-4">


<%-- BOTÓN GUARDAR --%>
<button
    type="submit"
    class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
>

Guardar

</button>


<%-- BOTÓN CANCELAR --%>
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


<%-- =============================== --%>
<%-- FOOTER DEL SISTEMA --%>
<%-- =============================== --%>

<jsp:include page="../includes/footer.jsp" />


</body>

</html>