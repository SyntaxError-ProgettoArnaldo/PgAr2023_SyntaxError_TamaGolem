package root;

public class Main
{
    public static final Giocatore giocatore1 = new Giocatore(Costanti.COLORE_GIOCATORE_A);
    public static final Giocatore giocatore2 = new Giocatore(Costanti.COLORE_GIOCATORE_B);

    public static void main(String[] args) throws InterruptedException {


        boolean continuare;
        do {
            InterazioneUtente.creaSquadra();  //creazione della squadra
            Battaglia.inizio();  //battaglia
            continuare=InterazioneUtente.continua();
        }while(continuare);
        System.out.println(Costanti.FINE);
    }
}
