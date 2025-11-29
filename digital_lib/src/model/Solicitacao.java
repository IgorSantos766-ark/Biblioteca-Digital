package model;

import java.time.LocalDateTime;

public class Solicitacao {

    private String usuario;
    private String recurso;
    private LocalDateTime dataHora;

    public Solicitacao(String usuario, String recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.dataHora = LocalDateTime.now();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getRecurso() {
        return recurso;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Solicitação de acesso: " + usuario +
               " -> " + recurso +
               " às " + dataHora; }

    private int id;
    private int idUsuarioSolicitante;
    private int idUsuarioDestino;
    private String status;

    public Solicitacao(int id, int idUsuarioSolicitante, int idUsuarioDestino, String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuarioSolicitante() {
        return idUsuarioSolicitante;
    }

    public int getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String novoStatus) {
        this.status = novoStatus;
    }
}

