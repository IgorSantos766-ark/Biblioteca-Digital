package service;

import exceptions.AcessoNegadoException;
import exceptions.ErroBancoException;
import exceptions.SolicitacaoNaoEncontradaException;
import model.Solicitacao;
import datastructures.ListaSolicitacoes;

public class SolicitacaoService {

    private ListaSolicitacoes listaSolicitacao;

    public SolicitacaoService() {
        this.listaSolicitacao = new ListaSolicitacoes();
    }

    // ------------------------------------------
    // CRIAR SOLICITAÇÃO
    // ------------------------------------------
    public void criarSolicitacao(datastructures.Solicitacao solicitacao, int idUsuarioAcionando) {

        if (solicitacao.getIdUsuarioSolicitante() != idUsuarioAcionando) {
            throw new AcessoNegadoException("Usuário não pode criar solicitação para outro usuário.");
        }

        // Não precisa try/catch: ListaSolicitacoes é estrutura em memória
        listaSolicitacao.adicionar(solicitacao);
    }

    // ------------------------------------------
    // LISTAR SOLICITAÇÕES DO USUÁRIO
    // ------------------------------------------
    public ListaSolicitacoes listarSolicitacao(int idUsuario, int idUsuarioAcionando) {

        if (idUsuario != idUsuarioAcionando) {
            throw new AcessoNegadoException("Você não pode visualizar solicitações de outro usuário.");
        }

        return listaSolicitacao.filtrarPorUsuario(idUsuario);
    }

    // ------------------------------------------
    // BUSCAR SOLICITAÇÃO ESPECÍFICA
    // ------------------------------------------
    public Solicitacao buscarSolicitacao(int idSolicitacao, int idUsuarioAcionando) {

        // Se não existe, o método já lança SolicitacaoNaoEncontradaException
        Solicitacao sol = listaSolicitacoes.buscarPorId(idSolicitacao);

        if (sol.getIdUsuarioSolicitante() != idUsuarioAcionando) {
            throw new AcessoNegadoException("Usuário não tem permissão para acessar esta solicitação.");
        }

        return sol;
    }

    // ------------------------------------------
    // REMOVER SOLICITAÇÃO
    // ------------------------------------------
    public void removerSolicitacao(int idSolicitacao, int idUsuarioAcionando) throws SolicitacaoNaoEncontradaException {

        // Valida acesso e existência
        buscarSolicitacao(idSolicitacao, idUsuarioAcionando);

        // Operação em memória → não exige try/catch
        boolean removido = listaSolicitacoes.removerPorId(idSolicitacao);

        if (!removido) {
            throw new SolicitacaoNaoEncontradaException(
                    "Erro ao remover: solicitação ID " + idSolicitacao + " não existe."
            );
        }
    }

    // ------------------------------------------
    // ATUALIZAR STATUS DA SOLICITAÇÃO
    // ------------------------------------------
    public void atualizarStatus(int idSolicitacao, String novoStatus, int idUsuarioAcionando) {

        Solicitacao sol = buscarSolicitacao(idSolicitacao, idUsuarioAcionando);

        // Operação simples → remover try/catch
        sol.setStatus(novoStatus);
    }
}
