package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;

    // Cada usuário possui suas pastas
    private List<Pasta> pastas;

    // Construtor completo (usado ao carregar do banco)
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pastas = new ArrayList<>();
    }

    // Construtor simples (quando ainda não tem ID)
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pastas = new ArrayList<>();
    }

    public Usuario(String nome2) {
    }

    // Getters e Setters (Encapsulamento)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pasta> getPastas() {
        return pastas;
    }

    // Método de regra de negócio: adicionar pasta
    public void adicionarPasta(Pasta pasta) {
        pastas.add(pasta);
    }

    // Método de regra de negócio: remover pasta
    public boolean removerPasta(String nomePasta) {
        return pastas.removeIf(p -> p.getNome().equalsIgnoreCase(nomePasta));
    }

    @Override
    public String toString() {
        return "Usuario { " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                " }";
    }
}
