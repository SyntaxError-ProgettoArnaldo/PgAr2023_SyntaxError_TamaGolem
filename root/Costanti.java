package root;

import UnibsLib.AnsiColors;

import java.util.HashMap;

public class Costanti
{
    public static final AnsiColors COLORE_GIOCATORE_A = AnsiColors.BLUE;
    public static final AnsiColors COLORE_GIOCATORE_B = AnsiColors.CYAN;

    public static final String BENVENUTO = "\nBenvenuto nel mondo dei Tamagolem!\n";
    public static final String MESS_INSERISCI_GIOCATORE = "Inserisci il nickname del tuo giocatore --> ";
    public static final String MESS_INSERISCI_TAMAGOLEM = "Inserisci il nome del TamaGolem numero ";
    public static HashMap<String, Integer> SCORTA_COMUNE = new HashMap<>();
    public static final int NUMERO_ELEMENTI = 5;
    public static final int NUMERO_PIETRE = 3;
    public static final int NUMERO_TAMAGOLEM = 2;

    public static final int NUMERO_SCORTA_COMUNE = 12;

    public static final int VITA_MASSIMA = 100;

    public static void inizializzaScortaComune()
    {
        SCORTA_COMUNE.put("pietra1",NUMERO_SCORTA_COMUNE/NUMERO_PIETRE);
        SCORTA_COMUNE.put("pietra2",NUMERO_SCORTA_COMUNE/NUMERO_PIETRE);
        SCORTA_COMUNE.put("pietra3",NUMERO_SCORTA_COMUNE/NUMERO_PIETRE);
    }


}
