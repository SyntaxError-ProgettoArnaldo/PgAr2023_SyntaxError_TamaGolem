package root;

import UnibsLib.InputData;
import UnibsLib.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public interface InterfacciaUtente
{
     static void creaSquadra()
    {
        System.out.println("Benvenuto");
        Main.giocatore1.setNome(InputData.readNonEmptyString("Inserisci giocatore 1:\n"));
        ArrayList<TamaGolem> listaTamaGolem = new ArrayList<>();
        ArrayList<TamaGolem> listaTamaGolem2 = new ArrayList<>();
        for (int i = 0; i < Costanti.NUMERO_TAMAGOLEM; i++)
        {
            TamaGolem t = new TamaGolem();
            t.setNome(InputData.readNonEmptyString("Inserisci il nome del TamaGolem numero" + (i+1) +":\n"));
            listaTamaGolem.add(t);
        }

        Main.giocatore1.setTamaGolem(listaTamaGolem);

        Main.giocatore2.setNome(InputData.readNonEmptyString("Inserisci giocatore 2:\n"));
        for (int i = 0; i < Costanti.NUMERO_TAMAGOLEM; i++)
        {
            TamaGolem t2 = new TamaGolem();
            t2.setNome(InputData.readNonEmptyString("Inserisci il nome del TamaGolem numero" + (i+1) +":\n"));
            listaTamaGolem2.add(t2);
        }

        Main.giocatore2.setTamaGolem(listaTamaGolem2);

        System.out.println(Main.giocatore1);
        System.out.println(Main.giocatore2);
    }

    static void scegliPietre(Giocatore giocatore)
    {
        ArrayList<Elemento> listaPietre1 = new ArrayList<>();
        ArrayList<Elemento> listaPietre2 = new ArrayList<>();
        HashMap<String,Integer> copiaScortaComune = new HashMap<>(Costanti.SCORTA_COMUNE);


        do {


            System.out.println("\nOra tocca a te "+giocatore.getNome()+", devi scegliere le pietre \n");

            for (int i = 0; i < Costanti.NUMERO_PIETRE; i++) {
                String[] entries = new String[Costanti.SCORTA_COMUNE.size()];
                for (int j = 0; j < copiaScortaComune.size(); j++) {
                    entries[j] = copiaScortaComune.keySet().toArray()[j] + " ( " + copiaScortaComune.get(copiaScortaComune.keySet().toArray()[j]).toString() + " )";
                }
                Menu menuPietreRimaste = new Menu("Pietre disponibili", entries);
                int scelta = menuPietreRimaste.choose() - 1;
                System.out.println(Costanti.SCORTA_COMUNE);
                rimuoviPietra(entries[scelta].split(" ")[0],copiaScortaComune);
                giocatore.getTamaGolem().get(0).getPietre().add(new Elemento(entries[scelta].split(" ")[0]));

                System.out.println(giocatore);
            }
        }while (confrontaArray(Main.giocatore1.getTamaGolem().get(0).getPietre(),Main.giocatore2.getTamaGolem().get(0).getPietre(),giocatore) || Main.giocatore2.getTamaGolem().get(0).getPietre().size()==0);
        Costanti.SCORTA_COMUNE = new HashMap<>(copiaScortaComune);
    }

    static boolean confrontaArray(ArrayList<Elemento> array1, ArrayList<Elemento> array2,Giocatore giocatore)
    {

        for (int i = 0; i < array1.size(); i++) {
            if(!array1.get(i).getNome().equals(array2.get(i).getNome()))
            {
                return false;
            }
        }

        giocatore.getTamaGolem().get(0).setPietre(new ArrayList<>());
        return true;

    }

    private static void rimuoviPietra(String nome, HashMap<String, Integer> copiaScortaComune)    {
        copiaScortaComune.put(nome,copiaScortaComune.get(nome)-1);
        if(copiaScortaComune.get(nome).equals(0))     {
            copiaScortaComune.remove(nome);
        }
    }
}
