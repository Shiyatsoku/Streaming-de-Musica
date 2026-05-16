package br.com.streaming.modelo;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.streaming.servico.Reproduzivel;
import br.com.streaming.util.Cores;

public class Playlist implements Reproduzivel {

    protected String nome;
    protected ArrayList<Musica> musicas = new ArrayList<>();
    protected String descricao;

    protected boolean tocando = false;
    protected boolean pausada = false;

    public Playlist(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        this.nome = nome.trim();
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void adicionarMusica(Musica m) {

        if (m == null) {
            throw new IllegalArgumentException("Música inválida");
        }

        musicas.add(m);
    }

    public void removerMusica(int index) {

        if (index >= 0 && index < musicas.size()) {
            musicas.remove(index);
        } else {
            throw new IllegalArgumentException("Índice inválido");
        }
    }

@Override
public void reproduzir() {

    Scanner scanner = new Scanner(System.in);

    tocando = true;
    pausada = false;

    if (musicas.isEmpty()) {
    System.out.println(Cores.VERMELHO + "\nNenhuma música disponível nesta playlist." + Cores.RESET);
    return;
}

    int atual = 0;

    while (tocando && atual < musicas.size()) {

        Musica m = musicas.get(atual);

        System.out.println(Cores.VERDE + """
╔════════════════════════════════════╗
║        TOCANDO PLAYLIST            ║
╚════════════════════════════════════╝
""" + Cores.RESET);

        System.out.println("> Playlist: " + nome);
        System.out.println("> Música: " + m.getTitulo());
        System.out.println("> Artista: " + m.getArtista());

        if (pausada) {

            System.out.println(Cores.VERMELHO + "\nSTATUS: PAUSADO" + Cores.RESET);

            System.out.println("""
            
1 - Continuar
2 - Parar
""");

        } else {

            System.out.println(Cores.VERDE + "\nSTATUS: TOCANDO" + Cores.RESET);

            System.out.println("""
            
1 - Próxima música
2 - Pausar
3 - Parar
""");
        }

        System.out.print("Escolha: ");

        int op;

        try {
            op = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            op = -1;
        }

        if (pausada) {

            switch (op) {

                case 1:
                    pausada = false;
                    System.out.println(Cores.VERDE + "\n>> Playlist retomada!" + Cores.RESET);
                    break;

                case 2:
                    parar();
                    break;

                default:
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }

        } else {

            switch (op) {

                case 1:
                    atual++;
                    break;

                case 2:
                    pausar();
                    break;

                case 3:
                    parar();
                    break;

                default:
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }
        }
    }

    if (atual >= musicas.size()) {

        System.out.println(Cores.VERDE + "\nPlaylist finalizada!" + Cores.RESET);
    }
}

@Override
public void pausar() {

    pausada = true;

    System.out.println(Cores.VERMELHO + "\n|| Playlist pausada!" + Cores.RESET);
}

@Override
public void parar() {

    tocando = false;

    System.out.println(Cores.VERMELHO + "\n[] Reprodução encerrada!" + Cores.RESET);
}

@Override
public int getDuracaoTotal() {

    int total = 0;

    for (Musica m : musicas) {
        total += m.getDuracao();
    }

    return total;
}
}