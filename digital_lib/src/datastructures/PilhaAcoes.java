package datastructures;

import model.acao;

public class PilhaAcoes {

    private acao[] elementos;
    private int topo;

    public PilhaAcoes() {
        this.elementos = new acao[10];
        this.topo = -1; // pilha começa vazia
    }

    // -----------------------------
    // Aumentar capacidade do array
    // -----------------------------
    private void aumentarCapacidade() {
        acao[] novoArray = new acao[elementos.length * 2];
        for (int i = 0; i < elementos.length; i++) {
            novoArray[i] = elementos[i];
        }
        elementos = novoArray;
    }

    // -----------------------------
    // Empilhar ação
    // -----------------------------
    public void empilhar(acao acao) {
        if (topo == elementos.length - 1) {
            aumentarCapacidade();
        }
        topo++;
        elementos[topo] = acao;
    }

    // -----------------------------
    // Desempilhar
    // -----------------------------
    public acao desempilhar() {
        if (isEmpty()) {
            return null;
        }
        acao removida = elementos[topo];
        topo--;
        return removida;
    }

    // -----------------------------
    // Observar o topo
    // -----------------------------
    public acao peek() {
        if (isEmpty()) {
            return null;
        }
        return elementos[topo];
    }

    // -----------------------------
    // Verificar se está vazia
    // -----------------------------
    public boolean isEmpty() {
        return topo == -1;
    }

    // -----------------------------
    // Quantidade de elementos
    // -----------------------------
    public int tamanho() {
        return topo + 1;
    }

    // -----------------------------
    // Imprimir ações (debug)
    // -----------------------------
    public void listar() {
        System.out.println("Pilha de ações:");
        for (int i = topo; i >= 0; i--) {
            System.out.println(" - " + elementos[i].getClass().getSimpleName());
        }
    }
}
