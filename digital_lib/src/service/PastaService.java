package service;

import database.PastaDAO;
import exceptions.PastaNaoEncontradaException;
import exceptions.ErroBancoException;
import model.Pasta;

import java.util.List;

public class PastaService {

    private PastaDAO pastaDAO;

    public PastaService() {
        this.pastaDAO = new PastaDAO();
    }

    // ---------------------------------------------------
    // CRIAR NOVA PASTA
    // ---------------------------------------------------
    public Pasta criarPasta(int idUsuario, String nomePasta) {

        try {
            Pasta pasta = new Pasta(nomePasta, idUsuario);
            pastaDAO.inserir(pasta);
            return pasta;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao criar pasta: " + nomePasta, e);
        }
    }

    // ---------------------------------------------------
    // BUSCAR PASTA POR ID
    // ---------------------------------------------------
    public Pasta buscarPorId(int idPasta) {

        try {
            Pasta pasta = pastaDAO.buscarPorId(idPasta);

            if (pasta == null) {
                throw new PastaNaoEncontradaException(idPasta);
            }

            return pasta;

        } catch (PastaNaoEncontradaException e) {
            throw e;

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao buscar pasta ID: " + idPasta, e);
        }
    }

    // ---------------------------------------------------
    // LISTAR TODAS AS PASTAS DE UM USUÁRIO
    // ---------------------------------------------------
    /**
     * @param idUsuario
     */
    public void listarPastasPorUsuario(int idUsuario) {

        try {
            final List<Pasta> pastas = pastaDAO.listarPorUsuario(idUsuario);


            if (pastas.isEmpty()) {
                System.out.println("Nenhuma pasta encontrada para esse usuário.");
                return;
            }

            System.out.println("=== PASTAS DO USUÁRIO " + idUsuario + " ===");
            for (Pasta p : pastas) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }

        } catch (Exception e) {
            throw new ErroBancoException("Erro ao listar pastas do usuário " + idUsuario, e);
        }
    }

    public void listarPastas(int idUsuario) {
        throw new UnsupportedOperationException("Unimplemented method 'listarPastas'");
    }
}
