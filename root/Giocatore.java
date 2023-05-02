package root;

import java.util.ArrayList;

public class Giocatore
{
    private String nome;
    private ArrayList<TamaGolem> tamaGolem;

    public Giocatore(String nome, ArrayList<TamaGolem> tamaGolem) {
        this.nome = nome;
        this.tamaGolem = tamaGolem;
    }

    public Giocatore()
    {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<TamaGolem> getTamaGolem() {
        return tamaGolem;
    }

    public void setTamaGolem(ArrayList<TamaGolem> tamaGolem) {
        this.tamaGolem = tamaGolem;
    }

    @Override
    public String toString() {
        return "Giocatore{" +
                "nome='" + nome + '\'' +
                ", tamaGolem=" + tamaGolem +
                '}';
    }
}
