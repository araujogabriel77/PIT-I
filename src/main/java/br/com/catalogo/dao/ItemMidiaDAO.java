package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import java.util.List;

public interface ItemMidiaDAO {
    void inserir(ItemMidia item);
    List<ItemMidia> listar();
    ItemMidia buscarPorId(int id);
    void atualizar(ItemMidia item);
    void excluir(int id);
    List<ItemMidia> buscarPorTituloOuAutor(String termo);
}