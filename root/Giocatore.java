package root;

import UnibsLib.AnsiColors;

import java.util.ArrayList;

public class Giocatore
{
    private String nome;
    private ArrayList<TamaGolem> tamaGolem;

    private AnsiColors colore;

    public Giocatore(String nome, ArrayList<TamaGolem> tamaGolem,AnsiColors colore) {
        this.nome = nome;
        this.tamaGolem = tamaGolem;
        this.colore = colore;
    }

    public Giocatore()
    {}

    public Giocatore(AnsiColors colore) {
        this.colore = colore;
    }

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
