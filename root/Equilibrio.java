package root;
import UnibsLib.RandomDraws;

import java.lang.Math;
public class Equilibrio
{
    private static int[][] mat;
    /**
     * Crea equilibrio che verra utilizzato per lo scontro
     * @return
     */
    public static int [][] creaEquilibrio()   {
        mat= new int[Costanti.NUMERO_ELEMENTI][Costanti.NUMERO_ELEMENTI];
        int somma;

        for (int i = 0; i < Costanti.NUMERO_ELEMENTI; i++) {
            int conta=0;
            do {
                conta++;
                somma=0;
                for (int j = 0; j < i+1; j++) {
                    somma+=mat[i][j];
                }
                for (int j = 1+i; j < Costanti.NUMERO_ELEMENTI; j++) {
                    do {
                        mat[i][j] = (int) (RandomDraws.drawInteger(-Costanti.VITA_MASSIMA,Costanti.VITA_MASSIMA));
                    }while (mat[i][j]==0);
                    somma+=mat[i][j];
                    mat[j][i]=-mat[i][j];
                }
            } while (somma!=0 && conta<200);
            if(conta>200)   {
                i=-1;
            }
        }

        return mat;
    }

    /**
     * Stampa a video la matrice che rappresente l quilibrio
     */
    public static void visualizzaEquilibrio()
    {
        for (int i = 0; i < Costanti.NUMERO_ELEMENTI; i++) {
            for (int j = 0; j < Costanti.NUMERO_ELEMENTI; j++) {
                System.out.print(mat[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

}