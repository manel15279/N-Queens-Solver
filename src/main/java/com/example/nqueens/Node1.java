package com.example.nqueens;

import java.util.ArrayList;

public class Node1 implements Comparable<Node1> {
    ArrayList<Integer> echiq;
    int cost, heuristic;

    public Node1(ArrayList<Integer> echiq, int cost, int heuristic) {
        this.echiq = echiq;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    // compareTo permet de comparer deux noeud selon leurs fonction f. elle nous permet de trier ouvert selon f.
    public int compareTo(Node1 n) {
        return Integer.compare(cost+heuristic, n.cost+n.heuristic);
    }

    @Override
    public String toString() {
        return "echiq=" + echiq ;
    }
    

}
