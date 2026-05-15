package br.com.streaming.modelo;

import br.com.streaming.util.Cores;

public class UsuarioFree extends Usuario {

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void criarPlaylist(String nome) {

        if (playlists.size() >= 3) {
            System.out.println("Limite de playlists atingido (3)");
        } else {
            super.criarPlaylist(nome);
        }
    }

    @Override
public void reproduzirMusica(Musica m) {

    if (reproducoes >= 30) {
        System.out.println(Cores.VERMELHO + "Limite de reproduções atingido!" + Cores.RESET);
        return;
    }

    reproducoes++;

    if (reproducoes % 3 == 0) {
        System.out.println(Cores.VERMELHO + "\n* ANÚNCIO *\n" + Cores.RESET);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }
    }

    System.out.println("Reproduzindo: " + m.getTitulo());
    }
}