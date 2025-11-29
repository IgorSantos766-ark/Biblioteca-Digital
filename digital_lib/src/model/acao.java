package model;

import java.time.LocalDateTime;

public abstract class acao {

    private String usuarioResponsavel;
    private LocalDateTime dataHora;

    public acao(String usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataHora = LocalDateTime.now();
    }

    public String getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    // Método obrigatório que cada ação deve implementar
    public abstract void executar();

    @Override
    public String toString() {
        return "Ação realizada por: " + usuarioResponsavel +
                " às " + dataHora;
    }
}
