import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();

    // LISTA DE USUÁRIOS
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    static ArrayList<PlaylistAutomatica> playlistsAutomaticas =
            new ArrayList<>();

    // USUÁRIO LOGADO
    static Usuario usuarioLogado = null;

    static Scanner scanner = new Scanner(System.in);

    // limpar tela (só vai pra baixo no terminal)
    public static void limparTela() {

    System.out.print("\033[H\033[2J");
    System.out.flush();
    }

    public static void main(String[] args) {

        adicionarMusicasTeste();

        criarPlaylistsAutomaticas();

        int op;

        do {

            limparTela();

            System.out.println("\n\n" + Cores.VERMELHO + """
            ╔════════════════════════════╗
            ║    STREAMING DE MUSICA     ║
            ╚════════════════════════════╝
            """ + Cores.RESET);

            System.out.println( Cores.VERDE + "══════════════════════════════════════════════" + Cores.RESET);
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Fazer login");
            System.out.println("0 - Sair");
            System.out.println( Cores.VERDE + "══════════════════════════════════════════════" + Cores.RESET);

            System.out.print("\nEscolha: ");
            op = lerOpcao();

            switch (op) {

                case 1:
                    limparTela();
                    criarUsuario();
                    break;

                case 2:
                    login();

                    if (usuarioLogado != null) {
                        menuSistema();
                    }
                    break;

                case 0:
                    System.out.println(Cores.VERDE + "Até breve!" + Cores.RESET);
                    break;

                default:
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }

        } while (op != 0);
    }

    // MENU PRINCIPAL DO SISTEMA
    public static void menuSistema() {

        int opcao;

        do {

            exibirMenu();

            opcao = lerOpcao();

            processarOpcao(opcao);

        } while (opcao != 0);

        usuarioLogado = null;
    }

    // MENU CRIAR USUÁRIO
    public static void criarUsuario() {


        System.out.println(Cores.VERDE + """
    ┌────────────────────────────────────┐
    │        CRIAÇÃO DE USUÁRIO          │
    └────────────────────────────────────┘
    """ + Cores.RESET);

        System.out.print("\nNome: ");
        String nome = scanner.nextLine();

        System.out.print("\nEmail: ");
        String email = scanner.nextLine();

        System.out.println("\n" + Cores.VERDE + "────────────────────────────────────" + Cores.RESET);
        System.out.println("Escolha o tipo de conta:\n");
        System.out.println("1 - Conta Free");
        System.out.println(Cores.AMARELO + "2 - * Premium *" + Cores.RESET);
        System.out.println("\n" + Cores.VERDE + "────────────────────────────────────" + Cores.RESET);

        System.out.print("\nEscolha: ");

        int tipo = lerOpcao();

        if (tipo == 1) {

            usuarios.add(new UsuarioFree(nome, email));

        } else {

            System.out.println("\n" + Cores.VERDE + "────────────────────────────────────" + Cores.RESET);
            System.out.println("\nQual plano premium deseja?\n");
            System.out.println("1 - Mensal");
            System.out.println("2 - Anual");
            System.out.println("3 - Familiar");
            System.out.println("\n" + Cores.VERDE + "────────────────────────────────────" + Cores.RESET);

            System.out.print("\nEscolha: ");

            int plano = lerOpcao();

            String tipoPlano =
                    (plano == 1) ? "Mensal" :
                    (plano == 2) ? "Anual" :
                    "Familiar";

            usuarios.add(new UsuarioPremium(nome, email, tipoPlano));
        }

        System.out.println(Cores.VERDE + "\nUsuário criado com sucesso!" + Cores.RESET);
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    // MENU LOGIN
    public static void login() {

    limparTela();

    System.out.println(Cores.VERDE + """
    ┌────────────────────────────────────┐
    │         LOGIN DE USUÁRIO           │
    └────────────────────────────────────┘
    """ + Cores.RESET);

    System.out.print("Digite seu email: ");
    String email = scanner.nextLine();

    for (Usuario u : usuarios) {

        if (u.email.equalsIgnoreCase(email)) {

            usuarioLogado = u;

            System.out.println(Cores.VERDE + "\nLogin realizado com sucesso!" + Cores.RESET);
            System.out.println("entrando no sistema...");

            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }

            limparTela();

            return;
        }
    }

    System.out.println( Cores.VERMELHO + "\nLogin incorreto. Usuário não encontrado." + Cores.RESET );
    System.out.println("\nVoltando ao menu...");

    try {
        Thread.sleep(3000);
    } catch (Exception e) {

    }

    limparTela();
}


    //MENU PRINCIAL DO SISTEMA
    public static void exibirMenu() {

        System.out.println("\n=== SISTEMA DE STREAMING ===");

        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Estatísticas");

        if (usuarioLogado instanceof UsuarioFree) {

            System.out.println("7. ==> FAZER UPGRADE PARA PREMIUM <==");

        } else {

            System.out.println("7. Baixar música");
            System.out.println("8. Ver músicas baixadas");
        }

        System.out.println("0. Logout");
        System.out.print("Escolha: ");
    }

    public static int lerOpcao() {

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void processarOpcao(int opcao) {

        switch (opcao) {

            case 1:
                cadastrarMusica();
                break;

            case 2:
                listarMusicas();
                break;

            case 3:
                buscarMusica();
                break;

            case 4:
                criarPlaylist();
                break;

            case 5:
                gerenciarPlaylists();
                break;

            case 6:
                estatisticas();
                break;

            case 7:

                if (usuarioLogado instanceof UsuarioFree) {

                    System.out.println("Fazendo upgrade...");

                    usuarioLogado =
                            new UsuarioPremium(
                                    usuarioLogado.nome,
                                    usuarioLogado.email,
                                    "Mensal"
                            );

                } else {

                    baixarMusica();
                }

                break;

            case 8:

                if (usuarioLogado instanceof UsuarioPremium) {

                    ((UsuarioPremium) usuarioLogado).listarBaixadas();

                } else {

                    System.out.println("Opção inválida!");
                }

                break;

            case 0:
                System.out.println("Logout realizado!");
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public static void cadastrarMusica() {

        try {

            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Artista: ");
            String artista = scanner.nextLine();

            System.out.print("Duração: ");
            int duracao = Integer.parseInt(scanner.nextLine());

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            musicas.add(
                    new Musica(
                            titulo,
                            artista,
                            duracao,
                            genero
                    )
            );

            System.out.println("✅ Música cadastrada!");

        } catch (Exception e) {

            System.out.println("Erro ao cadastrar música!");
        }
    }

    public static void listarMusicas() {

        for (int i = 0; i < musicas.size(); i++) {

            Musica m = musicas.get(i);

            System.out.println(
                    i + " - " +
                    m.getTitulo() +
                    " | " +
                    m.getArtista()
            );
        }
    }

    public static void buscarMusica() {

        System.out.print("Buscar: ");

        String busca =
                scanner.nextLine().toLowerCase();

        for (Musica m : musicas) {

            if (
                m.getTitulo()
                .toLowerCase()
                .contains(busca)
            ) {

                System.out.println(m.getTitulo());
            }
        }
    }

    public static void criarPlaylist() {

        System.out.print("Nome da playlist: ");

        String nome = scanner.nextLine();

        usuarioLogado.criarPlaylist(nome);

        System.out.println("✅ Playlist criada!");
    }

    public static void gerenciarPlaylists() {

        int op;

        do {

            System.out.println("\n=== PLAYLISTS ===");
            System.out.println("1. Listar playlists");
            System.out.println("2. Reproduzir playlist automática");
            System.out.println("0. Voltar");

            op = lerOpcao();

            switch (op) {

                case 1:

                    System.out.println("\n=== SUAS PLAYLISTS ===");

                    for (
                        int i = 0;
                        i < usuarioLogado.getPlaylists().size();
                        i++
                    ) {

                        System.out.println(
                                i + " - " +
                                usuarioLogado
                                .getPlaylists()
                                .get(i)
                                .getNome()
                        );
                    }

                    System.out.println("\n=== PLAYLISTS AUTOMÁTICAS ===");

                    for (
                        int i = 0;
                        i < playlistsAutomaticas.size();
                        i++
                    ) {

                        System.out.println(
                                i + " - " +
                                playlistsAutomaticas
                                .get(i)
                                .getNome()
                        );
                    }

                    break;

                case 2:

                    System.out.println("\n=== PLAYLISTS AUTOMÁTICAS ===");

                    for (
                        int i = 0;
                        i < playlistsAutomaticas.size();
                        i++
                    ) {

                        System.out.println(
                                i + " - " +
                                playlistsAutomaticas
                                .get(i)
                                .getNome()
                        );
                    }

                    System.out.print("Escolha: ");

                    int idx = lerOpcao();

                    if (
                        idx >= 0 &&
                        idx < playlistsAutomaticas.size()
                    ) {

                        playlistsAutomaticas
                                .get(idx)
                                .reproduzir();
                    }

                    break;
            }

        } while (op != 0);
    }

    public static void estatisticas() {

        System.out.println(
                "Total músicas: " +
                musicas.size()
        );

        System.out.println(
                "Total usuários: " +
                usuarios.size()
        );
    }

    public static void baixarMusica() {

        listarMusicas();

        int idx = lerOpcao();

        if (
            idx >= 0 &&
            idx < musicas.size()
        ) {

            ((UsuarioPremium) usuarioLogado)
                    .baixarMusica(musicas.get(idx));
        }
    }

    public static void criarPlaylistsAutomaticas() {

        PlaylistAutomatica top =
                new PlaylistAutomatica(
                        "Top Hits",
                        "top"
                );

        top.atualizar(musicas);

        playlistsAutomaticas.add(top);

        PlaylistAutomatica recentes =
                new PlaylistAutomatica(
                        "Mais Recentes",
                        "recentes"
                );

        recentes.atualizar(musicas);

        playlistsAutomaticas.add(recentes);
    }

    // MÚSICAS DE TESTE
    public static void adicionarMusicasTeste() {

        musicas.add(
                new Musica(
                        "Bohemian Rhapsody",
                        "Queen",
                        354,
                        "Rock"
                )
        );

        musicas.add(
                new Musica(
                        "Billie Jean",
                        "Michael Jackson",
                        293,
                        "Pop"
                )
        );
    }
}