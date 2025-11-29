package ui;

import java.util.Scanner;

import model.Arquivo;
import model.Pasta;
import service.UsuarioService;
import service.SolicitacaoService;
import service.BibliotecaService;
import exceptions.*;

public class MenuConsole {

    private Scanner scanner;
    private UsuarioService usuarioService;
    private BibliotecaService bibliotecaService;
    private SolicitacaoService solicitacaoService;

    private int usuarioLogadoId = -1;

    public MenuConsole(
            UsuarioService usuarioService,
            BibliotecaService bibliotecaService,
            SolicitacaoService solicitacaoService
    ) {
        this.scanner = new Scanner(System.in);
        this.usuarioService = usuarioService;
        this.bibliotecaService = bibliotecaService;
        this.solicitacaoService = solicitacaoService;
    }

    public void iniciar() {
        while (true) {
            if (usuarioLogadoId == -1) {
                menuLogin();
            } else {
                menuPrincipal();
            }
        }
    }

    // ----------------------------------------------
    // LOGIN
    // ----------------------------------------------
    private void menuLogin() {
        System.out.println("=========================");
        System.out.println("     LOGIN DO SISTEMA    ");
        System.out.println("=========================");
        System.out.print("Digite o ID do usuário: ");

        try {
            usuarioLogadoId = Integer.parseInt(scanner.nextLine());

            if (!usuarioService.existeUsuario(usuarioLogadoId)) {
                System.out.println("Usuário não encontrado!");
                usuarioLogadoId = -1;
            } else {
                System.out.println("Login realizado com sucesso!\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    // ----------------------------------------------
    // MENU PRINCIPAL
    // ----------------------------------------------
    private void menuPrincipal() {
        System.out.println("=========================");
        System.out.println("     MENU PRINCIPAL      ");
        System.out.println("=========================");
        System.out.println("1 - Criar Pasta");
        System.out.println("2 - Listar Pastas");
        System.out.println("3 - Adicionar Arquivo");
        System.out.println("4 - Remover Arquivo");
        System.out.println("5 - Buscar Arquivo");
        System.out.println("6 - Mover Arquivo entre pastas");
        System.out.println("7 - Listar arquivos da pasta");
        System.out.println("8 - Sair / Logout");
        System.out.print("Escolha: ");

        String opc = scanner.nextLine();

        switch (opc) {
            case "1": criarPasta(); break;
            case "2": listarPastas(); break;
            case "3": adicionarArquivo(); break;
            case "4": removerArquivo(); break;
            case "5": buscarArquivo(); break;
            case "6": moverArquivo(); break;
            case "7": listarArquivosPasta(); break;
            case "8": logout(); break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    // ---------------------------------------------------
    // OPÇÕES DO MENU
    // ---------------------------------------------------

    private void criarPasta() {
        System.out.print("Nome da nova pasta: ");
        String nome = scanner.nextLine();

        try {
            Pasta pasta = usuarioService.criarPasta(usuarioLogadoId, nome);
            System.out.println("Pasta criada com sucesso! ID = " + pasta.getId());
        } catch (Exception e) {
            System.out.println("Erro ao criar pasta: " + e.getMessage());
        }
    }

    private void listarPastas() {
        usuarioService.listarPastas(usuarioLogadoId);
    }

    private void adicionarArquivo() {
        System.out.print("ID da pasta: ");
        int idPasta = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        Arquivo arquivo = new Arquivo(nomeArquivo);

        try {
            Pasta pasta = usuarioService.getPasta(idPasta);
            bibliotecaService.adicionarArquivo(pasta, arquivo, usuarioLogadoId);
            System.out.println("Arquivo adicionado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerArquivo() {
        System.out.print("ID da pasta: ");
        int idPasta = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do arquivo a remover: ");
        String nomeArquivo = scanner.nextLine();

        try {
            Pasta pasta = usuarioService.getPasta(idPasta);

            bibliotecaService.removerArquivo(pasta, nomeArquivo, usuarioLogadoId);

            System.out.println("Arquivo removido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscarArquivo() {
        System.out.print("ID da pasta: ");
        int idPasta = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do arquivo para buscar: ");
        String nomeArquivo = scanner.nextLine();

        try {
            Pasta pasta = usuarioService.getPasta(idPasta);
            Arquivo arq = bibliotecaService.buscarArquivo(pasta, nomeArquivo, usuarioLogadoId);

            System.out.println("Arquivo encontrado: " + arq.getNome());

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void moverArquivo() {
        System.out.print("ID da pasta de origem: ");
        int idOrigem = Integer.parseInt(scanner.nextLine());
        System.out.print("ID da pasta de destino: ");
        int idDestino = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        try {
            Pasta origem = usuarioService.getPasta(idOrigem);
            Pasta destino = usuarioService.getPasta(idDestino);

            bibliotecaService.moverArquivo(origem, destino, nomeArquivo, usuarioLogadoId);

            System.out.println("Arquivo movido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarArquivosPasta() {
        System.out.print("ID da pasta: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            Pasta pasta = usuarioService.getPasta(id);
            bibliotecaService.listarArquivos(pasta, usuarioLogadoId);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void logout() {
        usuarioLogadoId = -1;
        System.out.println("Logout realizado!");
    }
}
