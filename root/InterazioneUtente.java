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
    static void creaSquadra()
    {
        System.out.println(Costanti.BENVENUTO);
        //primo giocatore
        giocatore1.setNome(InputData.readNonEmptyString(Costanti.COLORE_GIOCATORE_A+Costanti.MESS_INSERISCI_GIOCATORE+AnsiColors.RESET));
        scegliTamagolem(giocatore1);
        //secondo giocatore
        giocatore2.setNome(InputData.readNonEmptyString(Costanti.COLORE_GIOCATORE_B+Costanti.MESS_INSERISCI_GIOCATORE+AnsiColors.RESET));
        scegliTamagolem(giocatore2);

    }

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

    static void scegliPietreSingolo(Giocatore gCorrente,Giocatore gSuccessivo)
    {

        ArrayList<Elemento> listaPietre1;
        ArrayList<Elemento> listaPietre2 = new ArrayList<>();

        //riempi listaPietre 2
        for (int i = 0; i < 3 ; i++) {
            if (gSuccessivo.getTamaGolem().element().getPietre().isEmpty()){
                break;
            }
            listaPietre2.add(gSuccessivo.getTamaGolem().element().getPietre().element());
            gSuccessivo.getTamaGolem().element().getPietre().add(gSuccessivo.getTamaGolem().element().getPietre().element());
            gSuccessivo.getTamaGolem().element().getPietre().remove();
        }

        HashMap<String,Integer> copiaScortaComune;
        do
        {
            copiaScortaComune = new HashMap<>(Costanti.SCORTA_COMUNE);
            listaPietre1 = new ArrayList<>();
            System.out.println("\nOra tocca a te "+gCorrente.getNome()+", devi scegliere le pietre \n");
            for (int j = 0; j < Costanti.NUMERO_PIETRE; j++)
            {
                String[] entries = new String[copiaScortaComune.size()];
                for (int k = 0; k < copiaScortaComune.size(); k++) {
                    entries[k] = copiaScortaComune.keySet().toArray()[k] + " ( " + copiaScortaComune.get(copiaScortaComune.keySet().toArray()[k]).toString() + " )";
                }
                Menu menuPietreRimaste = new Menu("Pietre disponibili", entries);
                int scelta = menuPietreRimaste.choose() - 1;
                String nomePietra=entries[scelta].split(" ")[0];
                rimuoviPietra(entries[scelta].split(" ")[0],copiaScortaComune);
                listaPietre1.add(new Elemento(nomePietra));
            }

        }while (confrontaSetPietre(listaPietre1,listaPietre2));
        Costanti.SCORTA_COMUNE = copiaScortaComune;

        for (Elemento el:listaPietre1)
        {
            gCorrente.getTamaGolem().element().getPietre().add(el);
        }


    }



    static boolean confrontaSetPietre(ArrayList<Elemento> listaPietre1, ArrayList<Elemento> listaPietre2)
    {
        if(listaPietre2.isEmpty())
        {
            return false;
        }



        for (int i = 0; i < listaPietre1.size(); i++) {
            if(!listaPietre1.get(i).getNome().equalsIgnoreCase(listaPietre2.get(i).getNome()))
            {
                return false;
            }
        }
        System.out.println("\nPIETRE UGUALI: REINSERIRE\n");
        return true;
    }



    private static void rimuoviPietra(String nome, HashMap<String, Integer> copiaScortaComune)    {
        copiaScortaComune.put(nome,copiaScortaComune.get(nome)-1);
        if(copiaScortaComune.get(nome).equals(0))     {
            copiaScortaComune.remove(nome);
        }
    }
}
