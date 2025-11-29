package model;

import datastructures.ListaArquivos;
import exceptions.ArquivoNaoEncontradoException;
import exceptions.PermissaoNegadaException;  // IMPORT NECESSÁRIO

public class Pasta {

    private int id;                 // ID no banco
    private String nome;            // Nome da pasta
    private int idUsuarioDono;      // FK do usuário que é dono da pasta
    

    // Estrutura de arquivos
    private ListaArquivos arquivos;

    // ------------------------------------------------------
    // CONSTRUTORES
    // ------------------------------------------------------

    // Construtor completo (vindo do banco)
    public Pasta(int id, String nome, int idUsuarioDono) {
        this.id = id;
        this.nome = nome;
        this.idUsuarioDono = idUsuarioDono;
        this.arquivos = new ListaArquivos();
    }

    // Construtor para nova pasta (antes de inserir no banco)
    public Pasta(String nome, int idUsuarioDono) {
        this.nome = nome;
        this.idUsuarioDono = idUsuarioDono;
        this.arquivos = new ListaArquivos();
    }

    // ------------------------------------------------------
    // GETTERS E SETTERS
    // ------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUsuarioDono() {
        return idUsuarioDono;
    }

    public void setIdUsuarioDono(int idUsuarioDono) {
        this.idUsuarioDono = idUsuarioDono;
    }

    public ListaArquivos getArquivos() {
        return arquivos;
    }

    // ------------------------------------------------------
    // MÉTODOS DE NEGÓCIO
    // ------------------------------------------------------

    public void adicionarArquivo(Arquivo arquivo) {
        arquivos.adicionar(arquivo);
    }

    public boolean removerArquivo(String nomeArquivo) {
        try {
            return arquivos.remover(nomeArquivo);
        } catch (ArquivoNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Arquivo buscarArquivo(String nomeArquivo) {
        try {
            return arquivos.buscar(nomeArquivo);
        } catch (ArquivoNaoEncontradoException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // ------------------------------------------------------
    // PERMISSÃO DE ACESSO
    // ------------------------------------------------------

    public void verificarPermissao(int idUsuarioAcionando) {
        if (this.idUsuarioDono != idUsuarioAcionando) {
            throw new PermissaoNegadaException(
                "Usuário " + idUsuarioAcionando +
                " não tem permissão para acessar a pasta '" + this.nome + "'."
            );
        }
    }

    // ------------------------------------------------------
    @Override
    public String toString() {
        return "Pasta { id=" + id +
                ", nome='" + nome + '\'' +
                ", arquivos=" + arquivos.tamanho() +
                " }";
    }
}
