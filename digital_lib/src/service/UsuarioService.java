package service;

import database.UsuarioDAO;
import database.PastaDAO;
import exceptions.UsuarioNaoEncontradoException;
import exceptions.PastaNaoEncontradaException;
import exceptions.ErroBancoException;
import model.Usuario;
import model.Pasta;

import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;
    private PastaDAO pastaDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        this.pastaDAO = new PastaDAO();
    }

    // ============================================================
    // CRIAR USUÁRIO
    // ============================================================
    public void criarUsuario(Usuario usuario) {
        try {
            usuarioDAO.inserir(usuario);
        } catch (Exception e) {
            throw new ErroBancoException("Erro ao criar usuário: " + usuario.getNome(), e);
        }
    }

    // ============================================================
    // BUSCAR POR ID
    // ============================================================
    public Usuario buscarPorId(int idUsuario) {
        try {
            Usuario usuario = usuarioDAO.buscarPorId(idUsuario);

            if (usuario == null) {
                throw new UsuarioNaoEncontradoException("Usuário com ID " + idUsuario + " não encontrado.");
            }

            return usuario;

        } catch (UsuarioNaoEncontradoException e) {
            throw e;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao buscar usuário por ID: " + idUsuario, e);
        }
    }

    // ============================================================
    // BUSCAR POR NOME
    // ============================================================
    public Usuario buscarPorNome(String nome) {
        try {
            Usuario usuario = usuarioDAO.buscarPorNome(nome);

            if (usuario == null) {
                throw new UsuarioNaoEncontradoException("Usuário '" + nome + "' não encontrado.");
            }

            return usuario;

        } catch (UsuarioNaoEncontradoException e) {
            throw e;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao buscar usuário por nome: " + nome, e);
        }
    }

    // ============================================================
    // LISTAR TODOS
    // ============================================================
    public List<Usuario> listarUsuarios() {
        try {
            return usuarioDAO.listarTodos();
        } catch (Exception e) {
            throw new ErroBancoException("Erro ao listar usuários.", e);
        }
    }

    // ============================================================
    // REMOVER
    // ============================================================
    public void removerUsuario(int idUsuario) {
        try {
            boolean removido = usuarioDAO.remover(idUsuario);

            if (!removido) {
                throw new UsuarioNaoEncontradoException("Usuário com ID " + idUsuario + " não encontrado.");
            }

        } catch (UsuarioNaoEncontradoException e) {
            throw e;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao remover usuário: " + idUsuario, e);
        }
    }

    // ============================================================
    // CRUD PASTAS DO USUÁRIO
    // ============================================================

    public Pasta criarPasta(int idUsuario, String nomePasta) {
        Pasta pasta = new Pasta(nomePasta, idUsuario);

        try {
            pastaDAO.inserir(pasta);
            return pasta;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao criar pasta " + nomePasta, e);
        }
    }

    public List<Pasta> listarPastas(int idUsuario) {
        try {
            return pastaDAO.listarPorUsuario(idUsuario);

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao listar pastas do usuário " + idUsuario, e);
        }
    }

    public Pasta buscarPastaPorId(int idPasta) {
        try {
            Pasta pasta = pastaDAO.buscarPorId(idPasta);

            if (pasta == null) {
                throw new PastaNaoEncontradaException(idPasta);
            }

            return pasta;

        } catch (PastaNaoEncontradaException e) {
            throw e;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao buscar pasta ID " + idPasta, e);
        }
    }

	public boolean existeUsuario(int usuarioLogadoId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'existeUsuario'");
	}

    public Pasta getPasta(int idPasta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPasta'");
    }
}