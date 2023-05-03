package root;

import java.util.ArrayList;
import java.util.Collections;

public class Battaglia {
    public static void inizio()
    {
        Costanti.inizializzaScortaComune();

        ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
        listaGiocatori.add(Main.giocatore1);
        listaGiocatori.add(Main.giocatore2);
        Collections.shuffle(listaGiocatori);
        InterfacciaUtente.scegliPietre(listaGiocatori);
    }
}
