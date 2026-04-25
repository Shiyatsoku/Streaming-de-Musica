public class Musica {

    String titulo;
    String artista;
    int duracao;
    String genero;

    public Musica(String titulo, String artista, int duracao, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
        this.genero = genero;
    }

    public String formatarDuracao() {
        int min = duracao / 60;
        int seg = duracao % 60;
        return String.format("%d:%02d", min, seg);
    }
}