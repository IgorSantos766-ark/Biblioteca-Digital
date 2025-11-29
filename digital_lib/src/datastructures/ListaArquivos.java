package datastructures;

import model.*;
import exceptions.ArquivoNaoEncontradoException;

public class ListaArquivos {

    private Arquivo[] elementos;
    private int tamanho;

    public ListaArquivos() {
        this.elementos = new Arquivo[10];
        this.tamanho = 0;
    }

    // Aumentar o array quando necessário
    private void aumentarCapacidade() {
        Arquivo[] novoArray = new Arquivo[elementos.length * 2];
        for (int i = 0; i < elementos.length; i++) {
            novoArray[i] = elementos[i];
        }	
        elementos = novoArray;
    }

    // Adicionar arquivo no fim
    public void adicionar(Arquivo arquivo) {
        if (tamanho == elementos.length) {
            aumentarCapacidade();
        }
        elementos[tamanho] = arquivo;
        tamanho++;
    }

    // Remover por índice
    public void remover(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido!");
        }

        for (int i = index; i < tamanho - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        tamanho--;
    }

    // Remover por nome
public boolean remover(String nomeArquivo) throws ArquivoNaoEncontradoException {
        for (int i = 0; i < tamanho; i++) {
            if (elementos[i].getNome().equalsIgnoreCase(nomeArquivo)) {
                remover(i);
                return false;
            }
        }
        throw new ArquivoNaoEncontradoException("Arquivo '" + nomeArquivo + "' não encontrado.");
    }

   public Arquivo buscar(String nome) throws ArquivoNaoEncontradoException {
    for (int i = 0; i < tamanho; i++) {
        if (elementos[i].getNome().equalsIgnoreCase(nome)) {
            return elementos[i];
        }
    }
    throw new ArquivoNaoEncontradoException("Arquivo '" + nome + "' não encontrado.");
}

    // Retornar quantidade
    public int tamanho() {
        return tamanho;
    }

    // Obter por índice
    public Arquivo get(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Índice inválido!");
        }
        return elementos[index];
    }

    // Listar todos
    public void listar() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(elementos[i]);
        }
    }
}
