package root;

public class Main
{
    public static Giocatore giocatore1;
    public static Giocatore giocatore2;
    static boolean test = false;
    public static void main(String[] args)
    {
            boolean continuare;
            do
            {
                if(test)
                {
                    faiTest();
                }
                else
                {
                    InterazioneUtente.creaSquadra();  //creazione della squadra
                    Costanti.inizializzaScortaComune(); //inizializzazione della scorta comune delle pietre dei due giocatori
                }
                Battaglia.inizio();  //battaglia
                continuare=InterazioneUtente.continua();
            }
            while(continuare);
            System.out.println(Costanti.FINE);
    }

    public static void faiTest()
    {
        giocatore1 = new Giocatore("Giocatore1",Costanti.COLORE_GIOCATORE_A);
        giocatore2 = new Giocatore("Giocatore2",Costanti.COLORE_GIOCATORE_B);

        giocatore1.getTamaGolem().add(new TamaGolem("G1Tama1"));
        giocatore1.getTamaGolem().add(new TamaGolem("G1Tama2"));
        giocatore1.getTamaGolem().add(new TamaGolem("G1Tama3"));

        giocatore1.getTamaGolem().element().getPietre().add(new Elemento(Costanti.FUOCO));
        giocatore1.getTamaGolem().element().getPietre().add(new Elemento(Costanti.TERRA));
        giocatore1.getTamaGolem().element().getPietre().add(new Elemento(Costanti.ACQUA));

        giocatore2.getTamaGolem().add(new TamaGolem("G2Tama1"));
        giocatore2.getTamaGolem().add(new TamaGolem("G2Tama2"));
        giocatore2.getTamaGolem().add(new TamaGolem("G2Tama3"));

        giocatore2.getTamaGolem().element().getPietre().add(new Elemento(Costanti.BUIO));
        giocatore2.getTamaGolem().element().getPietre().add(new Elemento(Costanti.ACQUA));
        giocatore2.getTamaGolem().element().getPietre().add(new Elemento(Costanti.VELENO));

        Costanti.inizializzaScortaComune();

        Costanti.SCORTA_COMUNE.put(Costanti.FUOCO, Costanti.SCORTA_COMUNE.get(Costanti.FUOCO)-1);
        Costanti.SCORTA_COMUNE.replace(Costanti.TERRA,Costanti.SCORTA_COMUNE.get(Costanti.TERRA)-1);
        Costanti.SCORTA_COMUNE.replace(Costanti.ACQUA,Costanti.SCORTA_COMUNE.get(Costanti.ACQUA)-1);
        Costanti.SCORTA_COMUNE.replace(Costanti.BUIO,Costanti.SCORTA_COMUNE.get(Costanti.BUIO)-1);
        Costanti.SCORTA_COMUNE.replace(Costanti.ACQUA,Costanti.SCORTA_COMUNE.get(Costanti.ACQUA)-1);
        Costanti.SCORTA_COMUNE.replace(Costanti.VELENO,Costanti.SCORTA_COMUNE.get(Costanti.VELENO)-1);
    }
}
