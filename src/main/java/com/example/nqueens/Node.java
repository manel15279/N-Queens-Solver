package com.example.nqueens;

import java.util.ArrayList;

public class Node {
    ArrayList<Integer> echiq;

    public Node(ArrayList<Integer> echiq) {
        this.echiq = echiq;
    }

    @Override
    public String toString() {
        return "echiq=" + echiq ;
    }
}