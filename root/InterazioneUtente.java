package root;

import UnibsLib.AnsiColors;
import UnibsLib.InputData;
import UnibsLib.Menu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import static root.Main.*;

public class InterazioneUtente
{

    /**
     * Input nomi dei giocatori e relativi tamagolem
     */
    static void creaSquadra()
    {
        System.out.println(Costanti.BENVENUTO);
        //primo giocatore
        String giocatore = InputData.readNonEmptyString(Costanti.COLORE_GIOCATORE_A+Costanti.MESS_INSERISCI_GIOCATORE+AnsiColors.RESET);
        giocatore1 = new Giocatore(giocatore,Costanti.COLORE_GIOCATORE_A);
        scegliTamagolem(giocatore1);
        //secondo giocatore
        giocatore = InputData.readNonEmptyString(Costanti.COLORE_GIOCATORE_B+Costanti.MESS_INSERISCI_GIOCATORE+AnsiColors.RESET);
        giocatore2 = new Giocatore(giocatore,Costanti.COLORE_GIOCATORE_B);
        scegliTamagolem(giocatore2);
    }

    /**
     * Scelta nomi tamagolem
     * @param giocatore giocatore corrente
     */
    static void scegliTamagolem(Giocatore giocatore)
    {
        Deque<TamaGolem> listaTamaGolem = new ArrayDeque<>();

        for (int i = 0; i < Costanti.NUMERO_TAMAGOLEM; i++)
        {
            String nome = InputData.readNonEmptyString(giocatore.getColore()+Costanti.MESS_INSERISCI_TAMAGOLEM + (i+1) +"--> "+AnsiColors.RESET);
            TamaGolem t = new TamaGolem(nome);
            listaTamaGolem.add(t);
        }

        giocatore.setTamaGolem(listaTamaGolem);
    }

    /**
     * Scelta delle pietre effettuata dal giocatore corrente
     * Le pietre verranno inserite nel tamagolem corrente
     */
    static void scegliPietreSingolo(Giocatore gCorrente,Giocatore gSuccessivo)
    {
        ArrayList<Elemento> listaPietre1;
        ArrayList<Elemento> listaPietre2 = new ArrayList<>();

        //riempi listaPietre 2
        for (int i = 0; i < 3 ; i++)
        {
            if (gSuccessivo.getTamaGolem().element().getPietre().isEmpty())
            {
                break;
            }
            listaPietre2.add(gSuccessivo.getTamaGolem().element().getPietre().element());
            //metodo per iterare le pietre
            gSuccessivo.getTamaGolem().element().getPietre().add(gSuccessivo.getTamaGolem().element().getPietre().element());
            gSuccessivo.getTamaGolem().element().getPietre().remove();
        }

        //copia della scorta pietre
        HashMap<String,Integer> copiaScortaComune;
        do
        {
            copiaScortaComune = new HashMap<>(Costanti.SCORTA_COMUNE);
            listaPietre1 = new ArrayList<>();
            for (int j = 0; j < Costanti.NUMERO_PIETRE; j++)
            {
                //CREAZIONE MENU
                String[] entries = new String[copiaScortaComune.size()];
                for (int k = 0; k < copiaScortaComune.size(); k++)
                {
                    //rima del menu: nome elemento + ( quantità rimasta )
                    entries[k] = copiaScortaComune.keySet().toArray()[k] + " ( " + copiaScortaComune.get(copiaScortaComune.keySet().toArray()[k]).toString() + " )";
                }
                Menu menuPietreRimaste = new Menu(Costanti.PIETRE_DISPONIBILI, entries);
                int scelta = menuPietreRimaste.choose() - 1;
                String nomePietra=entries[scelta].split(" ")[0];
                rimuoviPietra(entries[scelta].split(" ")[0],copiaScortaComune);
                listaPietre1.add(new Elemento(nomePietra));  //aggiunta pietre
            }

        }
        while (confrontaSetPietre(listaPietre1,listaPietre2));
        //conferma cambiamenti
        Costanti.SCORTA_COMUNE = copiaScortaComune;

        for (Elemento el:listaPietre1)
        {
            gCorrente.getTamaGolem().element().getPietre().add(el);
        }


    }

    /**
     * Confronta due set di pietre
     * @return true--> i due set sono uguali, false--> il secondo è ancora vuoto, oppure sono diversi
     */
    static boolean confrontaSetPietre(ArrayList<Elemento> listaPietre1, ArrayList<Elemento> listaPietre2)
    {
        if(listaPietre2.isEmpty())
        {
            return false;
        }

        for (int i = 0; i < listaPietre1.size(); i++)
        {
            if(!listaPietre1.get(i).getNome().equalsIgnoreCase(listaPietre2.get(i).getNome()))
            {
                return false;
            }
        }
        System.out.println(AnsiColors.RED +Costanti.PIETRE_UGUALI+ AnsiColors.RESET);
        return true;
    }


    /**
     * Elimina una pietra dal nome dalla scorta comune dei due giocatori
     * @param nome Nome della pietra
     * @param scortaComune Scorta dei due giocatori
     */
    private static void rimuoviPietra(String nome, HashMap<String, Integer> scortaComune)
    {
        scortaComune.put(nome,scortaComune.get(nome)-1);
        if(scortaComune.get(nome).equals(0))
        {
            scortaComune.remove(nome);
        }
    }

    /**
     * Chiede all utente se vuole continuare o meno
     * @return 0--> no esci, 1--> si continua
     */
    public static boolean continua()
    {
        int scelta = InputData.readIntegerBetween(Costanti.CONTINUARE,0,1);
        return scelta != 0;

    }
}
