package com.example.nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {

    // methode qui verifier si il existe des conflits ou non pour placer une riene dans une ligne
    public static boolean evaluation(ArrayList<Integer> echiq, int col) {
        int diffL, diffC;

        // cas du riene numero 0, il n ya pas des rienes dans lechiquer donc retourner vrai
        if(echiq.size() == 0) return true;

        // faire un loop pour chaque riene déja placé
        for(int i = 0; i < echiq.size(); i++) {
            // calculer la difference sur les lignes
            diffC = col - echiq.get(i);
            // calculer la difference sur les colonne
            diffL = echiq.size() - i;
            // si meme ligne/diagonales  alors conflit retourner faux
            if(diffC == 0 || Math.abs(diffL) == Math.abs(diffC))
                return false;
        }
        // pas de conflit
        return true;
    }


    // methode pour générer les successeurs par DFS
    public static Result successeursDFS(int n) {
        Result result = new Result();
        Node node = new Node(new ArrayList<>());
        Stack<Node> ouvert = new Stack<Node>();
        ouvert.push(node);
        result.nbrNodeGenAvPremSol = 1;

        while(!ouvert.empty() && result.listeSol == null) {
            node = ouvert.pop();
            if(node.echiq.size() == n) {
                result.listeSol = node;
            }
            else{
                ArrayList<Integer> temp;
                Node nodetemp;
                // pour chaque ligne de la colonne essayer de placer la riene numero etat.size()
                for(int i = 0; i < n; i++){
                    // si on peut placer une riene en evitant des conflits
                    if(evaluation(node.echiq, i)){
                        // alors créer un nouveau état en déposant cette riene puis empiler l'état dans ouvert
                        node.echiq.add(i);
                        ouvert.push(new Node(new ArrayList<>(node.echiq)));
                        if(result.listeSol == null) result.nbrNodeGenAvPremSol++;
                        //on enleve la reine qu'on viens d'ajouter pour pouvoir explorer d'autres chemins
                        node.echiq.remove(node.echiq.size() - 1);
                    }
                }
            }
        }
        return result;
    }
}
// fonction d'evaluation optimal!! == calcul de nbr reines en attaques, on l'appel dans le main , utiliser la mm structure de données qu(on a modéliser