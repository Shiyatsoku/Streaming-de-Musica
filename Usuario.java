import java.util.ArrayList;

public class Usuario {

    ArrayList<Playlist> playlists = new ArrayList<>();

    // adicionar a playlist na lista de playlists do usuário
    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
    }
}