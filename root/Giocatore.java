package root;

import UnibsLib.AnsiColors;

import java.util.ArrayDeque;
import java.util.Deque;

public class Giocatore
{
    private final String nome;
    private Deque<TamaGolem> tamaGolem = new ArrayDeque<>();
    private final AnsiColors colore;

    public Giocatore(String nome,AnsiColors colore)
    {
        this.nome = nome;
        this.colore = colore;
    }

    public String getNome()
    {
        return nome;
    }

    public Deque<TamaGolem> getTamaGolem()
    {
        return tamaGolem;
    }

    public void setTamaGolem(Deque<TamaGolem> tamaGolem)
    {
        this.tamaGolem = tamaGolem;
    }

    public AnsiColors getColore()
    {
        return colore;
    }

    @Override
    public String toString()
    {
        return "Giocatore{" +
                "nome='" + nome + '\'' +
                ", tamaGolem=" + tamaGolem +
                '}';
    }
}
