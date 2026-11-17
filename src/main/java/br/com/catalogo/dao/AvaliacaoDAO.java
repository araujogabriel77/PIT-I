package br.com.catalogo.dao;

import br.com.catalogo.model.Avaliacao;
import br.com.catalogo.util.FabricaDeConexoes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public void inserir(Avaliacao avaliacao) {
        String sql = "INSERT INTO avaliacao (item_midia_id, nota, comentario, data_avaliacao) VALUES (?, ?, ?, ?)";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, avaliacao.getItemMidiaId());
            stmt.setInt(2, avaliacao.getNota());
            stmt.setString(3, avaliacao.getComentario());
            stmt.setDate(4, Date.valueOf(avaliacao.getDataAvaliacao()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir avaliação: " + e.getMessage(), e);
        }
    }

    public List<Avaliacao> listarPorItem(int itemMidiaId) {
        String sql = "SELECT * FROM avaliacao WHERE item_midia_id = ? ORDER BY data_avaliacao DESC";
        List<Avaliacao> avaliacoes = new ArrayList<>();

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemMidiaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Avaliacao av = new Avaliacao();
                    av.setId(rs.getInt("id"));
                    av.setItemMidiaId(rs.getInt("item_midia_id"));
                    av.setNota(rs.getInt("nota"));
                    av.setComentario(rs.getString("comentario"));
                    av.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                    avaliacoes.add(av);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar avaliações do item: " + e.getMessage(), e);
        }
        return avaliacoes;
    }

    public double calcularMedia(int itemMidiaId) {
        String sql = "SELECT AVG(nota) AS media FROM avaliacao WHERE item_midia_id = ?";
        double media = 0.0;

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, itemMidiaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    media = rs.getDouble("media"); // Retorna 0.0 se não houver registros
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular média das avaliações: " + e.getMessage(), e);
        }
        return media;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM avaliacao WHERE id = ?";

        try (Connection conn = FabricaDeConexoes.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir avaliação: " + e.getMessage(), e);
        }
    }
}