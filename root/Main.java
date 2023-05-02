package root;

public class Main
{
    public static final Giocatore giocatore1 = new Giocatore();
    public static final Giocatore giocatore2 = new Giocatore();



    public static void main(String[] args)
    {
        InterfacciaUtente.creaSquadra();

        Battaglia.inizio();

        Equilibrio.creaEquilibrio();
    }
}
