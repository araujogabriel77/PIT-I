package br.com.catalogo.service;

import br.com.catalogo.dao.AvaliacaoDAO;
import br.com.catalogo.dao.ItemMidiaDAO;
import br.com.catalogo.dao.ItemMidiaDAOImpl;
import br.com.catalogo.model.Avaliacao;
import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.util.ValidadorUtil;

import java.util.List;

public class ItemMidiaService {

    private ItemMidiaDAO itemMidiaDAO;
    private AvaliacaoDAO avaliacaoDAO;

    public ItemMidiaService() {
        this.itemMidiaDAO = new ItemMidiaDAOImpl();
        this.avaliacaoDAO = new AvaliacaoDAO();
    }

    public void cadastrar(ItemMidia item) {
        List<String> erros = ValidadorUtil.validarCamposObrigatorios(item);
        if (!erros.isEmpty()) {
            throw new IllegalArgumentException(String.join(" | ", erros));
        }
        itemMidiaDAO.inserir(item);
    }

    public List<ItemMidia> listarTodos() {
        return itemMidiaDAO.listar();
    }

    public ItemMidia buscarPorId(int id) {
        return itemMidiaDAO.buscarPorId(id);
    }

    public void atualizar(ItemMidia item) {
        List<String> erros = ValidadorUtil.validarCamposObrigatorios(item);
        if (!erros.isEmpty()) {
            throw new IllegalArgumentException(String.join(" | ", erros));
        }
        itemMidiaDAO.atualizar(item);
    }

    public void excluir(int id) {
        itemMidiaDAO.excluir(id);
    }

    public List<ItemMidia> buscar(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodos();
        }
        return itemMidiaDAO.buscarPorTituloOuAutor(termo);
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (!ValidadorUtil.validarNota(avaliacao.getNota())) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5.");
        }
        avaliacaoDAO.inserir(avaliacao);
    }

    public List<Avaliacao> listarAvaliacoes(int itemMidiaId) {
        return avaliacaoDAO.listarPorItem(itemMidiaId);
    }

    public double calcularMediaAvaliacoes(int itemMidiaId) {
        return avaliacaoDAO.calcularMedia(itemMidiaId);
    }
}