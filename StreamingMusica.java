import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();

    // LISTA DE USUÁRIOS
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    // USUÁRIO LOGADO
    static Usuario usuarioLogado = null;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        adicionarMusicasTeste();

        int op;

        do {

            System.out.println("\n=== STREAMING DE MÚSICA ===");
            System.out.println("1. Criar usuário");
            System.out.println("2. Fazer login");
            System.out.println("0. Sair");

            op = lerOpcao();

            switch (op) {

                case 1:
                    criarUsuario();
                    break;

                case 2:
                    login();

                    if (usuarioLogado != null) {
                        menuSistema();
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
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

    // CRIAR USUÁRIO
    public static void criarUsuario() {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.println("1. Free");
        System.out.println("2. Premium");

        int tipo = lerOpcao();

        if (tipo == 1) {

            usuarios.add(new UsuarioFree(nome, email));

        } else {

            System.out.println("1. Mensal");
            System.out.println("2. Anual");
            System.out.println("3. Familiar");

            int plano = lerOpcao();

            String tipoPlano =
                    (plano == 1) ? "Mensal" :
                    (plano == 2) ? "Anual" :
                    "Familiar";

            usuarios.add(new UsuarioPremium(nome, email, tipoPlano));
        }

        System.out.println("✅ Usuário criado!");
    }

    // LOGIN
    public static void login() {

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        for (Usuario u : usuarios) {

            if (u.email.equalsIgnoreCase(email)) {

                usuarioLogado = u;

                System.out.println("✅ Login realizado!");
                return;
            }
        }

        System.out.println("Usuário não encontrado!");
    }

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
            System.out.println("0. Voltar");

            op = lerOpcao();

            switch (op) {

                case 1:

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