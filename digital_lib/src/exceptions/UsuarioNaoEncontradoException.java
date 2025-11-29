package exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(int idUsuario) {
        super("Usuário com ID " + idUsuario + " não foi encontrado.");
    }

    
}
