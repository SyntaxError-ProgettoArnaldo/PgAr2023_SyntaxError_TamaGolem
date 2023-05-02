package root;

import UnibsLib.InputData;
import UnibsLib.Menu;

import java.sql.SQLOutput;
import java.util.ArrayList;

public interface InterfacciaUtente
{
    public static void creaSquadra()
    {
        System.out.println("Benvenuto");
        Main.giocatore1.setNome(InputData.readNonEmptyString("Inserisci giocatore 1:\n"));
        ArrayList<TamaGolem> listaTamaGolem = new ArrayList<>();
        for (int i = 0; i < Costanti.NUMERO_TAMAGOLEM; i++)
        {
            TamaGolem t = new TamaGolem();
            t.setNome(InputData.readNonEmptyString("Inserisci il nome del TamaGolem numero" + (i+1) +":\n"));
            listaTamaGolem.add(t);
        }

        Main.giocatore1.setTamaGolem(listaTamaGolem);

        Main.giocatore2.setNome(InputData.readNonEmptyString("Inserisci giocatore 2:\n"));
        listaTamaGolem.clear();
        for (int i = 0; i < Costanti.NUMERO_TAMAGOLEM; i++)
        {
            TamaGolem t = new TamaGolem();
            t.setNome(InputData.readNonEmptyString("Inserisci il nome del TamaGolem numero" + (i+1) +":\n"));
            listaTamaGolem.add(t);
        }

        Main.giocatore2.setTamaGolem(listaTamaGolem);

        System.out.println(Main.giocatore2.toString());
    }

    static void scegliPietre(Giocatore g)
    {
        System.out.println("Le pietre disponibili sono: ");
        Costanti.inizializzaScortaComune();

        String[] entries = new String[Costanti.SCORTA_COMUNE.size()];
        for (int i = 0; i <Costanti.SCORTA_COMUNE.size() ; i++) {
            entries[i] = String.valueOf(Costanti.SCORTA_COMUNE.get(i));
            System.out.println(entries[i]);
        }


        Menu menuPietreRimaste = new Menu("Pietre disponibili",  entries);



        for (int i = 0; i < Costanti.NUMERO_PIETRE; i++) {
            menuPietreRimaste.choose();
        }

    }
}
