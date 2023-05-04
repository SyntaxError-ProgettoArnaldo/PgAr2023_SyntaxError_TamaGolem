package root;

import UnibsLib.AnsiColors;
import UnibsLib.PrettyStrings;

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

    public static HashMap<String, Integer> creaMappaEquilibrio()
    {
        HashMap<String, Integer> mappaEquilibrio = new HashMap<>();
        for (int k = 0; k < Costanti.SCORTA_COMUNE.size(); k++)
        {
            mappaEquilibrio.put(String.valueOf(Costanti.SCORTA_COMUNE.keySet().toArray()[k]),k);
        }
        return mappaEquilibrio;

    }


    public static void scontro() throws InterruptedException {

        System.out.println(Costanti.INIZIO_SCONTRO);

        int[][] equilibrio = Equilibrio.creaEquilibrio();

        HashMap<String, Integer> mappaEquilibrio = creaMappaEquilibrio();

        Giocatore giocatoreA = listaGiocatori.get(0);
        Giocatore giocatoreB = listaGiocatori.get(1);

        System.out.println(giocatoreA.getColore()+Costanti.TURNO+giocatoreA.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreA,giocatoreB);

        System.out.println(giocatoreB.getColore()+Costanti.TURNO+giocatoreB.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreB,giocatoreA);


        boolean continuarePartita = true;
        do {

            do {

                Elemento elementoGA = giocatoreA.getTamaGolem().element().getPietre().element();
                Elemento elementoGB = giocatoreB.getTamaGolem().element().getPietre().element();

                int potenza = equilibrio[mappaEquilibrio.get(elementoGA.getNome())][mappaEquilibrio.get(elementoGB.getNome())];
                if (potenza < 0) {

                    //secondo fa male al primo
                    stampaAttacco(giocatoreB,giocatoreA,potenza);
                    giocatoreA.getTamaGolem().element().togliVita(Math.abs(potenza));

                } else if (potenza > 0) {
                    //primo fa male al secondo
                    stampaAttacco(giocatoreA,giocatoreB,potenza);
                    giocatoreB.getTamaGolem().element().togliVita(Math.abs(potenza));

                } else {
                    //nessun danno
                    System.out.println("NESSUN DANNO");
                }

                giocatoreA.getTamaGolem().element().getPietre().add(giocatoreA.getTamaGolem().element().getPietre().element());
                giocatoreB.getTamaGolem().element().getPietre().add(giocatoreB.getTamaGolem().element().getPietre().element());
                //System.out.println("vita 1--> " + giocatoreA.getTamaGolem().element().getVita());
                //System.out.println("vita 2--> " + giocatoreB.getTamaGolem().element().getVita());
                stampaStat(giocatoreA,giocatoreB);
                giocatoreA.getTamaGolem().element().getPietre().remove();
                giocatoreB.getTamaGolem().element().getPietre().remove();
                TimeUnit.MILLISECONDS.sleep(2000);
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

    private static void stampaStat(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.println(Costanti.SEP);


        System.out.println(Costanti.COLORE_BASE);
        System.out.println("VITA "+giocatoreA.getTamaGolem().element().getNome()+": "+giocatoreA.getTamaGolem().element().getVita());
        System.out.println("VITA "+giocatoreB.getTamaGolem().element().getNome()+": "+giocatoreB.getTamaGolem().element().getVita());
        System.out.println(AnsiColors.RESET);

    }

    public static void stampaAttacco(Giocatore giocatoreA, Giocatore giocatoreB, int potenza)
    {
        System.out.println(Costanti.COLORE_BASE);
        System.out.println(giocatoreA.getNome()+" CON IL SUO TAMAGOLEM "+giocatoreA.getTamaGolem().element().getNome()+" LANCIA LA PIETRA AL TAMAGOLEM "+
                giocatoreB.getTamaGolem().element().getNome()+" DI "+giocatoreB.getNome()+ " INFLIGGENDOGLI "+Math.abs(potenza)+" DI DANNO");
        System.out.println(AnsiColors.RESET);
    }

    private static boolean controlloVita(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        if(gCorrente.getTamaGolem().element().getVita()<=0 || gSuccessivo.getTamaGolem().element().getVita()<=0)
        {
            return false;
        }
        return true;
    }








}
