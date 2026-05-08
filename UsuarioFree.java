public class UsuarioFree extends Usuario {

    private int reproducoes = 0;

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
            System.out.println("Limite de reproduções atingido!");
            return;
        }

        reproducoes++;

        System.out.println("Reproduzindo: " + m.getTitulo());

        if (reproducoes % 3 == 0) {
            System.out.println("🔊 Anúncio...");
        }
    }
}