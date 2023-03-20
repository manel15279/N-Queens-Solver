package com.example.nqueens;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    // methode qui verifie si une solution est valide ou pas
    public static boolean evaluation(ArrayList<Integer> echiq, int n) {
        // cas du riene numero 0, il n ya pas des rienes dans lechiquer donc retourner vrai
        if(echiq.size() < n) return false;

        // faire un loop pour chaque riene déja placé
        for(int i = 0; i < echiq.size(); i++) {
            for(int j = i + 1; j < echiq.size(); j++) {
                if(echiq.get(i) == echiq.get(j) || (Math.abs(echiq.get(i) - echiq.get(j)) == j - i))
                    return false;
            }
        }
        return true;
    }
    
    //verifie s'il existe des conflits dans les cols seulement
    public static boolean verifC(ArrayList<Integer> echiq, int index) {
    	if(echiq.size() == 0) return true;
    	for(int i = 0; i < echiq.size(); i++) {
    		if(echiq.get(i) == index) return false;
    	}
    	return true;
    }
    
	// methode qui verifie s'il existe des conflits ou non pour placer une riene dans une ligne
	public static boolean check (ArrayList<Integer> echiq, int col) {
		// cas du riene numero 0, il n ya pas des rienes dans lechiquer donc retourner vrai
		if(echiq.size() == 0) return true;
		int i,diffl,diffc;
		// faire un loop pour chaque riene d�ja plac�
		for(i = 0; i < echiq.size(); i++) {
			// calculer la difference sur les lignes
			diffc = col - echiq.get(i);
			// calculer la difference sur les colonne
			diffl = echiq.size() - i;
			// si meme ligne/diagonales  alors conflit retourner faux
			if(diffc == 0 || Math.abs(diffl) == Math.abs(diffc))
				return false;
		}
		// pas de conflit
		return true;
	}
	
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

    public static void printEchiq1(Result1 result, int n){
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
}
