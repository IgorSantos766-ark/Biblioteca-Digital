package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.ErroBancoException;
import model.Usuario;

public class UsuarioDAO {

    private Connection conexao;

    public UsuarioDAO() {
        this.conexao = ConexaoBanco.getConnection();
    }

    // ----------------------------------------------------------
    // INSERIR USUÁRIO
    // ----------------------------------------------------------
    public void inserir(Usuario usuario) {

        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao inserir usuário: " + usuario.getNome(), e);
        }
    }

    // ----------------------------------------------------------
    // BUSCAR POR ID
    // ----------------------------------------------------------
    public Usuario buscarPorId(int idUsuario) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

            return null;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao buscar usuário por ID: " + idUsuario, e);
        }
    }

    // ----------------------------------------------------------
    // BUSCAR POR NOME
    // ----------------------------------------------------------
    public Usuario buscarPorNome(String nome) {

        String sql = "SELECT * FROM usuarios WHERE nome = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

            return null;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao buscar usuário por nome: " + nome, e);
        }
    }

    // ----------------------------------------------------------
    // LISTAR TODOS
    // ----------------------------------------------------------
    public List<Usuario> listarTodos() {

        String sql = "SELECT * FROM usuarios";
        List<Usuario> lista = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }

            return lista;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao listar usuários.", e);
        }
    }

    // ----------------------------------------------------------
    // REMOVER
    // ----------------------------------------------------------
    public boolean remover(int idUsuario) {

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao remover usuário ID = " + idUsuario, e);
        }
    }
}
