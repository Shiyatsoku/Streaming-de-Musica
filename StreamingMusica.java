import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        adicionarMusicasTeste();

        System.out.println("=== BEM-VINDO AO STREAMING ===");

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.println("1. Free");
        System.out.println("2. Premium");
        int tipo = lerOpcao();

        // aqui escolhe qual tipo de usuário vai ser criado
        if (tipo == 1) {
            usuario = new UsuarioFree(nome, email);
        } else {
            System.out.println("1. Mensal");
            System.out.println("2. Anual");
            System.out.println("3. Familiar");

            int plano = lerOpcao();

            // operador ternário pra escolher o plano
            String tipoPlano = (plano == 1) ? "Mensal" : (plano == 2) ? "Anual" : "Familiar";

            usuario = new UsuarioPremium(nome, email, tipoPlano);
        }

        int opcao;

        // loop principal do sistema (fica rodando até sair)
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao, nome, email);
        } while (opcao != 0);

        System.out.println("Até logo!");
    }

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Exibir estatísticas");

        // instanceof verifica o tipo do objeto (free ou premium)
        if (usuario instanceof UsuarioFree) {
            System.out.println("7. ==> FAZER UPGRADE PARA PREMIUM <==");
        } else {
            System.out.println("7. Baixar música");
            System.out.println("8. Ver músicas baixadas");
        }

        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1; // se digitar errado não quebra o programa
        }
    }

    public static void processarOpcao(int opcao, String nome, String email) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarMusica(); break;
            case 4: criarPlaylist(); break;
            case 5: gerenciarPlaylists(); break;
            case 6: estatisticas(); break;

            case 7:
                // aqui muda comportamento dependendo do tipo de usuário
                if (usuario instanceof UsuarioFree) {
                    System.out.println("Fazendo upgrade...");
                    usuario = new UsuarioPremium(nome, email, "Mensal");
                } else {
                    baixarMusica();
                }
                break;

            case 8:
                // casting: transformando Usuario em UsuarioPremium
                if (usuario instanceof UsuarioPremium) {
                    ((UsuarioPremium) usuario).listarBaixadas();
                } else {
                    System.out.println("Opção inválida!");
                }
                break;

            case 0: System.out.println("Saindo..."); break;
            default: System.out.println("Opção inválida!");
        }
    }

    public static void cadastrarMusica() {

        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Artista: ");
            String artista = scanner.nextLine();

            System.out.print("Duração (segundos): ");
            int duracao = Integer.parseInt(scanner.nextLine());

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            // cria objeto musica e adiciona na lista
            musicas.add(new Musica(titulo, artista, duracao, genero));

            System.out.println("Música cadastrada!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar música!");
        }
    }

    public static void listarMusicas() {
        for (int i = 0; i < musicas.size(); i++) {
            Musica m = musicas.get(i); // pega música da lista
            System.out.println(i + " - " + m.getTitulo() + " | " + m.getArtista());
        }
    }

    public static void buscarMusica() {
        System.out.print("Buscar: ");
        String busca = scanner.nextLine().toLowerCase();

        // percorre todas as músicas
        for (Musica m : musicas) {
            // contains permite buscar parte do nome
            if (m.getTitulo().toLowerCase().contains(busca)) {
                System.out.println(m.getTitulo());
            }
        }
    }

    public static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();

        // chama método do usuário (pode ser diferente em free/premium)
        usuario.criarPlaylist(nome);
    }

    public static void gerenciarPlaylists() {
        int op;

        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("0. Voltar");

            op = lerOpcao();

            switch (op) {
                case 1:
                    // percorre playlists do usuário
                    for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                        System.out.println(i + " - " + usuario.getPlaylists().get(i).getNome());
                    }
                    break;
            }

        } while (op != 0);
    }

    public static void estatisticas() {
        System.out.println("Total músicas: " + musicas.size());
    }

    public static void baixarMusica() {
        for (int i = 0; i < musicas.size(); i++) {
            System.out.println(i + " - " + musicas.get(i).getTitulo());
        }

        int idx = lerOpcao();

        // valida índice pra não dar erro
        if (idx >= 0 && idx < musicas.size()) {
            ((UsuarioPremium) usuario).baixarMusica(musicas.get(idx));
        }
    }

    // musicas de teste pra não precisar cadastrar toda hora
    public static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        musicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }
}