package br.com.catalogo.dao;

import br.com.catalogo.model.ItemMidia;
import br.com.catalogo.util.FabricaDeConexoes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemMidiaDAOImpl implements ItemMidiaDAO {

    @Override
    public void inserir(ItemMidia item) {
        String sql = "INSERT INTO item_midia (titulo, autor_diretor, ano_lancamento, genero, sinopse, tipo_midia) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir item de mídia: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ItemMidia> listar() {
        String sql = "SELECT * FROM item_midia ORDER BY id DESC";
        List<ItemMidia> itens = new ArrayList<>();

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItemMidia item = new ItemMidia();
                item.setId(rs.getInt("id"));
                item.setTitulo(rs.getString("titulo"));
                item.setAutorDiretor(rs.getString("autor_diretor"));
                item.setAnoLancamento(rs.getInt("ano_lancamento"));
                item.setGenero(rs.getString("genero"));
                item.setSinopse(rs.getString("sinopse"));
                item.setTipoMidia(rs.getString("tipo_midia"));
                itens.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens de mídia: " + e.getMessage(), e);
        }
        return itens;
    }

    @Override
    public ItemMidia buscarPorId(int id) {
        String sql = "SELECT * FROM item_midia WHERE id = ?";
        ItemMidia item = null;

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = new ItemMidia();
                    item.setId(rs.getInt("id"));
                    item.setTitulo(rs.getString("titulo"));
                    item.setAutorDiretor(rs.getString("autor_diretor"));
                    item.setAnoLancamento(rs.getInt("ano_lancamento"));
                    item.setGenero(rs.getString("genero"));
                    item.setSinopse(rs.getString("sinopse"));
                    item.setTipoMidia(rs.getString("tipo_midia"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar item por ID: " + e.getMessage(), e);
        }
        return item;
    }

    @Override
    public void atualizar(ItemMidia item) {
        String sql = "UPDATE item_midia SET titulo = ?, autor_diretor = ?, ano_lancamento = ?, genero = ?, sinopse = ?, tipo_midia = ? WHERE id = ?";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getTitulo());
            stmt.setString(2, item.getAutorDiretor());
            stmt.setInt(3, item.getAnoLancamento());
            stmt.setString(4, item.getGenero());
            stmt.setString(5, item.getSinopse());
            stmt.setString(6, item.getTipoMidia());
            stmt.setInt(7, item.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar item de mídia: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM item_midia WHERE id = ?";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir item de mídia: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ItemMidia> buscarPorTituloOuAutor(String termo) {
        String sql = "SELECT * FROM item_midia WHERE titulo LIKE ? OR autor_diretor LIKE ?";
        List<ItemMidia> itens = new ArrayList<>();
        String parametroBusca = "%" + termo + "%";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parametroBusca);
            stmt.setString(2, parametroBusca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemMidia item = new ItemMidia();
                    item.setId(rs.getInt("id"));
                    item.setTitulo(rs.getString("titulo"));
                    item.setAutorDiretor(rs.getString("autor_diretor"));
                    item.setAnoLancamento(rs.getInt("ano_lancamento"));
                    item.setGenero(rs.getString("genero"));
                    item.setSinopse(rs.getString("sinopse"));
                    item.setTipoMidia(rs.getString("tipo_midia"));
                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar itens por título ou autor: " + e.getMessage(), e);
        }
        return itens;
    }
}