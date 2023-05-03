package root;

public class Elemento {
    private String nome;
    private int rigaMat;

    public Elemento(String nome, int rigaMat) {
        this.nome = nome;
        this.rigaMat = rigaMat;
    }

    public Elemento() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Elemento{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
