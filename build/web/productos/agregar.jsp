<%@ page import="modelo.Categoria" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<head>
    <title>Agregar Producto</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">

    <!-- MENÚ -->
    <jsp:include page="../includes/menu.jsp" />

    <%
        // Lista de categorías enviada desde el servlet
        List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    %>

    <div class="max-w-4xl mx-auto mt-10 bg-white p-8 rounded shadow">

        <h1 class="text-2xl font-bold text-blue-600 mb-6">
            Crear nuevo producto
        </h1>

        <!-- FORMULARIO -->
        <form action="ProductoServlet" method="POST" enctype="multipart/form-data">

            <!-- ACCIÓN -->
            <input type="hidden" name="accion" value="guardar">

            <!-- CATEGORÍA -->
            <label class="block mb-2 font-semibold">Categoría</label>
            <select name="id_categoria" class="w-full border p-2 mb-4" required>
                <option value="">Seleccione</option>

                <%
                    if (categorias != null) {
                        for (Categoria c : categorias) {
                %>
                    <option value="<%= c.getIdCategoria() %>">
                        <%= c.getNombreCategoria() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>

            <!-- NOMBRE -->
            <label class="block mb-2 font-semibold">Nombre</label>
            <input type="text" name="nombre_producto" class="w-full border p-2 mb-4" required>

            <!-- MARCA -->
            <label class="block mb-2 font-semibold">Marca</label>
            <input type="text" name="marca_producto" class="w-full border p-2 mb-4" required>

            <!-- DESCRIPCIÓN -->
            <label class="block mb-2 font-semibold">Descripción</label>
            <textarea name="descripcion_producto" class="w-full border p-2 mb-4" required></textarea>

            <!-- PRECIO -->
            <label class="block mb-2 font-semibold">Precio</label>
            <input type="number" step="0.01" name="precio_producto" class="w-full border p-2 mb-4" required>

            <!-- STOCK -->
            <label class="block mb-2 font-semibold">Stock</label>
            <input type="number" name="stock_producto" class="w-full border p-2 mb-4" required>

            <!-- IMAGEN -->
            <label class="block mb-2 font-semibold">Imagen</label>
            <input type="file" name="imagen_producto" class="w-full border p-2 mb-4">

            <!-- BOTONES -->
            <div class="flex gap-4">

                <button type="submit"
                        class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
                    Guardar
                </button>

                <a href="ProductoServlet?accion=listarAdmin"
                   class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">
                    Cancelar
                </a>

            </div>

        </form>

    </div>

    <!-- FOOTER -->
    <jsp:include page="../includes/footer.jsp" />

</body>
</html>