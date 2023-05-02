package com.example.nqueens;

import java.util.Arrays;

public class Result2 {

    double successRate;
    int fitnessScore;
    int[] solution;
    int nbrGenerations;

    public Result2(double successRate, int[] solution, int nbrGenerations, double improvementRate) {
        this.successRate = successRate;
        this.solution = solution;
        this.nbrGenerations = nbrGenerations;
        this.fitnessScore = fitnessScore;
    }

    public Result2(int[] solution, int nbrGenerations) {
        this.solution = solution;
        this.nbrGenerations = nbrGenerations;
    }

    @Override
    public String toString() {
        return "Result{" +
                "successRate=" + successRate + "%, fitnessScore=" + fitnessScore +"%, solution=" + Arrays.toString(solution) + ", nbrGenerations=" + nbrGenerations +
                '}';
    }
}
