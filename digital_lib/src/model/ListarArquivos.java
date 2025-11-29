package model;

import datastructures.ListaArquivos;

public class ListarArquivos extends acao {

    private ListaArquivos lista;

    public ListarArquivos(String usuarioResponsavel, ListaArquivos lista) {
        super(usuarioResponsavel);
        this.lista = lista;
    }

    @Override
    public void executar() {
        if (lista.tamanho() == 0) {
            System.out.println("Nenhum arquivo encontrado.");
            return;
        }

        System.out.println("=== Lista de Arquivos ===");

        for (int i = 0; i < lista.tamanho(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i).getNome());
        }
    }
}
