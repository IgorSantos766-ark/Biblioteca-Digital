package exceptions;

public class PastaNaoEncontradaException extends RuntimeException {

    public PastaNaoEncontradaException(int idPasta) {
        super("Pasta com ID " + idPasta + " não foi encontrada.");
    }

    public PastaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
