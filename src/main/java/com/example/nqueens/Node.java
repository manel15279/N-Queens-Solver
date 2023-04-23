package com.example.nqueens;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    int[] echiq;
    int level = 0;

    public Node(int[] echiq, int level ) {
        this.echiq = echiq;
        this.level = level;
    }
    public Node(int[] echiq) {
        this.echiq = echiq;
    }

    @Override
    public String toString() {
        return Arrays.toString(echiq);
    }
}