package com.example.nqueens;

import java.util.Arrays;

public class Main{

    //focntion d'affichage de l'échiquier
    public static void printEchiq(Result result, int n){
        //remplissage échiquier selon les solutions trouvées
            //intialisation échiquier
            char[][] board = new char[n][n];
            for (int x = 0; x < n; x++) {
                Arrays.fill(board[x], '.');
            }
            for (int j = 0; j < n; j++) {
                board[j][result.listeSol.echiq.get(j)] = 'Q';
            }
            //Affichage échiquier
            System.out.println("Solution : ");
            for(int k = 0; k < board.length; k++) {
                for (int z = 0; z < board[k].length; z++) {
                    System.out.print(board[k][z] + " ");
                }
                System.out.println();
            }
            System.out.println();
    }

    public static void main(String[] args) {

        // n est le nombre de rienes a déposer dans le n*n échiquier
        int n = 8;
        Result result = DFS.successeursDFS(n);

        //Affichage
        System.out.println("Le nbr de nodes générés avant la premiere solution : "+result.nbrNodeGenAvPremSol);
        System.out.println("La liste des solutions : "+result.listeSol);

        printEchiq(result, n);
    }
}
