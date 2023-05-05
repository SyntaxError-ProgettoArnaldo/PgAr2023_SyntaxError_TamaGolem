package root;

import UnibsLib.AnsiColors;

import java.util.HashMap;

public class Costanti
{
    //UTILITA
    public static final String SEP = "------------------------------------------------------------------------";

    //COLORI
    public static final AnsiColors COLORE_GIOCATORE_A = AnsiColors.YELLOW;
    public static final AnsiColors COLORE_GIOCATORE_B = AnsiColors.GREEN;
    public static final AnsiColors COLORE_BASE = AnsiColors.BLACK;

    //STRINGHE
    public static final String BENVENUTO = "\nBenvenuto nel mondo dei Tamagolem!\n";
    public static final String MESS_INSERISCI_GIOCATORE = "Inserisci il nickname del tuo giocatore --> ";
    public static final String MESS_INSERISCI_TAMAGOLEM = "Inserisci il nome del TamaGolem numero ";
    public static final String TURNO = "Ora è il tuo turno: ";
    public static final String INIZIO_SCONTRO = "\nCHE LO SCONTRO ABBIA INIZIO !\n";
    public static final String MESS_FINE_PARTITA = "LA PARTITA E' CONCLUSA!";
    public static final String CONTINUARE = "Inserisci 0 se vuoi uscire, 1 se vuoi riiniziare una nuova partita?";
    public static final String  FINE = "Grazie per aver combattuto nel mondo dei tamagolem!";
    public static final String DECEDUTO = "Mi dispiace %s, il tuo tamagolem è deceduto !";

    //INTERI
    public static final int NUMERO_ELEMENTI = 6;
    public static final int NUMERO_PIETRE = ((NUMERO_ELEMENTI+1)/3)+1;  //⎡(N + 1) / 3⎤ + 1.
    public static final int NUMERO_TAMAGOLEM = ((NUMERO_ELEMENTI-1)*(NUMERO_ELEMENTI-2)/(2*NUMERO_PIETRE));  ////⎡(N - 1)(N - 2) / (2 * P)⎤
    public static final int NUMERO_SCORTA_COMUNE = ((2*NUMERO_TAMAGOLEM*NUMERO_PIETRE)/NUMERO_ELEMENTI)*NUMERO_ELEMENTI;  //⎡(2 * G * P) / N⎤ * N
    public static final int VITA_MASSIMA = 100;



    //HASHMAP
    public static HashMap<String, Integer> SCORTA_COMUNE = new HashMap<>();

    public static void inizializzaScortaComune()
    {
        SCORTA_COMUNE.put("TERRA",NUMERO_SCORTA_COMUNE/NUMERO_ELEMENTI);
        SCORTA_COMUNE.put("FUOCO",NUMERO_SCORTA_COMUNE/NUMERO_ELEMENTI);
        SCORTA_COMUNE.put("GHIACCIO",NUMERO_SCORTA_COMUNE/NUMERO_ELEMENTI);
        SCORTA_COMUNE.put("ACQUA",NUMERO_SCORTA_COMUNE/NUMERO_ELEMENTI);
        SCORTA_COMUNE.put("BUIO",NUMERO_SCORTA_COMUNE/NUMERO_ELEMENTI);
        SCORTA_COMUNE.put("VELENO",NUMERO_SCORTA_COMUNE/NUMERO_ELEMENTI);
    }



}
