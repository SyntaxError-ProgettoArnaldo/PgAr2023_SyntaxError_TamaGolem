package root;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Battaglia {
    public static void inizio()
    {
        ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
        listaGiocatori.add(Main.giocatore1);
        listaGiocatori.add(Main.giocatore2);
        Collections.shuffle(listaGiocatori);  //random chi inizia

        System.out.println("Inizia: "+listaGiocatori.get(0));
        InterfacciaUtente.scegliPietre(listaGiocatori.get(0));
    }
}
