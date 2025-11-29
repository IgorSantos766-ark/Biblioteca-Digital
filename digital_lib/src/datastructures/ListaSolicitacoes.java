package datastructures;

import java.util.ArrayList;
import java.util.List;

public class ListaSolicitacoes {

    private List<Solicitacao> lista;

    public ListaSolicitacoes() {
        this.lista = new ArrayList<>();
    }

    public void adicionar(Solicitacao solicitacao) {
        lista.add(solicitacao);
    }

    public ListaSolicitacoes filtrarPorUsuario(int idUsuario) {

        ListaSolicitacoes filtrada = new ListaSolicitacoes();

        for (Solicitacao s : lista) {
            if (s.getIdUsuarioSolicitante() == idUsuario) {
                filtrada.adicionar(s);
            }
        }

        return filtrada;
    }

    public Solicitacao buscarPorId(int id) {

        for (Solicitacao s : lista) {
            if (s.getIdSolicitacao() == id) {
                return s;
            }
        }

        throw new RuntimeException("Solicitação id " + id + " não encontrada.");
    }

    public void removerPorId(int id) {

        lista.removeIf(s -> s.getIdSolicitacao() == id);
    }

    public List<Solicitacao> getLista() {
        return lista;
    }
}
