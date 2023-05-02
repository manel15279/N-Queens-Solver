package com.example.nqueens;

import java.util.Random;

class Particle {

    Random rand = new Random();
    int[] position;
    int[] velocity;
    int[] globalBest;
    int[] personalBest;
    int fitness;

    public Particle(int n) {
        position = new int[n];
        velocity = new int[n];
        globalBest = new int[n];
        personalBest = new int[n];

        for (int i = 0; i < n; i++) {
            position[i] = rand.nextInt(n);
            velocity[i] = rand.nextInt(n) - n/2;
            globalBest[i] = position[i];
            personalBest[i] = position[i];
        }

        fitness = Util.calculateFitness(position, position.length);
    }

    public void update(double c1, double c2, double w) {
        for (int i = 0; i < position.length; i++) {
            velocity[i] = (int)(w*velocity[i] + c1*rand.nextDouble()*(personalBest[i]-position[i]) + c2*rand.nextDouble()*(globalBest[i]-position[i]));
            position[i] += velocity[i];
            while (position[i] < 0) position[i] += position.length;
            while (position[i] >= position.length) position[i] -= position.length;
        }
        fitness = Util.calculateFitness(position, position.length);
        updatePersonalBest();
        updateGlobalBest();
    }

    public void updatePersonalBest() {
        // Update the personalBest
        if(fitness < Util.calculateFitness(personalBest, personalBest.length)) {
            for (int i = 0; i < position.length; i++) {
                personalBest[i] = position[i];
            }
        }
    }

    public void updateGlobalBest() {
        // Update the globalBest
        if (Util.calculateFitness(personalBest, personalBest.length) < Util.calculateFitness(globalBest, globalBest.length)) {
            for (int i = 0; i < position.length; i++) {
                globalBest[i] = personalBest[i];
            }
        }
    }



}
