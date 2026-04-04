<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Producto" %>

<!DOCTYPE html>
<html>

<head>
    <title>Store Shoe</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">

    <!-- MENÚ -->
    <jsp:include page="includes/menu.jsp" />

    <!-- CATÁLOGO DE PRODUCTOS -->
    <div class="max-w-7xl mx-auto mt-10 px-4">

        <h1 class="text-3xl font-bold text-gray-700 text-center mb-8">
            Catálogo de Productos
        </h1>

        <%
            List<Producto> lista = (List<Producto>) request.getAttribute("productos");
        %>

        <!-- VALIDACIÓN: SIN PRODUCTOS -->
        <%
            if (lista == null || lista.isEmpty()) {
        %>
            <div class="text-center">
                <p class="text-gray-500 text-lg">
                    No hay productos disponibles
                </p>
            </div>
        <%
            } else {
        %>

        <!-- GRID DE PRODUCTOS -->
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">

            <%
                for (Producto p : lista) {

                    String imgPath = p.getImagenProducto();

                    // 🔴 CONTROL SEGURO
                    if (imgPath == null || imgPath.isEmpty()) {
                        imgPath = "img/productos/default.png";
                    }
            %>

            <!-- Card de producto -->
            <div class="bg-white rounded shadow p-4">

                <!-- Imagen -->
                <img src="${pageContext.request.contextPath}/<%= imgPath %>"
                     class="w-full h-48 object-cover rounded"
                     alt="<%= p.getNombreProducto() %>">

                <!-- Nombre -->
                <h2 class="text-xl font-bold mt-3">
                    <%= p.getNombreProducto() %>
                </h2>

                <!-- Marca -->
                <p class="text-gray-500">
                    <%= p.getMarcaProducto() %>
                </p>

                <!-- Precio -->
                <p class="text-green-600 font-bold text-lg mt-2">
                    $<%= p.getPrecioProducto() %>
                </p>

            </div>

            <%
                }
            %>

        </div>

        <%
            }
        %>

    </div>

    <!-- FOOTER -->
    <footer class="bg-slate-800 mt-10">
        <div class="max-w-7xl mx-auto px-4 py-6 text-center">
            <p class="text-gray-300">Sistema CRUD con Java Web</p>
            <p class="text-gray-400 text-sm">Desarrollado por Brahian Diaz</p>
        </div>
    </footer>

</body>

</html>