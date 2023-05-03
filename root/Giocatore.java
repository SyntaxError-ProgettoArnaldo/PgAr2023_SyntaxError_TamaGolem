package root;

import UnibsLib.AnsiColors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Giocatore
{
    private String nome;
    private Deque<TamaGolem> tamaGolem = new ArrayDeque<>();
    private AnsiColors colore;

    public Giocatore(String nome, Deque<TamaGolem> tamaGolem,AnsiColors colore) {
        this.nome = nome;
        this.tamaGolem =  tamaGolem;
        this.colore = colore;
    }

    public Giocatore()
    {}

    public Giocatore(Giocatore giocatore) {
        this.colore = giocatore.colore;
        this.tamaGolem = giocatore.tamaGolem;
        this.nome = giocatore.nome;
    }

    public Giocatore(AnsiColors colore) {
        this.colore = colore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Deque<TamaGolem> getTamaGolem() {
        return tamaGolem;
    }

    public void setTamaGolem(Deque<TamaGolem> tamaGolem) {
        this.tamaGolem = tamaGolem;
    }

    public AnsiColors getColore() {
        return colore;
    }

    public void setColore(AnsiColors colore) {
        this.colore = colore;
    }

    @Override
    public String toString() {
        return "Giocatore{" +
                "nome='" + nome + '\'' +
                ", tamaGolem=" + tamaGolem +
                '}';
    }
}
