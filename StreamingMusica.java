import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de Streaming de Música - CP1
 *
 */

public class StreamingMusica {

    // ArrayLists para armazenar os dados das músicas
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adicionar músicas de teste
        adicionarMusicasTeste();

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }

    // Menu principal

    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }


// FORNECIDO: Lê opção com tratamento de erro

    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }



    // Switch case para decidir a opção
    
    public static void processarOpcao(int opcao) {
    switch (opcao) {
        case 1:
            cadastrarMusica();
            break;
        case 2:
            listarMusicas();
            break;
        case 3:
            buscarPorTitulo();
            break;
        case 0:
            System.out.println("Encerrando o sistema...");
            break;
        default:
            System.out.println("Opção inválida!");
    }
}

//teste





    // cadastrar musica

    public static void cadastrarMusica() {
    System.out.println("\n--- CADASTRAR MÚSICA ---");

    // digitar o titulo
    String titulo;

    do {
        System.out.print("Título: ");
        titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Título não pode estar vazio!");
        }
    } while (titulo.isEmpty());

    // digitar o artista
    String artista;

    do {
        System.out.print("Artista: ");
        artista = scanner.nextLine().trim();
        if (artista.isEmpty()) {
            System.out.println("Artista não pode estar vazio!");
        }
    } while (artista.isEmpty());

    // duração em segundos
    int duracao = 0;
    boolean duracaoValida = false;

    while (!duracaoValida) {
        System.out.print("Duração (em segundos): ");

        // try para o programa não parar
        try {
            duracao = Integer.parseInt(scanner.nextLine());
            
            if (duracao > 0) {
                duracaoValida = true;
            } 
            
            else {
                System.out.println("A duração deve ser maior que zero!");
            }
        }
        
        // caso dê errado, ele cai no catch e volta para o while
        catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
        }
    }

    // digitar o gênero

    String genero;

    do {
        System.out.print("Gênero: ");
        genero = scanner.nextLine().trim();
        if (genero.isEmpty()) {
            System.out.println("Gênero não pode estar vazio!");
        }
    } while (genero.isEmpty());

    // adicionar aos arraylists
    titulos.add(titulo);
    artistas.add(artista);
    duracoes.add(duracao);
    generos.add(genero);

    // finalização do cadastro
    System.out.println("Música cadastrada com sucesso!");
}






    public static void listarMusicas() {
    System.out.println("\n--- MÚSICAS CADASTRADAS ---");

    // verifica se está vazio
    if (titulos.isEmpty()) {
        System.out.println("⚠️ Nenhuma música cadastrada!");
        return;
    }

    // for para caminhar pelos arrays cadastrados e exibibição 
    for (int i = 0; i < titulos.size(); i++) {
        
        System.out.println("\nMúsica " + (i + 1));
        System.out.println("Título: " + titulos.get(i));
        System.out.println("Artista: " + artistas.get(i));
        System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
        System.out.println("Gênero: " + generos.get(i));
    }
}





// buscar por titulo

public static void buscarPorTitulo() {
    System.out.println("\n--- BUSCAR POR TÍTULO ---");

    System.out.print("Digite o título: ");
    String busca = scanner.nextLine().toLowerCase();

    boolean encontrado = false;

    // for para verificar as músicas
    for (int i = 0; i < titulos.size(); i++) {

        // verifica se o título tem o texto buscado
        if (titulos.get(i).toLowerCase().contains(busca)) {

            System.out.println("\nMúsica encontrada:");
            System.out.println("Título: " + titulos.get(i));
            System.out.println("Artista: " + artistas.get(i));
            System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
            System.out.println("Gênero: " + generos.get(i));

            encontrado = true;
        }
    }

    // se não for encontrado
    if (!encontrado) {
        System.out.println("Nenhuma música encontrada!");
    }
}





    /**
     * FORNECIDO: Formata duração
     */
    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    /**
     * FORNECIDO: Músicas de teste
     */
    public static void adicionarMusicasTeste() {
        titulos.add("Bohemian Rhapsody");
        artistas.add("Queen");
        duracoes.add(354);
        generos.add("Rock");

        titulos.add("Billie Jean");
        artistas.add("Michael Jackson");
        duracoes.add(293);
        generos.add("Pop");
    }
}
