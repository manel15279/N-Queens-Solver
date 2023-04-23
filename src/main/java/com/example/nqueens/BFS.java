package com.example.nqueens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BFS {



    // methode pour générer les successeurs par DFS
    public static Result successeursBFS(int n) {
        Result result = new Result();
        Node node = new Node(new int[n]);
        LinkedList<Node> ouvert = new LinkedList<Node>();
        ouvert.addFirst(node);
        result.nbrNodeGenAvPremSol = 1;
        result.nbrNodeDev = 0;

        while(!ouvert.isEmpty()) {
            node = ouvert.pop();
            result.nbrNodeGenAvPremSol++;
            if(node.level == n) {
                if(Util.evaluation(node.echiq, n)) {
                    result.listeSol = node;
                    return result;
                }
            }
            else{
                result.nbrNodeDev++;
                // pour chaque ligne de la colonne essayer de placer la riene numero etat.size()
                for(int i = 0; i < n; i++){
                    // alors créer un nouveau état en déposant cette riene puis empiler l'état dans ouvert
                	if(node.level < n && Util.verifC(node,i)) {
                        int[] succEchiq = node.echiq.clone();
                        succEchiq[node.level] = i;
                		ouvert.addLast(new Node(succEchiq, node.level + 1));
                		result.nbrNodeGenAvPremSol++;
                		//on enleve la reine qu'on viens d'ajouter pour pouvoir explorer d'autres chemins
                	}
                }
            }
        }
        return result;
    }
}
// fonction d'evaluation optimal!! == calcul de nbr reines en attaques, on l'appel dans le main , utiliser la mm structure de données qu(on a modéliser
