package com.example.nqueens;

import java.util.Arrays;

public class Main{


    public static void main(String[] args) {

        // n est le nombre de rienes a déposer dans le n*n échiquier
        int n = 8;
        //Affichage
        
        //DFS
        long t1 = System.currentTimeMillis();
        Result resultDFS = DFS.successeursDFS(n);
        long t2 = System.currentTimeMillis();
        float tempsExe = (float) (t2 - t1) / 1000;
        System.out.println("Temps d'exécution : " + tempsExe + " s");
        System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: "+resultDFS.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultDFS.listeSol);
        Util.printEchiq(resultDFS, n);
        
       /* //BFS
        Result resultBFS = BFS.successeursBFS(n);
        System.out.println("Le nbr de nodes générés avant la premiere solution avec BFS: "+resultBFS.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultBFS.listeSol);
        Util.printEchiq(resultBFS, n);*/

        //Astar1
        long t3 = System.currentTimeMillis();
        Result1 resultAstar1 = Astar1.successeursAstar1(n);
        long t4 = System.currentTimeMillis();
        float tempsExe1 = (float) (t4 - t3) / 1000;
        System.out.println("Temps d'exécution : " + tempsExe1 + " s");
        System.out.println("Le nbr de nodes générés avant la premiere solution avec Astar1: "+resultAstar1.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultAstar1.listeSol);
        Util.printEchiq1(resultAstar1, n);

        //Astar2
        long t5 = System.currentTimeMillis();
        Result1 resultAstar2 = Astar2.successeursAstar2(n);
        long t6 = System.currentTimeMillis();
        float tempsExe2 = (float) (t6 - t5) / 1000;
        System.out.println("Temps d'exécution : " + tempsExe2 + " s");
        System.out.println("Le nbr de nodes générés avant la premiere solution avec Astar1: "+resultAstar2.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultAstar2.listeSol);
        Util.printEchiq1(resultAstar2, n);

    }
}
