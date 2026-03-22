// Creado por Brahian Díaz G, 21-MAR-2026

package service;
// Define el paquete de la clase de servicio

import dao.UsuarioDAO;
// Importa la clase DAO para acceso a datos

import modelo.Usuario;
// Importa la entidad Usuario

import java.util.List;
// Importa la interfaz List utilizada en el DAO


public class UsuarioService {
// Clase que intermedia entre el Servlet y el DAO

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    // Instancia del DAO para ejecutar operaciones de base de datos


    public List<Usuario> listarUsuarios() throws Exception {
    // Retorna la lista de usuarios

        return usuarioDAO.listar();
        // Ejecuta la consulta de listado en el DAO
    }


    public Usuario obtenerUsuario(int id) throws Exception {
    // Retorna un usuario según su ID

        return usuarioDAO.buscar(id);
        // Ejecuta la búsqueda por ID en el DAO
    }


    public void guardarUsuario(Usuario usuario) throws Exception {
    // Inserta o actualiza un usuario

        if (usuario.getId() == 0) {
        // Verifica si el usuario es nuevo

            usuarioDAO.insertar(usuario);
            // Inserta el usuario en la base de datos

        } else {
        // Verifica si el usuario ya existe

            usuarioDAO.actualizar(usuario);
            // Actualiza el usuario en la base de datos
        }
    }


    public void eliminarUsuario(int id) throws Exception {
    // Elimina un usuario por ID

        usuarioDAO.eliminar(id);
        // Ejecuta la eliminación en el DAO
    }
}