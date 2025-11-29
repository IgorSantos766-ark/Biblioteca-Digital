package datastructures;

import model.SolicitacaoAcesso;

public class FilaSolicitacoes {

    private SolicitacaoAcesso[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;

    public FilaSolicitacoes() {
        this.elementos = new SolicitacaoAcesso[10];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    // Aumenta a capacidade do array quando estiver cheio
    private void aumentarCapacidade() {
        SolicitacaoAcesso[] novoArray = new SolicitacaoAcesso[elementos.length * 2];

        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = elementos[(inicio + i) % elementos.length];
        }

        elementos = novoArray;
        inicio = 0;
        fim = tamanho;
    }

    // ENFILEIRAR - inserir no final
    public void enfileirar(SolicitacaoAcesso solicitacao) {
        if (tamanho == elementos.length) {
            aumentarCapacidade();
        }

        elementos[fim] = solicitacao;
        fim = (fim + 1) % elementos.length;
        tamanho++;
    }

    // DESENFILEIRAR - remover do início
    public SolicitacaoAcesso desenfileirar() {
        if (estaVazia()) {
            return null;
        }

        SolicitacaoAcesso removido = elementos[inicio];
        elementos[inicio] = null;
        inicio = (inicio + 1) % elementos.length;
        tamanho--;

        return removido;
    }

    // Olhar o primeiro sem remover
    public SolicitacaoAcesso peek() {
        if (estaVazia()) return null;
        return elementos[inicio];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    // Listar todas as solicitações da fila
    public void listar() {
        System.out.println("Solicitações na fila:");

        for (int i = 0; i < tamanho; i++) {
            System.out.println("- " + elementos[(inicio + i) % elementos.length]);
        }
    }
}
