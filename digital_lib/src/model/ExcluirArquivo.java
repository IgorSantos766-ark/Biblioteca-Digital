package model;

import datastructures.ListaArquivos;
import exceptions.ArquivoNaoEncontradoException;

public class ExcluirArquivo extends acao {

    private String nomeArquivo;
    private ListaArquivos listaArquivos;

    public ExcluirArquivo(String usuarioResponsavel, String nomeArquivo, ListaArquivos listaArquivos) {
        super(usuarioResponsavel);
        this.nomeArquivo = nomeArquivo;
        this.listaArquivos = listaArquivos;
    }

   @Override
public void executar() {
    try {
        // agora buscar() lança exceção
        Arquivo arquivo = listaArquivos.buscar(nomeArquivo);

        // Remover o arquivo encontrado
        for (int i = 0; i < listaArquivos.tamanho(); i++) {
            if (listaArquivos.get(i).getNome().equalsIgnoreCase(nomeArquivo)) {
                listaArquivos.remover(i);
                System.out.println("Arquivo excluído: " + nomeArquivo);
                return;
            }
        }

    } catch (ArquivoNaoEncontradoException e) {
        throw new RuntimeException(
            "Arquivo '" + nomeArquivo + "' não encontrado para exclusão."
        );
    }
}



}
