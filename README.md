# 👟 Ecommerce Store Shoe – CRUD de Usuarios

![Java](https://img.shields.io/badge/Java-Web-orange)
![Tomcat](https://img.shields.io/badge/Apache-Tomcat-red)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![TailwindCSS](https://img.shields.io/badge/TailwindCSS-Frontend-38B2AC)
![Status](https://img.shields.io/badge/Status-Academic%20Project-green)

Módulo **CRUD de Usuarios** desarrollado para el proyecto **Ecommerce Store Shoe**.  
Este módulo permite gestionar los usuarios del sistema mediante las operaciones básicas de **crear, listar, actualizar y eliminar registros**.

El sistema fue desarrollado utilizando **Java Web (Servlets y JSP)**, ejecutado en **Apache Tomcat**, conectado a **MySQL mediante JDBC** y con una interfaz diseñada usando **Tailwind CSS**.

---

# 📌 Características

- Registro de usuarios
- Listado de usuarios
- Edición de usuarios
- Eliminación de usuarios
- Conexión a base de datos MySQL
- Interfaz con Tailwind CSS
- Implementación del patrón DAO
- Arquitectura MVC

---

# 🧰 Tecnologías utilizadas

| Tecnología | Descripción |
|-----------|-------------|
| Java | Lenguaje principal del backend |
| JSP | Vistas de la aplicación |
| Servlets | Controladores |
| MySQL | Base de datos |
| JDBC | Conexión a la base de datos |
| Apache Tomcat | Servidor de aplicaciones |
| Tailwind CSS | Framework de estilos |

---

# 🏗 Arquitectura del proyecto

El proyecto sigue el patrón **MVC (Model - View - Controller)**.

```
EcoStoreShoe
│
├── src
│   ├── modelo
│   │   └── Usuario.java
│   │
│   ├── dao
│   │   └── UsuarioDAO.java
│   │
│   ├── conexion
│   │   └── ConexionBD.java
│   │
│   └── servlet
│       └── UsuarioServlet.java
│
├── web
│   ├── usuarios
│   │   ├── listarUsuarios.jsp
│   │   ├── agregarUsuario.jsp
│   │   └── editarUsuario.jsp
│
└── WEB-INF
    └── web.xml
```

---

# 🗄 Base de datos

Base de datos utilizada:

```
eco_store
```

Creación de la tabla de usuarios:

```sql
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    clave VARCHAR(100)
);
```

---

# 🔌 Configuración de conexión

Ejemplo de conexión a MySQL usando JDBC:

```java
public class ConexionBD {

    public static Connection conectar() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/eco_store",
                    "root",
                    "123456"
            );

            System.out.println("Conexión exitosa a la base de datos!");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return conn;
    }
}
```

---

# 🚀 Instalación y ejecución

## 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/eco-store-shoe.git
```

---

## 2. Crear la base de datos

```sql
CREATE DATABASE eco_store;
```

Luego crear la tabla `usuarios`.

---

## 3. Ejecutar el proyecto

1. Instalar **Apache Tomcat**
2. Configurar Tomcat en **NetBeans**
3. Ejecutar el proyecto

---

## 4. Acceder al sistema

Abrir en el navegador:

```
http://localhost:8080/EcoStoreShoe
```

---

# 📷 Capturas del sistema

```
screenshots/
    inicio_strore_shoe.png
    lista_usuarios.png
    crear_usuario.png
    editar_usuario.png
```

Luego agregarlas en el README:

```
## Lista de usuarios

![Lista de usuarios](screenshots/lista_usuarios.png)
```

---

# 📚 Objetivo del proyecto

Este proyecto fue desarrollado con fines **académicos**, aplicando conceptos de:

- Desarrollo **Java Web**
- Conexión a bases de datos con **JDBC**
- Implementación de **CRUD**
- Uso del patrón **DAO**
- Arquitectura **MVC**

---

# 👨‍💻 Autor

**Brahian Díaz García**

Proyecto académico – SENA

---

# 📄 Licencia

Proyecto desarrollado con fines educativos.
