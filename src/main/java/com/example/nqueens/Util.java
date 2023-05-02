package com.example.nqueens;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    // methode qui verifie si une solution est valide ou pas
    public static boolean evaluation(int[] echiq, int n) {
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                //checks if any queen is on the same column as the current queen
                if (echiq[i] == echiq[j]) {
                    return false;
                }
                //checks if any queen is on the same diagonal as the current queen
                if (Math.abs(i-j) == Math.abs(echiq[i]-echiq[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    //verifie s'il existe des conflits dans les cols seulement
    public static boolean verifC1(Node1 node, int index) {
        for(int i = 0; i < node.cost; i++) {
            if(node.echiq[i] == index) return false;
        }
        return true;
    }
    public static boolean verifC(Node node, int index) {
        for(int i = 0; i < node.level; i++) {
            if(node.echiq[i] == index) return false;
        }
        return true;
    }

    // methode qui verifie s'il existe des conflits ou non pour placer une riene dans une ligne
    public static boolean check (ArrayList<Integer> echiq, int col) {
        for (int i = 0; i < echiq.size(); i++) {
            //checks if any queen is on the same column as the current queen
            if (echiq.get(i) == col) {
                return false;
            }
            //checks if any queen is on the same diagonal as the current queen
            if (echiq.size() - i == Math.abs(col - echiq.get(i))) {
                return false;
            }
        }
        return true;
    }

    //computes the fitness of an individual/particle
    static int calculateFitness(int[] chromosome, int N) {
        int conflicts = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // check horizontal conflict
                if (chromosome[i] == chromosome[j]) {
                    conflicts++;
                }
                // check diagonal conflict
                if (Math.abs(i - j) == Math.abs(chromosome[i] - chromosome[j])) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    public static void printEchiq(Result result, int n){
        //remplissage échiquier selon les solutions trouvées
        //intialisation échiquier
        char[][] board = new char[n][n];
        for (int x = 0; x < n; x++) {
            Arrays.fill(board[x], '.');
        }
        for (int j = 0; j < n; j++) {
            board[j][result.listeSol.echiq[j]] = 'Q';
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

    public static void printEchiq1(Result1 result, int n){
        //remplissage échiquier selon les solutions trouvées
        //intialisation échiquier
        char[][] board = new char[n][n];
        for (int x = 0; x < n; x++) {
            Arrays.fill(board[x], '.');
        }
        for (int j = 0; j < n; j++) {
            board[j][result.listeSol.echiq[j]] = 'Q';
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

    // Print the chess board with the positions of the queens
    public static void printBoard(int[] queens) {
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}