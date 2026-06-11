import java.util.ArrayList;

public class Playlist {

    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<>();

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
        if (m == null) throw new IllegalArgumentException("Música inválida");
        musicas.add(m);
    }

    public void removerMusica(int index) {
        if (index >= 0 && index < musicas.size()) {
            musicas.remove(index);
        } else {
            throw new IllegalArgumentException("Índice inválido");
        }
    }
}