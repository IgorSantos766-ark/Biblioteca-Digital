package model;

public class Arquivo {

    private int id;                 // ID no MySQL
    private String nome;            // Nome do arquivo
    private String tipo;            // Ex: "pdf", "txt", "jpg"
    private double Tamanho;       // Tamanho em KB
    private int idPasta;            // FK para Pasta

    // Construtor completo (carregar do banco)
    public Arquivo(int id, String nome, String tipo, double tamanho, int idPasta, double Tamanho) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.Tamanho = Tamanho;
        this.idPasta = idPasta;
    }

    // Construtor sem ID (antes de salvar no MySQL)
    public Arquivo(String nome, String tipo, double tamanhoKB, int idPasta, double Tamanho) {
        this.nome = nome;
        this.tipo = tipo;
        this.Tamanho = Tamanho;
        this.idPasta = idPasta;
    }

    // ------------------------------
    // GETTERS & SETTERS
    // ------------------------------

    public Arquivo(String nomeArquivo) {
        //TODO Auto-generated constructor stub
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTamanho() {
        return Tamanho;
    }

    public void setTamanhoKB(double Tamanho) {
        this.Tamanho = Tamanho;
    }

    public int getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(int idPasta) {
        this.idPasta = idPasta;
    }

    @Override
    public String toString() {
        return "Arquivo { " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tamanho=" + Tamanho + "KB" +
                " }";
    }

    public class arquivo {

    private String nome;

    public arquivo(String nome) {
        this.nome = nome;
    }

    // getter e setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

}
