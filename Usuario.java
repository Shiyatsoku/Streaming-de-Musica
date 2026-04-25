import java.util.ArrayList;

public class Usuario {

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
    }

    public void reproduzirMusica(Musica m) {
        System.out.println("Reproduzindo: " + m.getTitulo());
    }

    public void exibirMenu() {
        System.out.println("Menu padrão");
    }
}