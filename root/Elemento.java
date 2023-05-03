package root;

public class Elemento {
    private String nome;

    public Elemento(String nome) {
        this.nome = nome;
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
