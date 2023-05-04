package root;

import javax.print.event.PrintEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static root.Main.*;

public class Battaglia {

    private static ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    public static void inizio() throws InterruptedException {
        Costanti.inizializzaScortaComune();
        listaGiocatori.add(giocatore1);
        listaGiocatori.add(giocatore2);
        Collections.shuffle(listaGiocatori);
        //InterfacciaUtente.scegliPietre(listaGiocatori);
        scontro();
    }

    public static void scontro() throws InterruptedException {
        int[][] equilibrio = Equilibrio.creaEquilibrio();
        HashMap<String, Integer> mappa = new HashMap<>();
        for (int k = 0; k < Costanti.SCORTA_COMUNE.size(); k++) {
            mappa.put(String.valueOf(Costanti.SCORTA_COMUNE.keySet().toArray()[k]),k);
        }
        System.out.println(mappa);


        Giocatore giocatoreA = listaGiocatori.get(0);
        Giocatore giocatoreB = listaGiocatori.get(1);

        System.out.println("è il turno di: "+giocatoreA.getNome());
        InterazioneUtente.scegliPietreSingolo(giocatoreA,giocatoreB);

        System.out.println("è il turno di: "+giocatoreA.getNome());
        InterazioneUtente.scegliPietreSingolo(giocatoreB,giocatoreA);
        boolean continuarePartita = true;
        do {


            do {
                Elemento elementoGA = giocatoreA.getTamaGolem().element().getPietre().element();
                Elemento elementoGB = giocatoreB.getTamaGolem().element().getPietre().element();

                int potenza = equilibrio[mappa.get(elementoGA.getNome())][mappa.get(elementoGB.getNome())];
                System.out.println("Potenza-->" + potenza);
                if (potenza < 0) {
                    //secondo fa male al primo
                    giocatoreA.getTamaGolem().element().togliVita(Math.abs(potenza));
                    System.out.println(giocatoreB.getTamaGolem().element().getNome() + " fa " + potenza + " di danno a " + giocatoreA.getTamaGolem().element().getNome());
                } else if (potenza > 0) {
                    //primo fa male al secondo
                    giocatoreB.getTamaGolem().element().togliVita(Math.abs(potenza));
                    System.out.println(giocatoreA.getTamaGolem().element().getNome() + " fa " + potenza + " di danno a " + giocatoreB.getTamaGolem().element().getNome());

                } else {
                    //nessun danno
                    System.out.println("NESSUN DANNO");
                }

                giocatoreA.getTamaGolem().element().getPietre().add(giocatoreA.getTamaGolem().element().getPietre().element());
                giocatoreB.getTamaGolem().element().getPietre().add(giocatoreB.getTamaGolem().element().getPietre().element());
                System.out.println("vita 1--> " + giocatoreA.getTamaGolem().element().getVita());
                System.out.println("vita 2--> " + giocatoreB.getTamaGolem().element().getVita());
                giocatoreA.getTamaGolem().element().getPietre().remove();
                giocatoreB.getTamaGolem().element().getPietre().remove();
                TimeUnit.MILLISECONDS.sleep(1000);
            }
            while (controlloVita(giocatoreA, giocatoreB));

            Giocatore morto;
            Giocatore vivo;

            if(giocatoreA.getTamaGolem().element().getVita() <= 0)
            {
                morto = new Giocatore(giocatoreA);
                vivo = new Giocatore(giocatoreB);
            }
            else
            {
                morto = new Giocatore(giocatoreB);
                vivo = new Giocatore(giocatoreA);
            }
            morto.getTamaGolem().remove();
            if(morto.getTamaGolem().isEmpty())
            {
                continuarePartita = false;
            }
            else
            {
                System.out.println("è il turno di: "+morto.getNome());

                InterazioneUtente.scegliPietreSingolo(morto,vivo);

            }

        }
        while(continuarePartita);


    }

    private static void rievoca(Giocatore morto)
    {
        morto.getTamaGolem().remove();
        if(morto.getTamaGolem().isEmpty())
        {

        }
    }

    private static boolean controlloVita(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        if(gCorrente.getTamaGolem().element().getVita()<=0 || gSuccessivo.getTamaGolem().element().getVita()<=0)
        {
            return false;
        }
        return true;
    }

    public static boolean continuarePartita(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        return true;
    }



    public static void scambiaTurno(Giocatore gCorrente,Giocatore gSuccessivo)
    {
        Giocatore scambio = new Giocatore(gCorrente);
        gCorrente = gSuccessivo;
        gSuccessivo = scambio;
    }





}
