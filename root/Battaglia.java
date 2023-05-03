package root;

import java.util.ArrayList;
import java.util.Collections;
import static root.Main.*;

public class Battaglia {

    private static ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    public static void inizio()
    {
        Costanti.inizializzaScortaComune();
        listaGiocatori.add(giocatore1);
        listaGiocatori.add(giocatore2);
        Collections.shuffle(listaGiocatori);
        //InterfacciaUtente.scegliPietre(listaGiocatori);
        scontro();
    }

    public static void scontro()
    {
        Giocatore giocatoreA = listaGiocatori.get(0);
        Giocatore giocatoreB = listaGiocatori.get(1);


        System.out.println("è il turno di: "+giocatoreA.getNome());
        InterfacciaUtente.scegliPietreSingolo(giocatoreA,giocatoreB);

        scambiaTurno(giocatoreA,giocatoreB);
        System.out.println("è il turno di: "+giocatoreA.getNome());
        InterfacciaUtente.scegliPietreSingolo(giocatoreA,giocatoreB);
        scambiaTurno(giocatoreA,giocatoreB);

        do {



            do
            {





            }while (controlloNuovoEvocazione(giocatoreA,giocatoreB));
            //togliere tama da lista
        }while(continuarePartita(giocatoreA,giocatoreB));












    }

    private static boolean controlloNuovoEvocazione(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        if(gCorrente.getTamaGolem().element().getVita()<=0 || gSuccessivo.getTamaGolem().element().getVita()<=0)
        {
            return false;
        }
        return true;
    }

    public static boolean continuarePartita(Giocatore gCorrente, Giocatore gSuccessivo)
    {

    }



    public static void scambiaTurno(Giocatore gCorrente,Giocatore gSuccessivo)
    {
        Giocatore scambio = new Giocatore(gCorrente);
        gCorrente = gSuccessivo;
        gSuccessivo = scambio;
    }




}
