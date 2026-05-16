package br.com.streaming.modelo;

import java.util.Scanner;

import br.com.streaming.servico.Reproduzivel;
import br.com.streaming.util.Cores;

public class Musica implements Reproduzivel {


    private String titulo;
    private String artista;
    private int duracao;
    private String genero;

    private boolean tocando = false;
    private boolean pausada = false;

    public Musica(String titulo, String artista, int duracao, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracao(duracao);
        setGenero(genero);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        this.titulo = titulo.trim();
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista.trim();
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao <= 0 || duracao >= 3600) {
            throw new IllegalArgumentException("Duração inválida");
        }
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null) throw new IllegalArgumentException("Gênero inválido");

        String g = genero.trim().toLowerCase();

        if (!(g.equals("pop") || g.equals("rock") || g.equals("jazz") ||
              g.equals("eletronica") || g.equals("eletrônica") ||
              g.equals("hip-hop") || g.equals("clássica") || g.equals("classica"))) {
            throw new IllegalArgumentException("Gênero inválido");
        }

        this.genero = g;
    }

    public String formatarDuracao() {
        int min = duracao / 60;
        int seg = duracao % 60;
        return String.format("%d:%02d", min, seg);
    }

    // Implementação dos métodos da interface Reproduzivel
@Override
public void reproduzir() {

    Scanner scanner = new Scanner(System.in);

    tocando = true;
    pausada = false;

    while (tocando) {

        System.out.println(Cores.VERDE + """
╔════════════════════════════════════╗
║         REPRODUZINDO              ║
╚════════════════════════════════════╝
""" + Cores.RESET);

        System.out.println("> Música: " + titulo);
        System.out.println("> Artista: " + artista);
        System.out.println("> Duração: " + formatarDuracao());

        if (pausada) {

            System.out.println(Cores.VERMELHO + "\nSTATUS: PAUSADO" + Cores.RESET);

            System.out.println("""
            
1 - Continuar
2 - Parar
""");

        } else {

            System.out.println(Cores.VERDE + "\nSTATUS: TOCANDO" + Cores.RESET);

            System.out.println("""
            
1 - Pausar
2 - Parar
""");
        }

        System.out.print("Escolha: ");

        int op;

        try {
            op = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            op = -1;
        }

        if (!pausada) {

            switch (op) {

                case 1:
                    pausar();
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
                    pausada = false;
                    System.out.println(Cores.VERDE + "\n>> Música retomada!" + Cores.RESET);
                    break;

                case 2:
                    parar();
                    break;

                default:
                    System.out.println(Cores.VERMELHO + "Opção inválida!" + Cores.RESET);
            }
        }
    }
}

@Override
public void pausar() {
    pausada = true;
    System.out.println(Cores.VERMELHO + "\n|| Música pausada!" + Cores.RESET);
}

@Override
public void parar() {
    tocando = false;
    System.out.println(Cores.VERMELHO + "\n[] Reprodução encerrada!" + Cores.RESET);
}

@Override
public int getDuracaoTotal() {
    return duracao;
    }
}