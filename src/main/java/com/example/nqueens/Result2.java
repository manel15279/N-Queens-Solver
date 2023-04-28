package com.example.nqueens;

import java.util.Arrays;

public class Result2 {

    double successRate;
    double improvementRate;
    int[] solution;
    int nbrGenerations;

    public Result2(double successRate, int[] solution, int nbrGenerations, double improvementRate) {
        this.successRate = successRate;
        this.solution = solution;
        this.nbrGenerations = nbrGenerations;
        this.improvementRate = improvementRate;
    }

    @Override
    public String toString() {
        return "Result{" +
                "successRate=" + successRate + "%, improvementRate=" + improvementRate +"%, solution=" + Arrays.toString(solution) + ", nbrGenerations=" + nbrGenerations +
                '}';
    }
}
