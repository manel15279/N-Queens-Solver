package com.example.nqueens;

import java.util.Arrays;

public class Main{


    public static void main(String[] args) {

        // n est le nombre de rienes a déposer dans le n*n échiquier
        int n = 20;
        //Affichage
        
        /*//DFS
        Result resultDFS = DFS.successeursDFS(n);
        System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: "+resultDFS.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultDFS.listeSol);
        Util.printEchiq(resultDFS, n);
        
        //BFS
        Result resultBFS = BFS.successeursBFS(n);
        System.out.println("Le nbr de nodes générés avant la premiere solution avec BFS: "+resultBFS.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultBFS.listeSol);
        Util.printEchiq(resultBFS, n);*/

        //Astar1
        Result1 resultAstar1 = Astar1.successeursAstar1(n);
        //System.out.println("Le nbr de nodes générés avant la premiere solution avec Astar1: "+resultAstar1.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+resultAstar1.listeSol);
        Util.printEchiq1(resultAstar1, n);

    }
}
