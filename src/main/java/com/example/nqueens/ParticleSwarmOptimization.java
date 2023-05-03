package com.example.nqueens;

public class ParticleSwarmOptimization {
    public static Result2 PSO(int n, int maxIterations, int swarmSize) {

        // create swarm
        Particle[] swarm = new Particle[swarmSize];
        for (int i = 0; i < swarmSize; i++) {
            swarm[i] = new Particle(n);
        }

        // start PSO
        int iteration = 0;
        Particle bestParticle = null;
        int minFitness = 0;
        while (iteration < maxIterations) {

            // Check if solution has been found
            minFitness = Integer.MAX_VALUE;
            bestParticle = new Particle(n);
            for (int i = 0; i < swarmSize; i++) {
                if (swarm[i].fitness < minFitness) {
                    minFitness = swarm[i].fitness;
                    bestParticle = swarm[i];
                }
            }
            // check for solution
            if (minFitness == 0) {
                return new Result2(bestParticle.position, iteration, minFitness);
            }

            // update particle positions and velocities and fitnesses
            for (int i = 0; i < swarmSize; i++) {
                swarm[i].update();
            }

            iteration++;
        }
        return new Result2(bestParticle.position, iteration, minFitness);
    }

}
