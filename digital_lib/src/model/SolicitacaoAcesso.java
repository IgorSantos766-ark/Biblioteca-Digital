package model;

import java.util.List;

import exceptions.AcessoNegadoException;

public class SolicitacaoAcesso extends acao {

    private String recursoSolicitado;
    private List<Solicitacao> listaSolicitacoes;
    private boolean usuarioTemPermissao;


   public SolicitacaoAcesso(String usuarioResponsavel,
                         String recursoSolicitado,
                         List<Solicitacao> listaSolicitacoes,
                         boolean usuarioTemPermissao) {
    super(usuarioResponsavel);
    this.recursoSolicitado = recursoSolicitado;
    this.listaSolicitacoes = listaSolicitacoes;
    this.usuarioTemPermissao = usuarioTemPermissao;
}


    @Override
    public void executar() throws AcessoNegadoException {

        // Verifica permissão
        if (!usuarioTemPermissao) {
            throw new AcessoNegadoException(
                "Acesso negado ao recurso: " + recursoSolicitado
            );
        }

        // Grava a solicitação
        Solicitacao solicitacao = new Solicitacao(getUsuarioResponsavel(), recursoSolicitado);
        listaSolicitacoes.add(solicitacao);

        System.out.println("Solicitação registrada: " +
                getUsuarioResponsavel() +
                " pediu acesso a '" + recursoSolicitado + "'.");
    }
    
}
