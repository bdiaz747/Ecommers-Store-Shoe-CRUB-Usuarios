package dao;

import conexion.ConexionDB;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERTAR
    public void insertar(Usuario u) throws Exception {

        String sql = "INSERT INTO usuarios(nombre,correo,clave) VALUES(?,?,?)";

        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, u.getNombre());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getClave());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    // LISTAR
    public List<Usuario> listar() throws Exception {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Usuario u = new Usuario();

            u.setId(rs.getInt("id"));
            u.setNombre(rs.getString("nombre"));
            u.setCorreo(rs.getString("correo"));
            u.setClave(rs.getString("clave"));

            lista.add(u);
        }

        rs.close();
        ps.close();
        conn.close();

        return lista;
    }

    // BUSCAR POR ID:
    public Usuario buscar(int id) throws Exception {

    Usuario u = null;

    String sql = "SELECT * FROM usuarios WHERE id=?";

    Connection conn = ConexionDB.conectar();
    PreparedStatement ps = conn.prepareStatement(sql);

    ps.setInt(1, id);

    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setCorreo(rs.getString("correo"));
        u.setClave(rs.getString("clave"));
    }

    rs.close();
    ps.close();
    conn.close();

    return u;
}
    // ACTUALIZAR
    public void actualizar(Usuario u) throws Exception {

        String sql = "UPDATE usuarios SET nombre=?, correo=?, clave=? WHERE id=?";

        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, u.getNombre());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getClave());
        ps.setInt(4, u.getId());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    // ELIMINAR
    public void eliminar(int id) throws Exception {

        String sql = "DELETE FROM usuarios WHERE id=?";

        Connection conn = ConexionDB.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}