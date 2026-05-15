package br.com.streaming.modelo;

import java.util.ArrayList;

public class Usuario {

    protected int reproducoes = 0;
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario(String nome, String email) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }

        this.nome = nome.trim();
        this.email = email.trim();
    }

    // GETTERS
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void criarPlaylist(String nome) {
        playlists.add(new PlaylistPersonalizada(nome));
    }

    public void reproduzirMusica(Musica m) {
        reproducoes++;
        System.out.println("Reproduzindo: " + m.getTitulo());
    }

    public int getReproducoes() {
        return reproducoes;
    }
}