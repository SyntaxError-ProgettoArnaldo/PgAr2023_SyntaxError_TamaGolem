package root;

public class Elemento {
    private final String nome;

    public Elemento(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }

    @Override
    public String toString()
    {
        return "Elemento{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
