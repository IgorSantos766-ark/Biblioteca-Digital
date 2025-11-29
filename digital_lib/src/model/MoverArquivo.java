package model;

import datastructures.ListaArquivos;
import exceptions.ArquivoNaoEncontradoException;

public class MoverArquivo extends acao {

    private String nomeArquivo;
    private ListaArquivos origem;
    private ListaArquivos destino;

    public MoverArquivo(String usuarioResponsavel, String nomeArquivo, 
                        ListaArquivos origem, ListaArquivos destino) {
        super(usuarioResponsavel);
        this.nomeArquivo = nomeArquivo;
        this.origem = origem;
        this.destino = destino;
    }

    @Override
public void executar() {
    Arquivo arquivo;
    try {
        arquivo = origem.buscar(nomeArquivo);
    } catch (ArquivoNaoEncontradoException e) {
        throw new RuntimeException("Arquivo '" + nomeArquivo + "' não encontrado na pasta de origem.");
    }

    for (int i = 0; i < origem.tamanho(); i++) {
        if (origem.get(i).getNome().equalsIgnoreCase(nomeArquivo)) {
            origem.remover(i);
            break;
        }
    }

    destino.adicionar(arquivo);
    System.out.println("Arquivo movido: " + nomeArquivo + " -> para destino.");
}

}
