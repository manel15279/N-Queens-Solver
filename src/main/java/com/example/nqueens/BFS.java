package com.example.nqueens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BFS {



    // methode pour générer les successeurs par DFS
    public static Result successeursBFS(int n) {
        Result result = new Result();
        Node node = new Node(new ArrayList<>());
        LinkedList<Node> ouvert = new LinkedList<Node>();
        ouvert.addFirst(node);
        result.nbrNodeGenAvPremSol = 1;

        while(!ouvert.isEmpty() && result.listeSol == null) {
            node = ouvert.pop();
            if(node.echiq.size() == n) {
                if(Util.evaluation(node.echiq, n)) {
                    result.listeSol = node;
                }
            }
            else{
                // pour chaque ligne de la colonne essayer de placer la riene numero etat.size()
                for(int i = 0; i < n; i++){
                    // alors créer un nouveau état en déposant cette riene puis empiler l'état dans ouvert
                	if(Util.verifC(node.echiq,i)) {
                		node.echiq.add(i);
                		ouvert.addLast(new Node(new ArrayList<>(node.echiq)));
                		result.nbrNodeGenAvPremSol++;
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
