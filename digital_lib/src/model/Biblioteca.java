package model;

import datastructures.FilaSolicitacoes;
import datastructures.PilhaAcoes;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    // Lista de usuários cadastrados
    private List<Usuario> usuarios;

    // Estruturas de dados próprias
    private FilaSolicitacoes filaSolicitacoes;
    private PilhaAcoes pilhaAcoes;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.filaSolicitacoes = new FilaSolicitacoes();
        this.pilhaAcoes = new PilhaAcoes();
    }

    // ------------------------------
    //         GERENCIAR USUÁRIOS
    // ------------------------------

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // ------------------------------
    //      SOLICITAÇÕES DE ACESSO
    // ------------------------------

    public void registrarSolicitacao(SolicitacaoAcesso solicitacao) {
        filaSolicitacoes.enfileirar(solicitacao);
    }

    public SolicitacaoAcesso proximaSolicitacao() {
        return filaSolicitacoes.desenfileirar();
    }

    // ------------------------------
    //       AÇÕES (PILHA)
    // ------------------------------

    public void registrarAcao(acao acao) {
        pilhaAcoes.empilhar(acao);
    }

    public acao desfazerUltimaAcao() {
        return pilhaAcoes.desempilhar();
    }

    // ------------------------------
    //        BUSCAS
    // ------------------------------

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Biblioteca { usuarios=" + usuarios.size() + " }";
    }
}
