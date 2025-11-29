package model;

import datastructures.ListaArquivos;

public class CriarArquivo extends acao {

    private Arquivo arquivo;
    private ListaArquivos listaArquivos;

    public CriarArquivo(String usuarioResponsavel, Arquivo arquivo, ListaArquivos listaArquivos) {
        super(usuarioResponsavel);
        this.arquivo = arquivo;
        this.listaArquivos = listaArquivos;
    }

    @Override
    public void executar() {
        listaArquivos.adicionar(arquivo);
        System.out.println("Arquivo criado: " + arquivo.getNome());
    }
}
