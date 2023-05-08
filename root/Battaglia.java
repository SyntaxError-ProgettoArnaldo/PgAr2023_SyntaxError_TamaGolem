package root;

import UnibsLib.AnsiColors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static root.Main.*;

public class Battaglia {
    public static final int TIMEOUT = 2000;   //lasso di tempo tra due "mosse"
    private static final ArrayList<Giocatore> listaGiocatori = new ArrayList<>();

    /**
     * Metodo per randomizzare il primo giocatore, da inizio allo scontro
     */
    public static void inizio()
    {
        listaGiocatori.add(giocatore1);
        listaGiocatori.add(giocatore2);
        Collections.shuffle(listaGiocatori);
        scontro();
    }

    /**
     * Crea la mappa dell equilibrio
     * @return HashMap relativo agli elementi presenti nell equilbrio
     */
    public static HashMap<String, Integer> creaMappaEquilibrio()
    {
        HashMap<String, Integer> mappaEquilibrio = new HashMap<>();
        for (int k = 0; k < Costanti.SCORTA_COMUNE.size(); k++)
        {
            mappaEquilibrio.put(String.valueOf(Costanti.SCORTA_COMUNE.keySet().toArray()[k]),k);
        }
        return mappaEquilibrio;
    }


    public static void scontro()
    {
        int[][] equilibrio = Equilibrio.creaEquilibrio();  //creazione equilibrio

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
                //pietre correnti dei due giocatori
                Elemento elementoGA = giocatoreA.getTamaGolem().element().getPietre().element();
                Elemento elementoGB = giocatoreB.getTamaGolem().element().getPietre().element();
                //potenza in base ai due elementi delle due pietre
                int potenza = equilibrio[mappaEquilibrio.get(elementoGA.getNome())][mappaEquilibrio.get(elementoGB.getNome())];
                if (potenza < 0)
                {
                    //secondo fa danno al primo
                    if(giocatoreA.getTamaGolem().element().togliVita(Math.abs(potenza))>0)
                    {
                        stampaAttacco(giocatoreB,giocatoreA,Math.abs(potenza));
                    }

                }
                else if (potenza > 0)
                {
                    //primo fa danno al secondo
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
                //eliminazione pietra coorente, si passa alla successiva
                giocatoreA.getTamaGolem().element().getPietre().remove();
                giocatoreB.getTamaGolem().element().getPietre().remove();
                try {
                    TimeUnit.MILLISECONDS.sleep(TIMEOUT);
                } catch (InterruptedException e) {
                    System.out.println(Costanti.ERRORE_SLEEP);
                }
            }

            while (controlloVitaTamagolem(giocatoreA, giocatoreB));

            Giocatore morto,vivo;
            //se uno dei due giocatori ha un tamagolem senza vita
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

    /**
     * Assegna le pietre ai due giocatori
     * @param giocatoreA
     * @param giocatoreB
     */
    private static void assegnaPietre(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.println(giocatoreA.getColore()+Costanti.TURNO+giocatoreA.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreA,giocatoreB);

        System.out.println(giocatoreB.getColore()+Costanti.TURNO+giocatoreB.getNome()+ AnsiColors.RESET);
        InterazioneUtente.scegliPietreSingolo(giocatoreB,giocatoreA);
    }


    /**
     * stampa i dati relativi ai due giocatori, come il nome, la vita e i tamagole rimasti
     */
    private static void stampaStat(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.printf(Costanti.NOME_GIOCATORE, giocatoreA.getNome());
        System.out.printf(Costanti.VITA, giocatoreA.getTamaGolem().element().getNome(),giocatoreA.getTamaGolem().element().getVita());
        System.out.printf(Costanti.TAMAGOLEM_RIMASTI, giocatoreA.getTamaGolem().size());

        System.out.printf(Costanti.NOME_GIOCATORE, giocatoreB.getNome());
        System.out.printf(Costanti.VITA, giocatoreB.getTamaGolem().element().getNome(),giocatoreB.getTamaGolem().element().getVita());
        System.out.printf(Costanti.TAMAGOLEM_RIMASTI, giocatoreB.getTamaGolem().size());
    }

    /**
     * Stampa l attacco in caso ci sia stato un danno
     * @param potenza dell attacco
     */
    public static void stampaAttacco(Giocatore giocatoreA, Giocatore giocatoreB, int potenza)
    {
        System.out.printf(Costanti.ATTACCO, giocatoreA.getTamaGolem().element().getNome(),giocatoreA.getNome(),giocatoreA.getTamaGolem().element().getPietre().element().getNome());
        System.out.printf(Costanti.ATTACCO, giocatoreB.getTamaGolem().element().getNome(),giocatoreB.getNome(),giocatoreB.getTamaGolem().element().getPietre().element().getNome());

        System.out.printf(Costanti.DANNO_INFLITTO,giocatoreA.getTamaGolem().element().getNome(),potenza, giocatoreB.getTamaGolem().element().getNome() );
    }

    /**
     * Stampa un attacco con potenza nulla
     */
    public static void stampaAttaccoNullo(Giocatore giocatoreA, Giocatore giocatoreB)
    {
        System.out.printf(Costanti.ATTACCO, giocatoreA.getTamaGolem().element().getNome(),giocatoreA.getNome(),giocatoreA.getTamaGolem().element().getPietre().element().getNome());
        System.out.printf(Costanti.ATTACCO, giocatoreB.getTamaGolem().element().getNome(),giocatoreB.getNome(),giocatoreB.getTamaGolem().element().getPietre().element().getNome());
        System.out.println(Costanti.COLPO_NULLO);
    }

    /**
     * Controlla la vita dei tamagolem correnti dei due giocatori
     * @param gCorrente
     * @param gSuccessivo
     * @return false se ce un tamagolem con vita negativa o nulla
     */
    private static boolean controlloVitaTamagolem(Giocatore gCorrente, Giocatore gSuccessivo)
    {
        if(gCorrente.getTamaGolem().element().getVita()<=0 || gSuccessivo.getTamaGolem().element().getVita()<=0)
        {
            return false;
        }
        return true;
    }

    /**
     * La fine dello scontro, si stampano i messaggi e si mostra l equilibrio
     * @param vivo
     * @param mappaEquilibrio
     */
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
