package root;

import UnibsLib.AnsiColors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static root.Main.*;

public class Battaglia {

    public static final int TIMEOUT = 2000;
    private static final ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    public static void inizio() throws InterruptedException
    {
        listaGiocatori.add(giocatore1);
        listaGiocatori.add(giocatore2);
        Collections.shuffle(listaGiocatori);
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


    public static void scontro() throws InterruptedException
    {
        int[][] equilibrio = Equilibrio.creaEquilibrio();

        HashMap<String, Integer> mappaEquilibrio = creaMappaEquilibrio();

        Giocatore giocatoreA = listaGiocatori.get(0);
        Giocatore giocatoreB = listaGiocatori.get(1);

        if(!test)
        {
            assegnaPietre(giocatoreA,giocatoreB);
        }


        System.out.println(Costanti.INIZIO_SCONTRO);
        boolean continuarePartita = true;
        do
        {
            do
            {
                System.out.println(Costanti.SEP);
                Elemento elementoGA = giocatoreA.getTamaGolem().element().getPietre().element();
                Elemento elementoGB = giocatoreB.getTamaGolem().element().getPietre().element();

                int potenza = equilibrio[mappaEquilibrio.get(elementoGA.getNome())][mappaEquilibrio.get(elementoGB.getNome())];
                if (potenza < 0)
                {
                    //secondo fa male al primo
                    if(giocatoreA.getTamaGolem().element().togliVita(Math.abs(potenza))>0)
                    {
                        stampaAttacco(giocatoreB,giocatoreA,Math.abs(potenza));
                    }

                }
                else if (potenza > 0)
                {
                    //primo fa male al secondo
                   if(giocatoreB.getTamaGolem().element().togliVita(Math.abs(potenza))>0)
                   {
                       stampaAttacco(giocatoreA,giocatoreB,Math.abs(potenza));
                   }
                }
                else
                {
                    //nessun danno
                    stampaAttaccoNullo(giocatoreA,giocatoreB);
                }

                giocatoreA.getTamaGolem().element().getPietre().add(giocatoreA.getTamaGolem().element().getPietre().element());
                giocatoreB.getTamaGolem().element().getPietre().add(giocatoreB.getTamaGolem().element().getPietre().element());
                stampaStat(giocatoreA,giocatoreB);
                giocatoreA.getTamaGolem().element().getPietre().remove();
                giocatoreB.getTamaGolem().element().getPietre().remove();
                TimeUnit.MILLISECONDS.sleep(TIMEOUT);
            }

            while (controlloVitaTamagolem(giocatoreA, giocatoreB));

            Giocatore morto,vivo;

            if(giocatoreA.getTamaGolem().element().getVita() <= 0)
            {
                morto = giocatoreA;
                vivo = giocatoreB;
            }
            else
            {
                morto = giocatoreB;
                vivo = giocatoreA;
            }
            morto.getTamaGolem().remove();
            if(morto.getTamaGolem().isEmpty())
            {
                continuarePartita = false;
                fineScontro(vivo,mappaEquilibrio);
            }
            else
            {
                System.out.println(AnsiColors.RED);
                System.out.printf(Costanti.DECEDUTO,morto.getNome());

                System.out.println(Costanti.SCELTA_PIETRE);
                System.out.println(AnsiColors.RESET);
                InterazioneUtente.scegliPietreSingolo(morto,vivo);
            }
        }
        while(continuarePartita);
    }

    private static void assegnaPietre(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.println(giocatoreA.getColore()+Costanti.TURNO+giocatoreA.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreA,giocatoreB);

        System.out.println(giocatoreB.getColore()+Costanti.TURNO+giocatoreB.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreB,giocatoreA);
    }


    private static void stampaStat(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.printf(Costanti.NOME_GIOCATORE, giocatoreA.getNome());
        System.out.printf(Costanti.VITA, giocatoreA.getTamaGolem().element().getNome(),giocatoreA.getTamaGolem().element().getVita());
        System.out.printf(Costanti.TAMAGOLEM_RIMASTI, giocatoreA.getTamaGolem().size());

        System.out.printf(Costanti.NOME_GIOCATORE, giocatoreB.getNome());
        System.out.printf(Costanti.VITA, giocatoreB.getTamaGolem().element().getNome(),giocatoreB.getTamaGolem().element().getVita());
        System.out.printf(Costanti.TAMAGOLEM_RIMASTI, giocatoreB.getTamaGolem().size());
    }

    public static void stampaAttacco(Giocatore giocatoreA, Giocatore giocatoreB, int potenza)
    {
        System.out.printf(Costanti.ATTACCO, giocatoreA.getTamaGolem().element().getNome(),giocatoreA.getNome(),giocatoreA.getTamaGolem().element().getPietre().element().getNome());
        System.out.printf(Costanti.ATTACCO, giocatoreB.getTamaGolem().element().getNome(),giocatoreB.getNome(),giocatoreB.getTamaGolem().element().getPietre().element().getNome());

        System.out.printf(Costanti.DANNO_INFLITTO,giocatoreA.getTamaGolem().element().getNome(),potenza, giocatoreB.getTamaGolem().element().getNome() );
    }

    public static void stampaAttaccoNullo(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.printf(Costanti.ATTACCO, giocatoreA.getTamaGolem().element().getNome(),giocatoreA.getNome(),giocatoreA.getTamaGolem().element().getPietre().element().getNome());
        System.out.printf(Costanti.ATTACCO, giocatoreB.getTamaGolem().element().getNome(),giocatoreB.getNome(),giocatoreB.getTamaGolem().element().getPietre().element().getNome());
        System.out.println(Costanti.COLPO_NULLO);
    }

    private static boolean controlloVitaTamagolem(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        if(gCorrente.getTamaGolem().element().getVita()<=0 || gSuccessivo.getTamaGolem().element().getVita()<=0)
        {
            return false;
        }
        return true;
    }

    private static void fineScontro(Giocatore vivo, HashMap<String, Integer> mappaEquilibrio)
    {
        System.out.println(AnsiColors.GREEN);
        System.out.println(Costanti.MESS_FINE_PARTITA);
        System.out.printf(Costanti.VINCITORE,vivo.getNome());
        System.out.println(Costanti.VISUALIZZA_EQUILIBRIO);
        System.out.println(AnsiColors.RESET);
        Equilibrio.visualizzaEquilibrio(mappaEquilibrio);
    }
}
