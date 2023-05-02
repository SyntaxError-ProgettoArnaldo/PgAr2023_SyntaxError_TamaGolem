package root;

import UnibsLib.InputData;
import UnibsLib.Menu;

import java.util.ArrayList;

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
        do {
            System.out.println("Le pietre disponibili sono: ");

            for (int i = 0; i < Costanti.NUMERO_PIETRE; i++) {
                String[] entries = new String[Costanti.SCORTA_COMUNE.size()];
                for (int j = 0; j < Costanti.SCORTA_COMUNE.size(); j++) {
                    entries[j] = Costanti.SCORTA_COMUNE.keySet().toArray()[j] + " ( " + Costanti.SCORTA_COMUNE.get(Costanti.SCORTA_COMUNE.keySet().toArray()[j]).toString() + " )";
                }
                Menu menuPietreRimaste = new Menu("Pietre disponibili", entries);
                int scelta = menuPietreRimaste.choose() - 1;
                rimuoviPietra(entries[scelta].split(" ")[0]);
                giocatore.getTamaGolem().get(0).getPietre().add(new Elemento(entries[scelta].split(" ")[0]));

                System.out.println(giocatore);
            }
        }while (confrontaArray());
    }

    static boolean confrontaArray() {


    }

    private static void rimuoviPietra(String nome)    {
        Costanti.SCORTA_COMUNE.put(nome,Costanti.SCORTA_COMUNE.get(nome)-1);
        if(Costanti.SCORTA_COMUNE.get(nome).equals(0))     {
            Costanti.SCORTA_COMUNE.remove(nome);
        }
    }
}
