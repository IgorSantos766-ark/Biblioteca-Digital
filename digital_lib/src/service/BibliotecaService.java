package service;

import model.*;
import exceptions.*;

public class BibliotecaService {

    // --------------------------------------------------
    // CRIAR NOVA PASTA
    // --------------------------------------------------
    public Pasta criarPasta(String nomePasta, int idUsuarioDono) {
        try {
            return new Pasta(nomePasta, idUsuarioDono);
        } catch (Exception e) {
            throw new ErroBancoException("Erro ao criar pasta no sistema.", e);
        }
    }

    // --------------------------------------------------
    // ADICIONAR ARQUIVO
    // --------------------------------------------------
    public void adicionarArquivo(Pasta pasta, Arquivo arquivo, int idUsuarioAcionando) {
        pasta.verificarPermissao(idUsuarioAcionando);

        try {
            pasta.adicionarArquivo(arquivo);
        } catch (Exception e) {
            throw new ErroBancoException("Erro ao adicionar arquivo '" + arquivo.getNome() + "'.", e);
        }
    }

    // --------------------------------------------------
    // REMOVER ARQUIVO
    // --------------------------------------------------
    public void removerArquivo(Pasta pasta, String nomeArquivo, int idUsuarioAcionando) {
        pasta.verificarPermissao(idUsuarioAcionando);

        boolean removido = pasta.removerArquivo(nomeArquivo);

        if (!removido) {
            throw new ArquivoNaoEncontradoException("Arquivo '" + nomeArquivo + "' não encontrado.");
        }
    }

    // --------------------------------------------------
    // BUSCAR ARQUIVO
    // --------------------------------------------------
    public Arquivo buscarArquivo(Pasta pasta, String nomeArquivo, int idUsuarioAcionando) {
        pasta.verificarPermissao(idUsuarioAcionando);

        Arquivo arquivo = pasta.buscarArquivo(nomeArquivo);

        if (arquivo == null) {
            throw new ArquivoNaoEncontradoException("Arquivo '" + nomeArquivo + "' não existe nesta pasta.");
        }

        return arquivo;
    }

    // --------------------------------------------------
    // MOVER ARQUIVO ENTRE PASTAS
    // --------------------------------------------------
    public void moverArquivo(Pasta origem, Pasta destino, String nomeArquivo, int idUsuarioAcionando) {

        origem.verificarPermissao(idUsuarioAcionando);
        destino.verificarPermissao(idUsuarioAcionando);

        Arquivo arquivo = origem.buscarArquivo(nomeArquivo);

        if (arquivo == null) {
            throw new ArquivoNaoEncontradoException(
                "Arquivo '" + nomeArquivo + "' não encontrado na pasta de origem."
            );
        }

        try {
            origem.removerArquivo(nomeArquivo);
            destino.adicionarArquivo(arquivo);
        } catch (Exception e) {
            throw new ErroBancoException("Erro ao mover arquivo '" + nomeArquivo + "'.", e);
        }
    }

    // --------------------------------------------------
    // LISTAR ARQUIVOS
    // --------------------------------------------------
    public void listarArquivos(Pasta pasta, int idUsuarioAcionando) {
        pasta.verificarPermissao(idUsuarioAcionando);

        try {
            pasta.getArquivos().listar();
        } catch (Exception e) {
            throw new ErroBancoException("Erro ao listar arquivos da pasta '" + pasta.getNome() + "'.", e);
        }
    }
}
