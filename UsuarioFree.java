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
    public void exibirMenu() {
        System.out.println("\n=== MENU FREE ===");
        System.out.println("1. Reproduzir música");
        System.out.println("2. Ver histórico");
        System.out.println("3. Criar playlist (máx. 3)");
        System.out.println("4. 💎 Fazer upgrade para Premium");
        System.out.println("0. Sair");
    }
}