package exceptions;

public class ErroBancoException extends RuntimeException {

    public ErroBancoException(String mensagem) {
        super(mensagem);
    }

    public ErroBancoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
