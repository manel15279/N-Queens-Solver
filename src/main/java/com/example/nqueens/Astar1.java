package com.example.nqueens;


//1)une premiere heuristique que on peut utiliser c'est l'heuristique du conflits minimum. on compte le nombre de conflits entre les reines 
//et on chosis les rienes avec le moins de conflits. donc a chaque fois on minimise le nombre total de conflits
// donc h est cette fonction et g peut etre la profondeur dans la solution ie le numero de la riene a placer
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import java.util.*;

public class Astar1 {

    //methode pour calculer le nombre de conflits total pour un noeud donné
    public static int calculateHeuristic(ArrayList<Integer> echiq, int n) {
        int nbConflit = 0;
        for (int i = 0; i < echiq.size(); i++) {
            for (int j = i + 1; j < echiq.size(); j++) {
                int diffL = j - i;
                int diffC = Math.abs(echiq.get(i) - echiq.get(j));
                if (diffL == diffC || diffC == 0) {
                    nbConflit++;
                }
            }
        }
        // Divide the number of conflicts by 2 because each conflict is counted twice
        return nbConflit;
    }

    public static Result1 successeursAstar1(int n) {
        Result1 result = new Result1(new Node1(new ArrayList<Integer>(), 0, 0), 0);
        PriorityQueue<Node1> ouvert = new PriorityQueue<Node1>();
        // commencer avec un noeud inital ou echiq est vide, cout & heuristique == 0
        Node1 node = new Node1(new ArrayList<Integer>(), 0, 0);
        boolean solutionFound = false;
        ouvert.add(node);

        while(!solutionFound) {
            Node1 current = ouvert.poll();

            // si ce noeud est une solution valide alors on le retourne comme resulat
            if (Util.evaluation(current.echiq, n)) {
                solutionFound = true;
                result.listeSol = current;
            }
            else {
                // on genere tous les successeur avec le minimum de conflits
                List<Node1> successors = generateSuccessors(current, n);

                // on ajoute la liste des successeurs dans ouvert
                ouvert.addAll(successors);
            }
        }
        // si aucun resultat n'est trouvé alors retourner null
        return result;
    }

    public static List<Node1> generateSuccessors(Node1 node, int n) {
        List<Node1> successors = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(Util.check(node.echiq, i)) {
                ArrayList<Integer> newEchiq = new ArrayList<>(node.echiq);
                newEchiq.add(i);
                int newHeuristic = calculateHeuristic(newEchiq, n);
                successors.add(new Node1(newEchiq, node.cost, newHeuristic));
            }
        }
        return successors;
    }

}
