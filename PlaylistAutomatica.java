import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {

        super(nome);

        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {

        System.out.println("Playlist automática");
        System.out.println("Critério: " + criterio);

        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {

        musicas.clear();

        if (criterio.equalsIgnoreCase("top")) {

            for (Musica m : todasMusicas) {
                musicas.add(m);
            }

        } else if (criterio.equalsIgnoreCase("recentes")) {

            for (Musica m : todasMusicas) {
                musicas.add(m);
            }
        }
    }
}