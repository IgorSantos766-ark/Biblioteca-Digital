import java.util.Scanner;

import model.Usuario;
import service.UsuarioService;
import service.PastaService;   // se você tiver
import model.Pasta;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UsuarioService usuarioService = new UsuarioService();
        PastaService pastaService = new PastaService(); // opcional — apague se não tiver

        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Buscar usuário por ID");
            System.out.println("3 - Listar usuários");
            System.out.println("4 - Remover usuário");
            System.out.println("5 - Criar pasta");
            System.out.println("6 - Listar pastas do usuário");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {

                    case 1 -> {
                        System.out.print("Nome do usuário: ");
                        String nome = sc.nextLine();

                        Usuario u = new Usuario(nome);
                        usuarioService.criarUsuario(u);
                        System.out.println("Usuário criado com sucesso! ID = " + u.getId());
                    }

                    case 2 -> {
                        System.out.print("ID do usuário: ");
                        int id = sc.nextInt();
                        Usuario u = usuarioService.buscarPorId(id);
                        System.out.println("Encontrado: " + u);
                    }

                    case 3 -> {
                        usuarioService.listarUsuarios();
                    }

                    case 4 -> {
                        System.out.print("ID do usuário a remover: ");
                        int id = sc.nextInt();
                        usuarioService.removerUsuario(id);
                        System.out.println("Usuário removido!");
                    }

                    case 5 -> {
                        System.out.print("ID do usuário dono da pasta: ");
                        int idUsuario = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nome da pasta: ");
                        String nomePasta = sc.nextLine();

                        Pasta pasta = pastaService.criarPasta(idUsuario, nomePasta);

                        System.out.println("Pasta criada! ID = " + pasta.getId());
                    }

                    case 6 -> {
                        System.out.print("ID do usuário: ");
                        int idUsuario = sc.nextInt();

                        pastaService.listarPastas(idUsuario);
                    }

                    case 0 -> {
                        System.out.println("Encerrando...");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("Opção inválida!");
                }

            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }
}
