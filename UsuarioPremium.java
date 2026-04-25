public class UsuarioPremium extends Usuario {

    private String plano;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
    }

    @Override
    public void exibirMenu() {
        System.out.println("\n=== MENU PREMIUM ===");
        System.out.println("1. Reproduzir música (Alta Qualidade)");
        System.out.println("2. Ver histórico");
        System.out.println("3. Criar playlist (ilimitado)");
        System.out.println("4. Baixar música");
        System.out.println("5. Ver músicas baixadas");
        System.out.println("0. Sair");
    }

    public void baixarMusica(Musica m) {
        System.out.println("Música baixada: " + m.getTitulo());
    }
}