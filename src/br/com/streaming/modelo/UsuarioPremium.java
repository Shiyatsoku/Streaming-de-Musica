package br.com.streaming.modelo;

import java.util.ArrayList;

import br.com.streaming.servico.Baixavel;
import br.com.streaming.util.Cores;

public class UsuarioPremium extends Usuario implements Baixavel {

    private String plano;
    private ArrayList<Musica> baixadas = new ArrayList<>();

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
    }

@Override
public void reproduzirMusica(Musica m) {
    reproducoes++;
    System.out.println("Reproduzindo em alta qualidade: " + m.getTitulo());
}

    @Override
public void baixar(Musica musica) {

    if (estaBaixada(musica)) {

        System.out.println(
                Cores.VERMELHO +
                "\nMúsica já baixada!" +
                Cores.RESET
        );

        return;
    }

    System.out.println(Cores.VERDE + """
┌────────────────────────────────────┐
│          BAIXANDO MÚSICA           │
└────────────────────────────────────┘
""" + Cores.RESET);

    System.out.println("> Música: " + musica.getTitulo());
    System.out.println("> Artista: " + musica.getArtista());

    try {
        Thread.sleep(2000);
    } catch (Exception e) {

    }

    baixadas.add(musica);

    System.out.println(
            Cores.VERDE +
            "\nDownload concluído!" +
            Cores.RESET
    );
}

@Override
public void removerDownload(Musica musica) {

    if (baixadas.remove(musica)) {

        System.out.println(
                Cores.VERMELHO +
                "\nDownload removido!" +
                Cores.RESET
        );

    } else {

        System.out.println(
                Cores.VERMELHO +
                "\nMúsica não encontrada!" +
                Cores.RESET
        );
    }
}

@Override
public boolean estaBaixada(Musica musica) {
    return baixadas.contains(musica);
}

@Override
public int getTamanhoBaixados() {
    return baixadas.size();
}

public void listarBaixadas() {

    if (baixadas.isEmpty()) {

        System.out.println("\nNenhuma música baixada.");

    } else {

        System.out.println();

        for (int i = 0; i < baixadas.size(); i++) {

            Musica m = baixadas.get(i);

            System.out.println(
                    i + " - " +
                    m.getTitulo() +
                    " | " +
                    m.getArtista()
            );
        }
    }
}

    public ArrayList<Musica> getBaixadas() {
    return baixadas;
    }
}