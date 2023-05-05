package root;

public class Main
{
    public static final Giocatore giocatore1 = new Giocatore(Costanti.COLORE_GIOCATORE_A);
    public static final Giocatore giocatore2 = new Giocatore(Costanti.COLORE_GIOCATORE_B);

    static boolean test = true;

    public static void main(String[] args) throws InterruptedException {

            boolean continuare;
            do {
                if(test)    {
                    faiTest();
                }
                else {
                    InterazioneUtente.creaSquadra();  //creazione della squadra
                }
                Battaglia.inizio();  //battaglia
                continuare=InterazioneUtente.continua();
            }while(continuare);
            System.out.println(Costanti.FINE);

    }

    private static void faiTest() {
        giocatore1.setNome("Giocatore1");
        giocatore2.setNome("Giocatore2");

        giocatore1.getTamaGolem().add(new TamaGolem("G1Tama1"));
        giocatore1.getTamaGolem().add(new TamaGolem("G1Tama2"));
        giocatore1.getTamaGolem().add(new TamaGolem("G1Tama3"));

        giocatore2.getTamaGolem().add(new TamaGolem("G2Tama1"));
        giocatore2.getTamaGolem().add(new TamaGolem("G2Tama2"));
        giocatore2.getTamaGolem().add(new TamaGolem("G2Tama3"));

        giocatore1.getTamaGolem().element().getPietre().add(new Elemento("FUOCO"));
        giocatore1.getTamaGolem().element().getPietre().add(new Elemento("TERRA"));
        giocatore1.getTamaGolem().element().getPietre().add(new Elemento("ACQUA"));

        giocatore2.getTamaGolem().element().getPietre().add(new Elemento("BUIO"));
        giocatore2.getTamaGolem().element().getPietre().add(new Elemento("ACQUA"));
        giocatore2.getTamaGolem().element().getPietre().add(new Elemento("VELENO"));

        /*Costanti.inizializzaScortaComune();

        Costanti.SCORTA_COMUNE.put("FUOCO",2);
        Costanti.SCORTA_COMUNE.replace("TERRA",2);
        Costanti.SCORTA_COMUNE.replace("ACQUA",2);
        Costanti.SCORTA_COMUNE.replace("BUIO",2);
        Costanti.SCORTA_COMUNE.replace("ACQUA",1);
        Costanti.SCORTA_COMUNE.replace("VELENO",2);*/

    }
}
