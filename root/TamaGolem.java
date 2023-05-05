package root;

import java.util.ArrayDeque;
import java.util.Deque;

public class TamaGolem
{
    private Deque<Elemento> pietre = new ArrayDeque<>();
    private String nome;
    private int vita = Costanti.VITA_MASSIMA;

    public TamaGolem(String nome)
    {
        this.nome = nome;
    }

    public Deque<Elemento> getPietre() {
        return pietre;
    }


    public String getNome() {
        return nome;
    }


    public int getVita() {
        return vita;
    }


    /**
     *Toglie una certa quantita q di vita al tamagolem
     * @param q quantita da togliere
     * @return vita rimasta
     */
    public int togliVita(int q)
    {
        this.vita = this.vita-q;
        if(vita<0)
            this.vita=0;
        return vita;
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
