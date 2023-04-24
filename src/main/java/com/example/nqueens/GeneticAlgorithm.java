package com.example.nqueens;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneticAlgorithm {

    // Fitness function: calculates the number of conflicts in the placement of the queens
    private static int calculateFitness(int[] chromosome, int N) {
        int conflicts = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // check horizontal conflict
                if (chromosome[i] == chromosome[j]) {
                    conflicts++;
                }
                // check diagonal conflict
                if (Math.abs(i - j) == Math.abs(chromosome[i] - chromosome[j])) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }

    // Initialization: generates an initial population of random solutions
    private static int[][] initializePopulation(int populationSize, int n) {
        int[][] population = new int[populationSize][n];
        Random random = new Random();
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < n; j++) {
                population[i][j] = random.nextInt(n);
            }
        }
        return population;
    }

    // Selection: selects a subset of the population (parents) for the next generation using tournament selection
    private static int[][] selection(int[][] population, int populationSize, int n, double selectionRate) {
        int tournamentSize = (int) Math.round(populationSize * selectionRate);
        int[][] parents = new int[populationSize / 2][n];
        Random random = new Random();
        for (int i = 0; i < populationSize / 2; i++) {
            // Select individuals for tournament
            int[] tournament = new int[tournamentSize];
            for (int j = 0; j < tournamentSize; j++) {
                int index = random.nextInt(populationSize);
                tournament[j] = index;
            }
            // Determine the best individual from the tournament
            int minFitness = Integer.MAX_VALUE;
            int[] bestIndividual = new int[n];
            for (int j = 0; j < tournamentSize; j++) {
                int fitness = calculateFitness(population[tournament[j]], n);
                if (fitness < minFitness) {
                    minFitness = fitness;
                    bestIndividual = population[tournament[j]];
                }
            }
            parents[i] = bestIndividual;
        }
        return parents;
    }


    // Crossover: performs one-point crossover on the selected parents to create new children chromosomes
    private static int[][] crossover(int[][] parents, int populationSize, int n) {
        int[][] children = new int[populationSize / 2][n];
        Random random = new Random();
        for (int i = 0; i < populationSize / 2; i += 2) {
            int crossoverPoint = random.nextInt(n);
            for (int j = 0; j < crossoverPoint; j++) {
                children[i][j] = parents[i][j];
                children[i + 1][j] = parents[i + 1][j];
            }
            for (int j = crossoverPoint; j < n; j++) {
                children[i][j] = parents[i + 1][j];
                children[i + 1][j] = parents[i][j];
            }
        }
        return children;
    }

    // Mutation: mutates some of the children chromosomes by randomly changing a gene (queen position) in the chromosome
    private static int[][] mutation(int[][] children, double mutationRate, int populationSize, int n) {
        Random random = new Random();
        for (int i = 0; i < populationSize / 2; i++) {
            if (random.nextDouble() < mutationRate) {
                int mutationPoint = random.nextInt(n);
                children[i][mutationPoint] = random.nextInt(n);
            }
        }
        return children;
    }

    public static int[] geneticAlgorithm(int n, int populationSize, int maxGenerations, double mutationRate, double selectionRate) {
        int[][] population = initializePopulation(populationSize, n);

        for (int generation = 1; generation <= maxGenerations; generation++) {
            // Evaluate fitness of the population
            int[] fitness = new int[populationSize];
            for (int i = 0; i < populationSize; i++) {
                fitness[i] = calculateFitness(population[i], n);
            }

            // Check if solution has been found
            int minFitness = Integer.MAX_VALUE;
            int[] bestIndividual = new int[n];
            for (int i = 0; i < populationSize; i++) {
                if (fitness[i] < minFitness) {
                    minFitness = fitness[i];
                    bestIndividual = population[i];
                }
            }
            if (minFitness == 0) {
                System.out.println("Solution found after " + generation + " generations.");
                return bestIndividual;
            }

            // Select parents for the next generation
            int[][] parents = selection(population, populationSize, n, selectionRate);

            // Create children through crossover
            int[][] children = crossover(parents, populationSize, n);

            // Mutate some of the children chromosomes
            children = mutation(children, mutationRate, populationSize, n);

            // Combine parents and children to form new population
            int[][] newPopulation = new int[populationSize][n];
            for (int i = 0; i < populationSize / 2; i++) {
                newPopulation[i] = parents[i];
                newPopulation[i + populationSize / 2] = children[i];
            }
            population = newPopulation;
        }
        return null;
    }

    public static Map<String, Integer> tuneGeneticAlgorithm(int n) {
        int[] mutationRates = {1, 5, 10, 20, 50}; // mutation rates to try
        int[] maxGenerations = {500, 1000, 5000, 10000}; // max generations to try
        int[] populationSizes = {500, 1000, 5000, 10000}; // population sizes to try
        double[] selectionRates = {0.2, 0.4, 0.6, 0.8, 1.0}; // selection rates to try

        int bestFitness = Integer.MAX_VALUE;
        Map<String, Integer> bestParams = new HashMap<>();

        for (int mutationRate : mutationRates) {
            for (int maxGeneration : maxGenerations) {
                for (int populationSize : populationSizes) {
                    for (double selectionRate : selectionRates) {
                        if (populationSize < n) {
                            continue;
                        }

                        // check if mutation rate is too high
                        if (mutationRate >= 50 && populationSize < 500) {
                            continue;
                        }
                        // run GA with current parameters
                        int[] solution = geneticAlgorithm(n, populationSize, maxGeneration, mutationRate, selectionRate);

                        // check fitness of solution
                        int fitness = calculateFitness(solution, n);

                        // update best parameters if a better solution is found
                        if (fitness < bestFitness) {
                            bestFitness = fitness;
                            bestParams.put("mutationRate", mutationRate);
                            bestParams.put("maxGenerations", maxGeneration);
                            bestParams.put("populationSize", populationSize);
                            bestParams.put("selectionRate", (int) (selectionRate * 100));
                        }
                    }
                }
            }
        }
        return bestParams;
    }

}

/*
 *
 * Here is a step-by-step explanation of how the Genetic Algorithm for the N-Queens problem works:

Initialize the population: The algorithm starts by creating a population of random solutions to the N-Queens problem.
Each solution is represented as an array of integers, where each integer represents the position of a queen on the board.

Evaluate fitness of the population: For each solution in the population, the algorithm calculates the number of conflicts between queens on the board.
 This is done by checking each pair of queens to see if they are in the same row, column, or diagonal.
 The fitness of each solution is then set to be the number of conflicts.

Select parents for the next generation: The algorithm selects pairs of parent solutions from the current population using tournament selection.
In tournament selection, a subset of the population is randomly selected, and the solution with the lowest fitness
(i.e., the one with the fewest conflicts) is chosen as a parent.

Create children through crossover: The algorithm creates children by performing crossover between pairs of parent solutions.
In crossover, the genetic material from two parent solutions is combined to form a new solution.
The algorithm uses the one-point crossover method, where a random point along the length of the chromosome is selected, and the genetic material from each parent is swapped at that point.

Mutate some of the children chromosomes: The algorithm introduces new genetic material into the population by randomly changing the position
of a queen in some of the children chromosomes. This is done by selecting a random chromosome and randomly changing the position of one
of the queens in the chromosome.

Combine parents and children to form new population: The algorithm combines the parent solutions and children solutions to form a new population
of solutions for the next generation. The population size remains constant, and the new population is formed by replacing the least fit solutions
from the current population with the children solutions.

Repeat steps 2-6 until a solution is found: The algorithm repeats steps 2-6 for a specified number of generations or
until a solution with zero conflicts (i.e., a valid solution to the N-Queens problem) is found. If a solution is found,
the algorithm terminates and prints the solution and the number of generations it took to find it.

Overall, the Genetic Algorithm works by iteratively improving the population of solutions through selection, crossover, and mutation.
The fitness function guides the search towards solutions with fewer conflicts, and the genetic operators allow new genetic material
to be introduced into the population, preventing the algorithm from getting stuck in a local optimum.

*/


/*

what is the mutation rate?

The mutation rate is a parameter in the Genetic Algorithm that determines the probability that a mutation operation will be applied to a particular
chromosome in the population. In the context of the N-Queens problem, the mutation rate determines the probability that a queen's position will be
changed in a particular solution.

In the code example I provided earlier, the mutation rate is set to a fixed value of 0.01. This means that for each chromosome in the population,
there is a 1% chance that a queen's position will be randomly changed during the mutation operation. However,
the mutation rate can be adjusted based on the problem being solved and the characteristics of the solution space.

A high mutation rate increases the diversity of the population, but it can also slow down convergence and lead to premature convergence.
On the other hand, a low mutation rate can prevent the algorithm from exploring new areas of the solution space, resulting in getting stuck in
a local optimum. Therefore, finding the optimal mutation rate for a given problem is an important part of tuning the Genetic Algorithm.

 */