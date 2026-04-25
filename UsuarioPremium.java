import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private String plano;
    private ArrayList<Musica> baixadas = new ArrayList<>();

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
    }

    @Override
    public void reproduzirMusica(Musica m) {
        System.out.println("Reproduzindo em alta qualidade: " + m.getTitulo());
    }

    public void baixarMusica(Musica m) {
        baixadas.add(m);
        System.out.println("Música baixada: " + m.getTitulo());
    }

    public void listarBaixadas() {
        if (baixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada");
        } else {
            for (int i = 0; i < baixadas.size(); i++) {
                System.out.println(i + " - " + baixadas.get(i).getTitulo());
            }
        }
    }
}