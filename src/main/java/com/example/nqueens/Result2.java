package com.example.nqueens;

import java.util.Arrays;

public class Result2 {

    double successRate;
    int[] solution;
    int nbrGenerations;

    public Result2(double successRate, int[] solution, int nbrGenerations) {
        this.successRate = successRate;
        this.solution = solution;
        this.nbrGenerations = nbrGenerations;
    }

    @Override
    public String toString() {
        return "Result{" +
                "successRate=" + successRate +
                ", solution=" + Arrays.toString(solution) +
                ", nbrGenerations=" + nbrGenerations +
                '}';
    }
}
