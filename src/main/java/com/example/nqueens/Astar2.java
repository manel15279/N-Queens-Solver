package com.example.nqueens;


//1)une premiere heuristique que on peut utiliser c'est l'heuristique du conflits minimum. on compte le nombre de conflits entre les reines
//et on chosis les rienes avec le moins de conflits. donc a chaque fois on minimise le nombre total de conflits
// donc h est cette fonction et g peut etre la profondeur dans la solution ie le numero de la riene a placer
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import java.util.*;

public class Astar2 {

    public static Result1 result;

    //methode pour calculer le nombre de conflits total pour un noeud donné
    public static int calculateEucHeuristic(ArrayList<Integer> echiq, int n) {
        int dist = 0;
        for (int i = 0; i < echiq.size(); i++) {
            for (int j = i + 1; j < echiq.size(); j++) {
                if (echiq.get(i) == echiq.get(j)) {
                    dist += 2;
                }
                if (Math.abs(i - j) == Math.abs(echiq.get(i) - echiq.get(j))) {
                    dist += 2;
                }
            }
        }
        return (int) Math.sqrt(dist);
    }


    public static Result1 successeursAstar(int n) {
        result = new Result1(new Node1(new ArrayList<Integer>(), 0, 0), 0);
        PriorityQueue<Node1> ouvert = new PriorityQueue<Node1>();
        // commencer avec un noeud inital ou echiq est vide, cout & heuristique == 0
        Node1 node = new Node1(new ArrayList<Integer>(), 0, 0);
        ouvert.add(node);
        result.nbrNodeGenAvPremSol = 1;
        result.nbrNodeDev = 0;
        HashSet<Node1> closedSet = new HashSet<>();


        while(!ouvert.isEmpty()) {
            Node1 current = ouvert.poll();
            result.nbrNodeDev++;

            // si ce noeud est une solution valide alors on le retourne comme resulat
            if (Util.evaluation(current.echiq, n)) {
                result.listeSol = current;
                return result;
            }
            else {
                closedSet.add(current);
                // on genere tous les successeur avec le minimum de conflits
                for (Node1 neighbor : generateSuccessors(current, n)) {
                    if (closedSet.contains(neighbor)) {
                        continue;
                    }
                    int tentativeG = current.cost + 1;
                    if (!ouvert.contains(neighbor) || tentativeG < neighbor.cost) {
                        neighbor.cost = tentativeG;
                        if (!ouvert.contains(neighbor)) {
                            ouvert.add(neighbor);
                            result.nbrNodeGenAvPremSol++;
                        }
                    }
                }
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
                int newHeuristic = calculateEucHeuristic(newEchiq, n);
                successors.add(new Node1(newEchiq, node.cost + 1, newHeuristic));
            }
        }
        return successors;
    }

}
