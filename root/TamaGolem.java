package root;

import java.util.ArrayList;

public class TamaGolem
{
    private ArrayList<Elemento> pietre;
    private String nome;
    private int vita = Costanti.VITA_MASSIMA;

    public TamaGolem(ArrayList<Elemento> pietre, String nome, int vita) {
        this.pietre = pietre;
        this.nome = nome;
        this.vita = vita;
    }

    public TamaGolem() {}

    public ArrayList<Elemento> getPietre() {
        return pietre;
    }

    public void setPietre(ArrayList<Elemento> pietre) {
        this.pietre = pietre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    @Override
    public String
    toString() {
        return "TamaGolem{" +
                "pietre=" + pietre +
                ", nome='" + nome + '\'' +
                ", vita=" + vita +
                '}';
    }
}
