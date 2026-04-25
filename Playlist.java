import java.util.ArrayList;

public class Playlist {

    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist(String nome) {
        this.nome = nome;
    }

    public void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    public void removerMusica(int index) {
        if (index >= 0 && index < musicas.size()) {
            musicas.remove(index);
        }
    }
}