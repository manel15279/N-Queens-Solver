package com.example.nqueens;

import java.util.ArrayList;

public class Node {
    ArrayList<Integer> echiq;
    int r;

    public Node(ArrayList<Integer> echiq, int r) {
        this.echiq = echiq;
        this.r = r;
    }

    @Override
    public String toString() {
        return "echiq=" + echiq ;
    }
}