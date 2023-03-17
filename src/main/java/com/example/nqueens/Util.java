package com.example.nqueens;

import java.util.ArrayList;
import java.util.Arrays;

public class Util {
    // methode qui verifier si il existe des conflits ou non pour placer une riene dans une ligne
    public static boolean evaluation(ArrayList<Integer> echiq,int taille) {
        int diffL, diffC;

        // cas du riene numero 0, il n ya pas des rienes dans lechiquer donc retourner vrai
        if(taille == 0) return true;

        // faire un loop pour chaque riene déja placé
        for(int i = 0; i < taille; i++) {
            for(int j = i + 1; j < taille; j++) {
                // calculer la difference sur les colonnes
                diffC = Math.abs(echiq.get(i) - echiq.get(j));
                // calculer la difference sur les lignes
                diffL = j - i;
                if(diffC == 0 || Math.abs(diffL) == Math.abs(diffC))
                    return false;
            }
        }
        return true;
    }
    
    //verifie si conflit dans les cols seulement
    public static boolean verifC(ArrayList<Integer> echiq,int taille, int index) {
    	if(taille == 0) return true;   	
    	for(int i = 0; i < taille; i++) {
    		if(echiq.get(i) == index) return false;
    	}
    	return true;
    }
    
	// methode qui verifier si il existe des conflits ou non pour placer une riene dans une ligne 
	public static boolean check (ArrayList<Integer> echiq,int taille, int col) {
		// cas du riene numero 0, il n ya pas des rienes dans lechiquer donc retourner vrai
		if(taille == 0) return true;
		int i,diffl,diffc;
		// faire un loop pour chaque riene d�ja plac�
		for(i=0;i<taille;++i) {
			// calculer la difference sur les lignes
			diffc=col-echiq.get(i);
			// calculer la difference sur les colonne
			diffl=taille-i;
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
    
}
