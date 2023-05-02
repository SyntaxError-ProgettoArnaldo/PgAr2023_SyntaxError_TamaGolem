package root;

import java.util.HashMap;

public class Costanti
{
    public static HashMap<String, Integer> SCORTA_COMUNE = new HashMap<>();
    public static final int NUMERO_ELEMENTI = 5;
    public static final int NUMERO_PIETRE = 3;
    public static final int NUMERO_TAMAGOLEM = 2;

    public static final int NUMERO_SCORTA_COMUNE = 12;

    public static final int VITA_MASSIMA = 100;

    public static void inizializzaScortaComune()
    {
        SCORTA_COMUNE.put("pietra 1",3);
        SCORTA_COMUNE.put("pietra 2",3);
        SCORTA_COMUNE.put("pietra 3",3);
    }


}
