package root;

import UnibsLib.AnsiColors;

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



        int[][] equilibrio = Equilibrio.creaEquilibrio();

        HashMap<String, Integer> mappaEquilibrio = creaMappaEquilibrio();

        Giocatore giocatoreA = listaGiocatori.get(0);
        Giocatore giocatoreB = listaGiocatori.get(1);

        System.out.println(giocatoreA.getColore()+Costanti.TURNO+giocatoreA.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreA,giocatoreB);

        System.out.println(giocatoreB.getColore()+Costanti.TURNO+giocatoreB.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreB,giocatoreA);

        System.out.println(Costanti.INIZIO_SCONTRO);
        boolean continuarePartita = true;
        do {

            do {
                System.out.println(Costanti.SEP);
                Elemento elementoGA = giocatoreA.getTamaGolem().element().getPietre().element();
                Elemento elementoGB = giocatoreB.getTamaGolem().element().getPietre().element();

                int potenza = equilibrio[mappaEquilibrio.get(elementoGA.getNome())][mappaEquilibrio.get(elementoGB.getNome())];
                if (potenza < 0) {

                    //secondo fa male al primo
                    if(giocatoreA.getTamaGolem().element().togliVita(Math.abs(potenza))>0)
                    {
                        stampaAttacco(giocatoreB,giocatoreA,Math.abs(potenza));
                    }



                } else if (potenza > 0) {
                    //primo fa male al secondo

                   if(giocatoreB.getTamaGolem().element().togliVita(Math.abs(potenza))>0)
                   {
                       stampaAttacco(giocatoreA,giocatoreB,Math.abs(potenza));
                   }

                } else {
                    //nessun danno
                    stampaAttaccoNullo(giocatoreA,giocatoreB);
                }

                giocatoreA.getTamaGolem().element().getPietre().add(giocatoreA.getTamaGolem().element().getPietre().element());
                giocatoreB.getTamaGolem().element().getPietre().add(giocatoreB.getTamaGolem().element().getPietre().element());
                //System.out.println("vita 1--> " + giocatoreA.getTamaGolem().element().getVita());
                //System.out.println("vita 2--> " + giocatoreB.getTamaGolem().element().getVita());
                stampaStat(giocatoreA,giocatoreB);
                giocatoreA.getTamaGolem().element().getPietre().remove();
                giocatoreB.getTamaGolem().element().getPietre().remove();
                TimeUnit.MILLISECONDS.sleep(500);
            }
            while (controlloVitaTamagolem(giocatoreA, giocatoreB));

            Giocatore morto,vivo;

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
                fineScrontro(vivo,mappaEquilibrio);
            }
            else
            {
                System.out.println(AnsiColors.RED);
                System.out.printf(Costanti.DECEDUTO,morto.getNome());

                System.out.println("Scegli le pietre del tuo prossimo tamagolem:");
                System.out.println(AnsiColors.RESET);
                InterazioneUtente.scegliPietreSingolo(morto,vivo);


            }


        }
        while(continuarePartita);


    }


    private static void stampaStat(Giocatore giocatoreA, Giocatore giocatoreB)
    {


        System.out.println(Costanti.COLORE_BASE);
        System.out.println("Giocatore--> "+giocatoreA.getNome());
        System.out.println("VITA "+giocatoreA.getTamaGolem().element().getNome()+": "+giocatoreA.getTamaGolem().element().getVita());
        System.out.println("TAMAGOLEM RIMASTI: "+giocatoreA.getTamaGolem().size());


        System.out.println("Giocatore--> "+giocatoreB.getNome());
        System.out.println("VITA: "+giocatoreB.getTamaGolem().element().getNome()+": "+giocatoreB.getTamaGolem().element().getVita());
        System.out.println("TAMAGOLEM RIMASTI: "+giocatoreB.getTamaGolem().size());
        System.out.println(AnsiColors.RESET);

    }

    public static void stampaAttacco(Giocatore giocatoreA, Giocatore giocatoreB, int potenza)
    {
        System.out.println(Costanti.COLORE_BASE);
        System.out.println("Il tamagolem "+giocatoreA.getTamaGolem().element().getNome()+" di "+giocatoreA.getNome()+" USA "+giocatoreA.getTamaGolem().element().getPietre().element().getNome()+"!");
        System.out.println("Il tamagolem "+giocatoreB.getTamaGolem().element().getNome()+" di "+giocatoreB.getNome()+" USA "+giocatoreB.getTamaGolem().element().getPietre().element().getNome()+"!");

        System.out.println(giocatoreA.getTamaGolem().element().getNome()+" infligge "+potenza+" di danno a "+giocatoreB.getTamaGolem().element().getNome());

        System.out.println(AnsiColors.RESET);
    }

    public static void stampaAttaccoNullo(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.println(Costanti.COLORE_BASE);
        System.out.println("Il tamagolem "+giocatoreA.getTamaGolem().element().getNome()+" di "+giocatoreA.getNome()+" USA "+giocatoreA.getTamaGolem().element().getPietre().element().getNome()+"!");
        System.out.println("Il tamagolem "+giocatoreB.getTamaGolem().element().getNome()+" di "+giocatoreB.getNome()+" USA "+giocatoreB.getTamaGolem().element().getPietre().element().getNome()+"!");
        System.out.println("COLPO NULLO!");
        System.out.println(AnsiColors.RESET);
    }

    private static boolean controlloVitaTamagolem(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        if(gCorrente.getTamaGolem().element().getVita()<=0 || gSuccessivo.getTamaGolem().element().getVita()<=0)
        {
            return false;
        }
        return true;
    }

    private static void fineScrontro(Giocatore vivo,HashMap<String, Integer> mappaEquilibrio)
    {
        System.out.println(AnsiColors.GREEN);
        System.out.println(Costanti.MESS_FINE_PARTITA);
        System.out.println("Il giocatore VINCENTE è: "+vivo.getNome()+"!!!");
        System.out.println("Ora ti verra mostrato l equilibrio, cio che definisce tutto il nostro mondo...\n");
        System.out.println(AnsiColors.RESET);
        Equilibrio.visualizzaEquilibrio(mappaEquilibrio);
    }







}
