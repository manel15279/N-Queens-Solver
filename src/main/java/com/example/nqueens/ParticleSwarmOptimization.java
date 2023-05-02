package com.example.nqueens;

public class ParticleSwarmOptimization {
    public static Result2 PSO(int n, int maxIterations, int swarmSize, double c1, double c2, double w) {

        // create swarm
        Particle[] swarm = new Particle[swarmSize];
        for (int i = 0; i < swarmSize; i++) {
            swarm[i] = new Particle(n);
        }

        // start PSO
        int iteration = 0;
        Particle bestParticle = null;
        while (iteration < maxIterations) {

            // update particle positions and velocities and fitnesses
            for (int i = 0; i < swarmSize; i++) {
                swarm[i].update(c1, c2, w);
            }

            // check for solution
            for (int i = 0; i < swarmSize; i++) {
                if (swarm[i].fitness == 0) {
                    return new Result2(swarm[i].position, iteration);
                }
            }

            // check for best particle
            if (bestParticle == null || bestParticle.fitness > getBestFitness(swarm)) {
                bestParticle = new Particle(n);
                bestParticle = swarm[getBestParticleIndex(swarm)];
            }

            iteration++;
        }
        return new Result2(bestParticle.position, iteration);
    }

    private static int getBestFitness(Particle[] swarm) {
        int bestFitness = Integer.MAX_VALUE;
        for (Particle particle : swarm) {
            if (particle.fitness < bestFitness) {
                bestFitness = particle.fitness;
            }
        }
        return bestFitness;
    }

    private static int getBestParticleIndex(Particle[] swarm) {
        int bestParticleIndex = 0;
        int bestFitness = Integer.MAX_VALUE;
        for (int i = 0; i < swarm.length; i++) {
            if (swarm[i].fitness < bestFitness) {
                bestFitness = swarm[i].fitness;
                bestParticleIndex = i;
            }
        }
        return bestParticleIndex;
    }
}
