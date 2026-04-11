<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<head>
    <title>Productos</title>
    <meta charset="UTF-8">
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">

    <!-- MENÚ -->
    <jsp:include page="../includes/menu.jsp" />

    <!-- 🔒 FUNCIÓN SEGURA -->
    <%! 
        public String escapar(String texto) {
            if (texto == null) return "";
            return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
        }
    %>

    <%
        List<Producto> lista = (List<Producto>) request.getAttribute("productos");
    %>

    <div class="max-w-10xl mx-auto mt-10 bg-white p-8 rounded shadow">

        <h1 class="text-3xl font-bold text-blue-600 mb-6">
            Productos
        </h1>

        <a href="ProductoServlet?accion=nuevo">
            <button class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
                Crear nuevo producto
            </button>
        </a>

        <br><br>

        <table class="min-w-full border border-gray-200">

            <!-- HEADER -->
            <tr class="bg-gray-200">
                <th class="px-4 py-2 text-left">ID</th>
                <th class="px-4 py-2 text-left">Categoría</th>
                <th class="px-4 py-2 text-left">Nombre</th>
                <th class="px-4 py-2 text-left">Marca</th>
                <th class="px-4 py-2 text-left">Descripción</th>
                <th class="px-4 py-2 text-left">Precio</th>
                <th class="px-4 py-2 text-left">Stock</th>
                <th class="px-4 py-2 text-left">Imagen</th>
                <th class="px-4 py-2 text-left">Acciones</th>
            </tr>

            <%
                if (lista != null && !lista.isEmpty()) {
                    for (Producto p : lista) {
            %>

            <!-- FILA -->
            <tr class="border-t hover:bg-gray-50">

                <td class="px-4 py-2"><%= p.getIdProducto() %></td>
                <td class="px-4 py-2"><%= p.getIdCategoria() %></td>

                <!-- 🔒 TEXTO SEGURO -->
                <td class="px-4 py-2"><%= escapar(p.getNombreProducto()) %></td>
                <td class="px-4 py-2"><%= escapar(p.getMarcaProducto()) %></td>
                <td class="px-4 py-2"><%= escapar(p.getDescripcionProducto()) %></td>

                <td class="px-4 py-2">$ <%= p.getPrecioProducto() %></td>
                <td class="px-4 py-2"><%= p.getStockProducto() %></td>

                <td class="px-4 py-2"><%= escapar(p.getImagenProducto()) %></td>

                <!-- ACCIONES -->
                <td class="px-4 py-2">

                    <!-- EDITAR -->
                    <a href="ProductoServlet?accion=editar&id=<%=p.getIdProducto()%>"
                       class="text-blue-500 hover:underline font-semibold">
                        Editar
                    </a>

                    |

                    <!-- ELIMINAR -->
                    <form action="ProductoServlet" method="POST" style="display:inline">
                        <input type="hidden" name="id" value="<%=p.getIdProducto()%>">
                        <input type="hidden" name="accion" value="eliminar">

                        <input 
                            type="submit" 
                            value="Eliminar"
                            class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 ml-2"
                            onclick="return confirm('¿Seguro que deseas eliminar este producto?')">
                    </form>

                </td>

            </tr>

            <%
                    }
                } else {
            %>

            <!-- SIN DATOS -->
            <tr>
                <td colspan="9" class="px-4 py-2 text-center text-gray-500">
                    No hay productos
                </td>
            </tr>

            <%
                }
            %>

        </table>

    </div>

    <!-- FOOTER -->
    <jsp:include page="../includes/footer.jsp" />

</body>
</html>