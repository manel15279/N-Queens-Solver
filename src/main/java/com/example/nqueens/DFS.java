package com.example.nqueens;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {


    // methode pour générer les successeurs par DFS
    public static Result successeursDFS(int n) {
        Result result = new Result();
        Node node = new Node(new ArrayList<>(),0);
        Stack<Node> ouvert = new Stack<Node>();
        ouvert.push(node);
        result.nbrNodeGenAvPremSol = 1;

        while(!ouvert.empty() && result.listeSol == null) {
            node = ouvert.pop();
            if(node.echiq.size() == n) {
                if(Util.evaluation(node.echiq,node.r)) {
                    result.listeSol = node;
                }
            }
            else{
                // pour chaque ligne de la colonne essayer de placer la riene numero etat.size()
                for(int i = 0; i < n; i++){
                    // alors créer un nouveau état en déposant cette riene puis empiler l'état dans ouvert
                	if(Util.verifC(node.echiq,node.r,i)) {
                		node.echiq.add(i);
                		ouvert.push(new Node(new ArrayList<>(node.echiq),node.r+1));
                		result.nbrNodeGenAvPremSol++;
                		//on enleve la reine qu'on viens d'ajouter pour pouvoir explorer d'autres chemins
                		node.echiq.remove(node.r);
                	}
                }
            }
        }
        return result;
    }
}
// fonction d'evaluation optimal!! == calcul de nbr reines en attaques, on l'appel dans le main , utiliser la mm structure de données qu(on a modéliser