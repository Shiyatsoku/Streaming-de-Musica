package br.com.streaming.principal;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.streaming.modelo.*;
import br.com.streaming.util.Cores;

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
// menu inicial do sistema (criar usuário, login, listar usuários)
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
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Cadastro automático (não cadastra informações nas estatisticas, apenas para facilitar testes)");
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
                
                case 3:
                    listarUsuarios();
                    break;

                case 4:
                    usuarioLogado = new UsuarioFree(
                            "Teste",
                            "teste@gmail.com"
                    );

                    System.out.println(Cores.VERDE + "\nEntrando automaticamente..." + Cores.RESET);

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }

                    menuSistema();
                    break;

                case 0:
                    System.out.println(Cores.VERDE + "Até breve!" + Cores.RESET);
                    break;

                default:
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }

        } while (op != 0);
    }


    public static void menuSistema() {

        int opcao;

        do {

            exibirMenu();

            opcao = lerOpcao();

            processarOpcao(opcao);

        } while (opcao != 0);

        usuarioLogado = null;
    }

// menu de criação de usuário (free ou premium)
    public static void criarUsuario() {

    try {

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
            System.out.println("1 - Mensal (R$ 19,90)");
            System.out.println("2 - Anual (R$ 199,00)");
            System.out.println("3 - Familiar (R$ 29,90)");
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

    } catch (IllegalArgumentException e) {

        System.out.println(Cores.VERMELHO + "\nErro: " + e.getMessage() + Cores.RESET);
    }

    System.out.println("\nPressione Enter para continuar...");
    scanner.nextLine();
}

// menu de login incial do sistema
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

        if (u.getEmail().equalsIgnoreCase(email)) {

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


// menu inicial do sistema após login (opções específicas para usuário free ou premium)
public static void exibirMenu() {

    System.out.println(Cores.VERDE + """
    ┌────────────────────────────────────┐
    │         Sistema de Streaming       │
    └────────────────────────────────────┘
    """ + Cores.RESET);

    System.out.println("1. Cadastrar música");
    System.out.println("2. Listar músicas");
    System.out.println("3. Buscar música");
    System.out.println("4. Reproduzir música");
    System.out.println("5. Criar playlist");
    System.out.println("6. Gerenciar playlists");
    System.out.println("7. Estatísticas");

    if (usuarioLogado instanceof UsuarioFree) {

        System.out.println(Cores.AMARELO + "\n8. ==> FAZER UPGRADE PARA PREMIUM <==\n" + Cores.RESET);

    } else {

        System.out.println("8. Baixar música");
        System.out.println("9. Ver músicas baixadas");
    }

    System.out.println("0. Logout\n");

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
                    reproduzirMusica();
                    break;
                    
            case 5:
                criarPlaylist();
                break;

            case 6:
                gerenciarPlaylists();
                break;

            case 7:
                estatisticas();
                break;

            case 8:

                if (usuarioLogado instanceof UsuarioFree) {

                    System.out.println("Fazendo upgrade...");

                    usuarioLogado =
                            new UsuarioPremium(
                                    usuarioLogado.getNome(),
                                    usuarioLogado.getEmail(),
                                    "Mensal"
                            );

                } else {

                    baixarMusica();
                }

                break;

            case 9: 

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

// opção 1 cadastrar música
   public static void cadastrarMusica() {

    try {

        System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│         Cadastrar Música           │
└────────────────────────────────────┘
""" + Cores.RESET);

        System.out.print("\nTítulo: ");
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

        System.out.println(Cores.VERDE + "\nMúsica cadastrada com sucesso!" + Cores.RESET);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        limparTela();

    } catch (Exception e) {

        System.out.println(Cores.VERMELHO + "\nErro ao cadastrar música!" + Cores.RESET);

        try {
            Thread.sleep(2000);
        } catch (Exception ex) {

        }

        limparTela();
    }
}

// opção 2 listar músicas
public static void listarMusicas() {

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│          LISTA DE MÚSICAS          │
└────────────────────────────────────┘
""" + Cores.RESET);

    if (musicas.isEmpty()) {

        System.out.println(Cores.VERMELHO + "\nNenhuma música cadastrada." + Cores.RESET);

    } else {

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

    System.out.println("\nPressione Enter para voltar ao menu...");
    scanner.nextLine();

    limparTela();
}

// opção 3 buscar música por título
public static void buscarMusica() {

    limparTela();

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│           BUSCAR MÚSICA            │
└────────────────────────────────────┘
""" + Cores.RESET);

    System.out.print("\nDigite o nome da música: ");

    String busca = scanner.nextLine().toLowerCase();

    boolean encontrada = false;

    System.out.println("\n" + Cores.CIANO + "Resultados encontrados:\n" + Cores.RESET);

    for (Musica m : musicas) {

        if (m.getTitulo().toLowerCase().contains(busca)) {

            System.out.println(m.getTitulo() + " | " + m.getArtista() + " | " + m.formatarDuracao());

            encontrada = true;
        }
    }

    if (!encontrada) System.out.println(Cores.VERMELHO + "Nenhuma música encontrada." + Cores.RESET);

    System.out.println("\nPressione Enter para voltar...");

    scanner.nextLine();

    limparTela();
}

// opção 4 criar playlist
public static void criarPlaylist() {

    limparTela();

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│          CRIAR PLAYLIST            │
└────────────────────────────────────┘
""" + Cores.RESET);

    System.out.print("\nNome da playlist: ");

    String nome = scanner.nextLine();

    int antes = usuarioLogado.getPlaylists().size();

    usuarioLogado.criarPlaylist(nome);

    int depois = usuarioLogado.getPlaylists().size();

    if (depois > antes) {

        System.out.println(Cores.VERDE + "\nPlaylist criada com sucesso!" + Cores.RESET);

    } else {

        System.out.println(Cores.VERMELHO + "\nNão foi possível criar a playlist." + Cores.RESET);
    }

    try {
        Thread.sleep(2000);
    } catch (Exception e) {

    }

    limparTela();
}

// opção 5 gerenciar playlists (listar playlists do usuário e playlists automáticas, reproduzir playlist automática)
  public static void gerenciarPlaylists() {

    int op;

    do {

        limparTela();

        System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│        GERENCIAR PLAYLISTS         │
└────────────────────────────────────┘
""" + Cores.RESET);

        System.out.println("1 - Ver todas playlists");
        System.out.println("2 - Reproduzir playlist");
        System.out.println("0 - Voltar");

        System.out.print("\nEscolha: ");

        op = lerOpcao();

        switch (op) {

            case 1:

                limparTela();

                System.out.println(Cores.CIANO + """
=== SUAS PLAYLISTS ===
""" + Cores.RESET);

                if (usuarioLogado.getPlaylists().isEmpty()) {

                    System.out.println("Nenhuma playlist criada.");

                } else {

                    for (int i = 0; i < usuarioLogado.getPlaylists().size(); i++) {

                        System.out.println(
                                (i + 1) + ". " +
                                usuarioLogado.getPlaylists().get(i).getNome()
                        );
                    }
                }

                System.out.println(Cores.CIANO + """
                
=== PLAYLISTS AUTOMÁTICAS ===
""" + Cores.RESET);

                System.out.println("1. Top 10 Mais Tocadas");
                System.out.println("2. Recomendadas para Você");
                System.out.println("3. Adicionadas Recentemente");

                System.out.println("\nPressione Enter para voltar...");
                scanner.nextLine();

                break;

            case 2:

                limparTela();

                System.out.println(Cores.CIANO + """
=== ESCOLHA UMA PLAYLIST ===
""" + Cores.RESET);

                int contador = 1;

                for (int i = 0; i < usuarioLogado.getPlaylists().size(); i++) {

                    System.out.println(
                            contador + ". " +
                            usuarioLogado.getPlaylists().get(i).getNome()
                    );

                    contador++;
                }

                System.out.println(contador + ". Top 10 Mais Tocadas");
                contador++;

                System.out.println(contador + ". Recomendadas para Você");
                contador++;

                System.out.println(contador + ". Adicionadas Recentemente");

                System.out.print("\nEscolha: ");

                int escolha = lerOpcao();

                limparTela();

                System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│         REPRODUZINDO               │
└────────────────────────────────────┘
""" + Cores.RESET);

                int qtdUsuario = usuarioLogado.getPlaylists().size();

                if (escolha >= 1 && escolha <= qtdUsuario) {

                    usuarioLogado
                            .getPlaylists()
                            .get(escolha - 1)
                            .reproduzir();

                } else if (escolha == qtdUsuario + 1) {

                    System.out.println("Gerando playlist \"Top 10 Mais Tocadas\"...\n");

                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {

                    }

                    playlistsAutomaticas.get(0).reproduzir() ;

                } else if (escolha == qtdUsuario + 2) {

                    System.out.println("Gerando playlist \"Recomendadas para Você\"...\n");

                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {

                    }

                    playlistsAutomaticas.get(1).reproduzir();

                } else if (escolha == qtdUsuario + 3) {

                    System.out.println("Gerando playlist \"Adicionadas Recentemente\"...\n");

                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {

                    }

                    playlistsAutomaticas.get(2).reproduzir();

                } else {

                    System.out.println(Cores.VERMELHO + "Playlist inválida." + Cores.RESET);
                }

                System.out.println("\nPressione Enter para voltar...");
                scanner.nextLine();

                break;

            case 0:

                limparTela();

                break;

            default:

                System.out.println(Cores.VERMELHO + "Opção inválida." + Cores.RESET);

                try {
                    Thread.sleep(1500);
                } catch (Exception e) {

                }
        }

    } while (op != 0);
}

public static void estatisticas() {

    limparTela();

    int usuariosFree = 0;
    int usuariosPremium = 0;

    int reproducoesFree = 0;
    int reproducoesPremium = 0;

    for (Usuario u : usuarios) {

        if (u instanceof UsuarioPremium) {

            usuariosPremium++;
            reproducoesPremium += u.getReproducoes();

        } else {

            usuariosFree++;
            reproducoesFree += u.getReproducoes();
        }
    }

    int totalUsuarios = usuarios.size();

    int reproducoesTotais =
            reproducoesFree + reproducoesPremium;

    int porcentagemFree = 0;
    int porcentagemPremium = 0;

    if (reproducoesTotais > 0) {

        porcentagemFree =
                (reproducoesFree * 100) / reproducoesTotais;

        porcentagemPremium =
                (reproducoesPremium * 100) / reproducoesTotais;
    }

    int anuncios = reproducoesFree / 3;

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│      ESTATÍSTICAS DO SISTEMA       │
└────────────────────────────────────┘
""" + Cores.RESET);

    System.out.println("Total de usuários: " + totalUsuarios);

    System.out.println("- Free: " + usuariosFree + " usuários");

    System.out.println("- Premium: " + usuariosPremium + " usuários");

    System.out.println();

    System.out.println("Reproduções totais: " + reproducoesTotais);

    System.out.println("- Free: " + reproducoesFree + " reproduções (" + porcentagemFree + "%)");

    System.out.println("- Premium: " + reproducoesPremium + " reproduções (" + porcentagemPremium + "%)");

    System.out.println();

    System.out.println("Anúncios exibidos: " + anuncios);

    System.out.println("\nPressione Enter para voltar...");

    scanner.nextLine();

    limparTela();
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


public static void listarUsuarios() {

    limparTela();

    System.out.println("\n" + Cores.AZUL + "=== USUÁRIOS CADASTRADOS ===\n" + Cores.RESET);

    if (usuarios.isEmpty()) {

    System.out.println(Cores.VERMELHO + "Nenhum usuário cadastrado." + Cores.RESET);
    System.out.println("\nVoltando ao menu...");

    try {
        Thread.sleep(3000);
    } catch (Exception e) {

    }

    limparTela();
    return;

    } else {

        for (int i = 0; i < usuarios.size(); i++) {

            Usuario u = usuarios.get(i);

            String tipoConta;

            if (u instanceof UsuarioPremium) {
                tipoConta = "Premium";
            } else {
                tipoConta = "Free";
            }

            System.out.println(
                    i + " - " +
                    u.getNome() +
                    " | " +
                    u.getEmail() +
                    " | Conta: " +
                    tipoConta
            );
        }
    }

    System.out.println("\nPressione Enter para continuar...");
    scanner.nextLine();
    }

public static void reproduzirMusica() {

    limparTela();

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│         REPRODUZIR MÚSICA          │
└────────────────────────────────────┘
""" + Cores.RESET);

    if (musicas.isEmpty()) {

        System.out.println(Cores.VERMELHO + "Nenhuma música cadastrada." + Cores.RESET);

    } else {

        for (int i = 0; i < musicas.size(); i++) {

            Musica m = musicas.get(i);

            System.out.println(i + " - " + m.getTitulo() + " | " + m.getArtista());
        }

        System.out.print("\nEscolha: ");

        int idx = lerOpcao();

        if (idx >= 0 && idx < musicas.size()) {

            limparTela();

            System.out.println(Cores.CIANO + """
┌────────────────────────────────────┐
│            REPRODUZINDO            │
└────────────────────────────────────┘
""" + Cores.RESET);

            Musica musicaSelecionada = musicas.get(idx);

            usuarioLogado.reproduzirMusica(musicaSelecionada);

            musicaSelecionada.reproduzir();

        } else {

            System.out.println(Cores.VERMELHO + "Música inválida." + Cores.RESET);
        }
    }

    System.out.println("\nPressione Enter para voltar...");

    scanner.nextLine();

    limparTela();
    }

    public static void baixarMusica() {

    limparTela();

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│           BAIXAR MÚSICA            │
└────────────────────────────────────┘
""" + Cores.RESET);

    for (int i = 0; i < musicas.size(); i++) {

        Musica m = musicas.get(i);

        System.out.println(i + " - " + m.getTitulo() + " | " + m.getArtista());
    }

    System.out.print("\nEscolha: ");

    int idx = lerOpcao();

    if (idx >= 0 && idx < musicas.size()) {

        ((UsuarioPremium) usuarioLogado).baixarMusica(musicas.get(idx));

    } else {

        System.out.println(Cores.VERMELHO + "Música inválida." + Cores.RESET);
    }

    System.out.println("\nPressione Enter para voltar...");

    scanner.nextLine();

    limparTela();
    }
}