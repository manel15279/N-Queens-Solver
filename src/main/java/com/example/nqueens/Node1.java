package com.example.nqueens;

import java.util.ArrayList;
import java.util.Arrays;

public class Node1 {
    int[] echiq;
    int cost, heuristic;

    public Node1(int[] echiq, int cost, int heuristic) {
        this.echiq = echiq;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public String toString() {
        return Arrays.toString(echiq);
    }
}
