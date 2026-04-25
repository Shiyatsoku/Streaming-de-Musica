import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome) {
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

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    // adicionar a playlist na lista de playlists do usuário
    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
    }
}