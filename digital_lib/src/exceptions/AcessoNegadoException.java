package exceptions;

public class AcessoNegadoException extends RuntimeException {

    public AcessoNegadoException(String mensagem) {
        super(mensagem);
    }

    // Construtor padrão
    public AcessoNegadoException() {
        super("Acesso negado! Você não possui permissão para realizar esta ação.");
    }
}
