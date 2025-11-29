package database;

import database.ConexaoBanco;
import model.Arquivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoDAO {

    // ============================
    //     INSERIR ARQUIVO
    // ============================
    public Arquivo inserir(Arquivo arquivo, int idPasta) {

        final String sql = "INSERT INTO arquivo (nome, id_pasta) VALUES (?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, arquivo.getNome());
            stmt.setInt(2, idPasta);
            stmt.executeUpdate();

            // pega a primary key gerada pelo banco
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    arquivo.setId(rs.getInt(1));
                }
            }

            return arquivo;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir arquivo: " + e.getMessage(), e);
        }
    }


    // ============================
    //     REMOVER ARQUIVO
    // ============================
    public boolean remover(int idPasta, String nomeArquivo) {

        final String sql = "DELETE FROM arquivo WHERE nome = ? AND id_pasta = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeArquivo);
            stmt.setInt(2, idPasta);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover arquivo: " + e.getMessage(), e);
        }
    }


    // ============================
    //     BUSCAR ARQUIVO
    // ============================
    public Arquivo buscar(int idPasta, String nomeArquivo) {

        final String sql = "SELECT * FROM arquivo WHERE id_pasta = ? AND nome = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPasta);
            stmt.setString(2, nomeArquivo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Arquivo arq = new Arquivo(rs.getString("nome"));
                    arq.setId(rs.getInt("id"));
                    return arq;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar arquivo: " + e.getMessage(), e);
        }
    }


    // ============================
    //   LISTAR ARQUIVOS DA PASTA
    // ============================
    public List<Arquivo> listarPorPasta(int idPasta) {

        final String sql = "SELECT * FROM arquivo WHERE id_pasta = ? ORDER BY nome";

        List<Arquivo> lista = new ArrayList<>();

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPasta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Arquivo arq = new Arquivo(rs.getString("nome"));
                    arq.setId(rs.getInt("id"));
                    lista.add(arq);
                }
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar arquivos: " + e.getMessage(), e);
        }
    }


    // ============================
    //        MOVER ARQUIVO
    // ============================
    public boolean mover(int idOrigem, int idDestino, String nomeArquivo) {

        final String sql = "UPDATE arquivo SET id_pasta = ? WHERE id_pasta = ? AND nome = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idDestino);
            stmt.setInt(2, idOrigem);
            stmt.setString(3, nomeArquivo);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao mover arquivo: " + e.getMessage(), e);
        }
    }
}
