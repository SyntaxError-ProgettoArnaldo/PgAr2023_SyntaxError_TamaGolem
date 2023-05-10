package root;
import UnibsLib.PrettyStrings;
import UnibsLib.RandomDraws;

import java.util.HashMap;


public class Equilibrio
{
    private static int[][] mat;
    /**
     * Crea equilibrio che verrà utilizzato per lo scontro
     * @return matrice dell'equilibrio
     */
    public static int [][] creaEquilibrio()
    {
        mat= new int[Costanti.NUMERO_ELEMENTI][Costanti.NUMERO_ELEMENTI];
        int somma;

        for (int i = 0; i < Costanti.NUMERO_ELEMENTI; i++)
        {
            int conta=0;
            do
            {
                conta++;
                somma=0;
                for (int j = 0; j < i+1; j++)
                {
                    somma+=mat[i][j];
                }
                for (int j = 1+i; j < Costanti.NUMERO_ELEMENTI; j++)
                {
                    do
                    {
                        mat[i][j] = RandomDraws.drawInteger(-Costanti.VITA_MASSIMA,Costanti.VITA_MASSIMA);
                    }
                    while (mat[i][j]==0);
                    somma+=mat[i][j];
                    mat[j][i]=-mat[i][j];
                }
            }
            while (somma!=0 && conta<200);
            if(conta==200)
            {
                i=-1;
            }
        }
        return mat;
    }

    /**
     * Stampa a video la matrice che rappresenta l'equilibrio
     */
    public static void visualizzaEquilibrio(HashMap<String, Integer> mappaEquilibrio)
    {
        System.out.println(Costanti.SEP+"\n");

        int larghezza = 10;
        String[] nomeElementi = mappaEquilibrio.keySet().toArray(new String[mappaEquilibrio.size()]);
        System.out.print(PrettyStrings.center("",larghezza));
        for (String nomeElemento : nomeElementi)
        {
            System.out.print(PrettyStrings.center(String.valueOf(nomeElemento), larghezza));
        }

        System.out.println("\n");

        for (int i = 0; i < Costanti.NUMERO_ELEMENTI; i++)
        {
            System.out.print(PrettyStrings.center(String.valueOf(nomeElementi[i]),larghezza));
            for (int j = 0; j < Costanti.NUMERO_ELEMENTI; j++)
            {
                System.out.print(PrettyStrings.center(String.valueOf(mat[i][j]),larghezza));
            }
            System.out.println("\n");
        }
        System.out.println(Costanti.SEP);
    }

}