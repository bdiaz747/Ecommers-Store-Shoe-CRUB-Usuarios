<%@ page import="modelo.Producto" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>

<html>

<head>

    <!-- Título de la pestaña -->
    <title>Productos</title>

    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-100">

    <!-- ============================= -->
    <!-- MENÚ DEL SISTEMA -->
    <!-- ============================= -->
    <jsp:include page="../includes/menu.jsp" />


    <!-- ============================= -->
    <!-- OBTENER LISTA DESDE EL SERVLET -->
    <!-- ============================= -->
    <%
        // Recibe los productos enviados desde el servlet
        List<Producto> lista = (List<Producto>) request.getAttribute("productos");
    %>


    <!-- ============================= -->
    <!-- CONTENEDOR PRINCIPAL -->
    <!-- ============================= -->
    <div class="max-w-5xl mx-auto mt-10 bg-white p-8 rounded shadow">

        <!-- TÍTULO -->
        <h1 class="text-3xl font-bold text-blue-600 mb-6">
            Productos
        </h1>

        <!-- BOTÓN CREAR (aún no conectado) -->
        <a href="ProductoServlet?accion=nuevo">
            <button class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
                Crear nuevo producto
            </button>
        </a>

        <br><br>

        <!-- ============================= -->
        <!-- TABLA DE PRODUCTOS -->
        <!-- ============================= -->
        <table class="min-w-full border border-gray-200">

            <!-- ENCABEZADOS -->
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
                // Verifica si hay productos
                if (lista != null && !lista.isEmpty()) {

                    // Recorre cada producto
                    for (Producto p : lista) {
            %>

            <tr class="border-t">

                <!-- DATOS DEL PRODUCTO -->
                <td class="px-4 py-2"><%= p.getIdProducto() %></td>
                <td class="px-4 py-2"><%= p.getIdCategoria() %></td>
                <td class="px-4 py-2"><%= p.getNombreProducto() %></td>
                <td class="px-4 py-2"><%= p.getMarcaProducto() %></td>
                <td class="px-4 py-2"><%= p.getDescripcionProducto() %></td>
                <td class="px-4 py-2">$ <%= p.getPrecioProducto() %></td>
                <td class="px-4 py-2"><%= p.getStockProducto() %></td>
                <td class="px-4 py-2"><%= p.getImagenProducto() %></td>

                <!-- ============================= -->
                <!-- ACCIONES -->
                <!-- ============================= -->
                <td class="px-4 py-2">

                    <!-- EDITAR -->
                    <!-- Envía el ID por URL al servlet -->
                    <a href="ProductoServlet?accion=editar&id=<%=p.getIdProducto()%>"
                       class="text-blue-500 hover:underline">
                        Editar
                    </a>

                    |

                    <!-- ELIMINAR -->
                    <!-- Formulario POST para eliminar -->
                    <form action="ProductoServlet" method="POST" style="display:inline">

                        <!-- ID del producto -->
                        <input type="hidden" name="id" value="<%=p.getIdProducto()%>">

                        <!-- Acción eliminar -->
                        <input type="hidden" name="accion" value="eliminar">

                        <!-- Botón eliminar -->
                        <input 
                            type="submit" 
                            value="Eliminar"
                            class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
                            onclick="return confirm('¿Seguro que deseas eliminar este producto?')">
                    </form>

                </td>

            </tr>

            <%
                    }
                } else {
            %>

            <!-- MENSAJE SI NO HAY PRODUCTOS -->
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


    <!-- ============================= -->
    <!-- FOOTER -->
    <!-- ============================= -->
    <jsp:include page="../includes/footer.jsp" />

</body>

</html>