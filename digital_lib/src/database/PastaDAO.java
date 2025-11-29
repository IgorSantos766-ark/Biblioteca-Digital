package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ErroBancoException;
import model.Pasta;

public class PastaDAO {

    private static final String URL =
        "jdbc:mysql://localhost:3306/biblioteca?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASSWORD = "4rkMy5QLS@nt05";

    private static Connection conexao = null;

    // -------------------------------------------------------
    // SINGLETON DE CONEXÃO
    // -------------------------------------------------------
    public static Connection getConnection() {

        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conectado ao MySQL!");
            }
            return conexao;

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao conectar ao MySQL.", e);
        }
    }

    // -------------------------------------------------------
    // FECHAR CONEXÃO
    // -------------------------------------------------------
    public static void fechar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão encerrada.");
            }

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao fechar conexão MySQL.", e);
        }
    }

    // ----------------------------------------------------------
    // BUSCAR PASTA POR ID
    // ----------------------------------------------------------
    public Pasta buscarPorId(int idPasta) {

        String sql = "SELECT * FROM pastas WHERE id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setInt(1, idPasta);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pasta(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idUsuario")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao buscar pasta ID: " + idPasta, e);
        }
    }

    // ----------------------------------------------------------
    // INSERIR PASTA
    // ----------------------------------------------------------
    public void inserir(Pasta pasta) {

        String sql = "INSERT INTO pastas (nome, idUsuario) VALUES (?, ?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, pasta.getNome());
            stmt.setInt(2, pasta.getIdUsuarioDono());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pasta.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao inserir pasta: " + pasta.getNome(), e);
        }
    }

    // ----------------------------------------------------------
    // LISTAR PASTAS POR USUÁRIO
    // ----------------------------------------------------------
    public List<Pasta> listarPorUsuario(int idUsuario) {

        String sql = "SELECT * FROM pastas WHERE idUsuario = ?";
        List<Pasta> lista = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Pasta(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idUsuario")
                ));
            }

            return lista;

        } catch (SQLException e) {
            throw new ErroBancoException("Erro ao listar pastas do usuário " + idUsuario, e);
        }
    }
}
