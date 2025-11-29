package datastructures;


public class Solicitacao {

    private int idSolicitacao;
    private int idUsuarioSolicitante;
    private int idUsuarioAlvo;
    private String status;

    public Solicitacao(int idSolicitacao, int idUsuarioSolicitante, int idUsuarioAlvo, String status) {
        this.idSolicitacao = idSolicitacao;
        this.idUsuarioSolicitante = idUsuarioSolicitante;
        this.idUsuarioAlvo = idUsuarioAlvo;
        this.status = status;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public int getIdUsuarioSolicitante() {
        return idUsuarioSolicitante;
    }

    public int getIdUsuarioAlvo() {
        return idUsuarioAlvo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String novoStatus) {
        this.status = novoStatus;
    }

    @Override
    public String toString() {
        return "Solicitação { " +
                "ID=" + idSolicitacao +
                ", solicitante=" + idUsuarioSolicitante +
                ", alvo=" + idUsuarioAlvo +
                ", status='" + status + '\'' +
                " }";
    }
}
