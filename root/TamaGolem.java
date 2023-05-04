package root;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TamaGolem
{
    private Deque<Elemento> pietre = new ArrayDeque<>();
    private String nome;
    private int vita = Costanti.VITA_MASSIMA;

    public TamaGolem(Deque<Elemento> pietre, String nome, int vita) {
        this.pietre = pietre;
        this.nome = nome;
        this.vita = vita;
    }

    public TamaGolem(String nome)
    {
        this.nome = nome;
    }

    public Deque<Elemento> getPietre() {
        return pietre;
    }

    public void setPietre(Deque<Elemento> pietre) {
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

    /**
     *Toglie una certa quantita q di vita al tamagolem
     * @param q quantita da togliere
     */
    public void togliVita(int q)
    {
        this.vita = this.vita-q;
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
