package service;

import dao.CategoriaDAO;
import modelo.Categoria;

import java.util.List;

public class CategoriaService {
    CategoriaDAO dao = new CategoriaDAO();

    public List<Categoria> listar() {
        return dao.listarCategorias();
    }
}