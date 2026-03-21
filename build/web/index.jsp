<!DOCTYPE html>
<!-- Define que el documento es HTML5 -->

<html>

<head>

    <!-- Título de la página -->
    <title>Store Shoe</title>

    <!-- Codificación de caracteres -->
    <meta charset="UTF-8">

    <!-- Responsive -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Cargar Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body class="bg-gray-100">

    <!-- MENÚ -->
    <nav class="bg-slate-800">

        <div class="max-w-7xl mx-auto px-4">

            <div class="flex justify-between items-center h-16">

                <div class="text-white text-xl font-bold">
                    Ecommers Store Shoe 
                </div>

                <div class="flex space-x-6">

                    <a href="${pageContext.request.contextPath}/index.jsp"
                       class="text-gray-300 hover:text-white">
                        Inicio
                    </a>

                    <!-- Cambiado para pasar por el Servlet -->
                    <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar"
                       class="text-gray-300 hover:text-white">
                        Usuarios
                    </a>

                </div>

            </div>

        </div>

    </nav>

    <!-- CONTENIDO PRINCIPAL -->
    <div class="max-w-4xl mx-auto mt-10 text-center">

        <!-- Título -->
        <h1 class="text-3xl font-bold text-gray-700">
            Store Shoe
        </h1>

        <!-- Boton para ir a usuarios -->
        <!-- Cambiado para pasar por el Servlet -->
        <a href="UsuarioServlet?accion=listar">

            <a href="${pageContext.request.contextPath}/UsuarioServlet?accion=listar">
                <button class="mt-6 bg-blue-500 text-white px-6 py-3 rounded hover:bg-blue-600">
                    Ver Usuarios
                </button>
            </a>

        </a>

    </div>

    <!-- FOOTER -->
    <footer class="bg-slate-800 mt-10">

        <div class="max-w-7xl mx-auto px-4 py-6 text-center">

            <p class="text-gray-300">
                Sistema CRUD Usuarios con Java Web
            </p>

            <p class="text-gray-400 text-sm">
                Desarrollado por Brahian Diaz
            </p>

        </div>

    </footer>

</body>

</html>