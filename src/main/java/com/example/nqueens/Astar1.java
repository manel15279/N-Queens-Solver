package com.example.nqueens;


//1)une premiere heuristique que on peut utiliser c'est l'heuristique du conflits minimum. on compte le nombre de conflits entre les reines 
//et on chosis les rienes avec le moins de conflits. donc a chaque fois on minimise le nombre total de conflits
// donc h est cette fonction et g peut etre la profondeur dans la solution ie le numero de la riene a placer
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Astar1 {


    public static int countNumberOfConflicts(Node1 node, int n) {
    	int nbConflit = 0,diffC,diffL;
    	int taille = node.r+1;
        for(int j = 0; j < taille; j++) {
            for(int i = 0; i < n; i++) {
                // calculer la difference sur les colonnes
                diffC = Math.abs(i - node.echiq.get(j));
                // calculer la difference sur les lignes
                diffL = taille - j;
                if(diffC == 0 || Math.abs(diffL) == Math.abs(diffC)) {
                    nbConflit++;
                }
            }
        }
    	
    	return nbConflit;
    }
    public static int h(Node1 node, int n) {
    	int nbConflit = countNumberOfConflicts(node,n); 	
    	return nbConflit;
    }
    


    // methode pour générer les successeurs par DFS
    public static Result1 successeursAstar1(int n) {
        Result1 result = new Result1();
        Node1 node = new Node1(new ArrayList<>(),0,0);
        PriorityQueue<Node1> ouvert = new PriorityQueue<Node1>();
        ouvert.add(node);
        result.nbrNodeGenAvPremSol = 1;
        
        while(result.listeSol == null) {
            node = ouvert.poll();
            if(node.echiq.size() == n) {
                if(Util.evaluation(node.echiq,node.r)) {
                    result.listeSol = node;
                }
            }
            else{
                // pour chaque ligne de la colonne essayer de placer la riene numero etat.size()
                for(int i = 0; i < n; i++){
                    // alors créer un nouveau état en déposant cette riene puis empiler l'état dans ouvert
                	if(Util.check(node.echiq,node.r,i)) {
                		node.echiq.add(i);
                		ouvert.offer(new Node1(new ArrayList<>(node.echiq),node.r+1,h(node,n)));
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