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

    /**
     * LIVE CODING: Professor implementa este método
     * Exibe o menu principal do sistema
     */
    public static void exibirMenu() {
        // TODO: Professor implementa ao vivo
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar por título");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    /**
     * FORNECIDO: Lê opção com tratamento de erro
     */
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * LIVE CODING: Professor implementa este método
     * Processa a opção escolhida
     */
    public static void processarOpcao(int opcao) {
        // TODO: Professor implementa ao vivo
    }

    /**
     * LIVE CODING: Professor implementa este método (PRINCIPAL)
     * Cadastra uma nova música
     */
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");

        // TODO: Professor implementa ao vivo
        // 1. Solicitar título
        // 2. Validar título (não vazio)
        // 3. Solicitar artista
        // 4. Validar artista
        // 5. Solicitar duração
        // 6. Validar duração
        // 7. Adicionar nos ArrayLists
        // 8. Exibir mensagem de sucesso

        System.out.println("⚠️ TODO: Implementar cadastro");
    }

    /**
     * LIVE CODING: Professor implementa este método
     * Lista todas as músicas
     */
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");

        // TODO: Professor implementa ao vivo
        // 1. Verificar se está vazio
        // 2. Percorrer ArrayLists
        // 3. Exibir cada música formatada

        System.out.println("⚠️ TODO: Implementar listagem");
    }

    /**
     * ALUNO IMPLEMENTA: Busca por título
     * (Professor mostra a estrutura, alunos completam depois)
     */
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");

        // TODO: Aluno implementa
        System.out.print("Digite o título: ");
        String busca = scanner.nextLine().toLowerCase();

        // TODO: Percorrer e buscar
        // Dica: usar .contains() e .toLowerCase()

        System.out.println("⚠️ TODO: Implementar busca");
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
