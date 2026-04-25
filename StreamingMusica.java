import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

     // lista principal que guarda todas as músicas do sistema
    static ArrayList<Musica> musicas = new ArrayList<>();

    // objeto usuário que tem as playlists
    static Usuario usuario = new Usuario();


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        adicionarMusicasTeste();

        int opcao;
        
        // loop principal do sistema, fica rodando até escolher 0
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
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
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    // try e o catch vai evitar o programa parar se o usuário digitar alguma letra
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    // decisão do para onde o programa deve ir de acordo com a opção escolhida
    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1: cadastrarMusica(); break;
            case 2: listarMusicas(); break;
            case 3: buscarMusica(); break;
            case 4: criarPlaylist(); break;
            case 5: gerenciarPlaylists(); break;
            case 6: estatisticas(); break;
            case 0: System.out.println("Saindo..."); break;
            default: System.out.println("Opção inválida!");
        }
    }

    // cadastrar musica: pede os dados, cria objeto e adiciona na lista
    public static void cadastrarMusica() {

        // do while para que o programa continue pedindo o título até que o usuário digitar algo (não pode ser vazio)
        String titulo;
        do {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
        } while (titulo.isEmpty());

        String artista;
        do {
            System.out.print("Artista: ");
            artista = scanner.nextLine();
        } while (artista.isEmpty());

        // converte texto pra número (pode dar erro se digitar errado)
        int duracao = Integer.parseInt(scanner.nextLine());

        String genero = scanner.nextLine();

        // adiciona tudo que foi digitado na lista de músicas
        musicas.add(new Musica(titulo, artista, duracao, genero));

        System.out.println("Música cadastrada!");
    }


// listar músicas: percorre a lista e mostra todas as músicas
    public static void listarMusicas() {

        //for para percorrer a lista de músicas e mostrar as informações de cada música
        for (int i = 0; i < musicas.size(); i++) {
            Musica m = musicas.get(i);
            System.out.println(i + " - " + m.getTitulo() + " | " + m.getArtista());
        }
    }

    public static void buscarMusica() {
        String busca = scanner.nextLine().toLowerCase();

        for (Musica m : musicas) {

            // contains deixa você buscar parte do nome (não precisa ser igual)
            if (m.getTitulo().toLowerCase().contains(busca)) {
                System.out.println(m.getTitulo());
            }
        }
    }

    // adiciona nova playlist dentro do menu de playlist
    public static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        String nome = scanner.nextLine();
        usuario.criarPlaylist(nome);
    }

    public static void gerenciarPlaylists() {
        int op;

        // menu da playlist
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música à uma playlist");
            System.out.println("3. Remover música");
            System.out.println("4. Ver detalhes");
            System.out.println("0. Voltar");

            op = lerOpcao();

            switch (op) {
                // mostra todas as playlists que o usuario criou
                case 1:
                    for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                        System.out.println(i + " - " + usuario.getPlaylists().get(i).getNome());
                    }
                    break;

                // adicionar musica na playlist
                case 2:

                System.out.println("\n--- MÚSICAS ---");
                // percorre a lista de músicas e mostra o título de cada música com um número
                for (int i = 0; i < musicas.size(); i++) {
                    System.out.println(i + " - " + musicas.get(i).getTitulo());
                }

                System.out.print("Escolha o número da música: ");
                int m = lerOpcao();

                // listar playlists
                System.out.println("\n--- PLAYLISTS ---");
                for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                    System.out.println(i + " - " + usuario.getPlaylists().get(i).getNome());
                }

                System.out.print("Escolha o número da playlist: ");
                int p = lerOpcao();

                // validação pra não dar erro
                if (m >= 0 && m < musicas.size() && p >= 0 && p < usuario.getPlaylists().size()) {
                    usuario.getPlaylists().get(p).adicionarMusica(musicas.get(m));
                    System.out.println("Música adicionada!");
                } else {
                    System.out.println("Índice inválido!");
                }

                break;

                case 3:
                    // mostrar playlist
                    System.out.println("\n--- PLAYLISTS ---");
                    for (int i = 0; i < usuario.getPlaylists().size(); i++) {
                        System.out.println(i + " - " + usuario.getPlaylists().get(i).getNome());
                    }

                    System.out.print("Escolha o número da playlist: ");
                    int pl = lerOpcao();

                    // valida playlist
                    if (pl >= 0 && pl < usuario.getPlaylists().size()) {

                        Playlist playlist = usuario.getPlaylists().get(pl);

                        // listar músicas da playlist
                        System.out.println("\n--- MÚSICAS DA PLAYLIST ---");
                        for (int i = 0; i < playlist.getMusicas().size(); i++) {
                            System.out.println(i + " - " + playlist.getMusicas().get(i).getTitulo());
                        }

                        System.out.print("Escolha o número da música para remover: ");
                        int mus = lerOpcao();

                        // valida música
                        if (mus >= 0 && mus < playlist.getMusicas().size()) {
                            playlist.removerMusica(mus);
                            System.out.println("Música removida!");
                        } else {
                            System.out.println("Índice da música inválido!");
                        }

                    } else {
                        System.out.println("Índice da playlist inválido!");
                    }

                    break;

                case 4:
    // listar playlists
    System.out.println("\n--- PLAYLISTS ---");
    for (int i = 0; i < usuario.getPlaylists().size(); i++) {
        System.out.println(i + " - " + usuario.getPlaylists().get(i).getNome());
    }

    System.out.print("Escolha o número da playlist: ");
    int idx = lerOpcao();

    // validação
    if (idx >= 0 && idx < usuario.getPlaylists().size()) {

        Playlist pl2 = usuario.getPlaylists().get(idx);

        System.out.println("\n--- DETALHES DA PLAYLIST ---");
        System.out.println("Nome: " + pl2.getNome());

        if (pl2.getMusicas().isEmpty()) {
            System.out.println("Playlist vazia!");
        } else {
            for (int i = 0; i < pl2.getMusicas().size(); i++) {
                Musica m2 = pl2.getMusicas().get(i);
                System.out.println(i + " - " + m2.getTitulo() + " | " + m2.getArtista());
            }
        }

    } else {
        System.out.println("Índice inválido!");
    }

    break;

            }

        } while (op != 0);
        // submenu funciona igual ao menu principal (loop)
    }

    // mostra quantidade total de músicas cadastradas
    public static void estatisticas() {
        System.out.println("Total músicas: " + musicas.size());
    }

    // musicas de teste
    public static void adicionarMusicasTeste() {
        musicas.add(new Musica("Bohemian Rhapsody", "Queen", 354, "Rock"));
        musicas.add(new Musica("Billie Jean", "Michael Jackson", 293, "Pop"));
    }
}