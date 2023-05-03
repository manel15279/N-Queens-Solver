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

    public void update() {
        int[] newPosition = new int[position.length];

        // Perform crossover between current position and personal best
        for (int i = 0; i < position.length; i++) {
            if (rand.nextDouble() < 0.5) {
                if(rand.nextDouble() < 0.3){
                    newPosition[i] = globalBest[i];
                }
                else {
                    newPosition[i] = position[i];
                }
            } else {
                newPosition[i] = personalBest[i];
            }
        }

        // swap
        int mutationPoint1 = rand.nextInt(position.length);
        int mutationPoint2 = rand.nextInt(position.length);
        int temp = newPosition[mutationPoint1];
        newPosition[mutationPoint1] = newPosition[mutationPoint2];
        newPosition[mutationPoint2] = temp;

        // Update position with crossover result
        position = newPosition;
        fitness = Util.calculateFitness(position, position.length);

        // Update personal and global best positions
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
