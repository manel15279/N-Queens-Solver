package com.example.nqueens;
import java.util.*;

public class GeneticAlgorithm {

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
                int fitness = Util.calculateFitness(population[tournament[j]], n);
                if (fitness < minFitness) {
                    minFitness = fitness;
                    bestIndividual = population[tournament[j]];
                }
            }
            parents[i] = bestIndividual;
        }
        return parents;
    }

    public static int[][] elitistSelection(int[][] population, int elitismCount, int n) {
        // Sort the population by fitness in descending order
        Arrays.sort(population, (o1, o2) -> {
            int fitness1 = Util.calculateFitness(o1, n);
            int fitness2 = Util.calculateFitness(o2, n);
            return Integer.compare(fitness1, fitness2);
        });

        // Select the top elitismCount chromosomes
        int[][] eliteChromosomes = Arrays.copyOfRange(population, 0, elitismCount);

        // Return the elite chromosomes
        return eliteChromosomes;
    }


    // Crossover: performs one-point crossover on the selected parents to create new children chromosomes
    private static int[][] crossover(int[][] parents, int populationSize, int n) {
        int[][] children = new int[parents.length][n];
        Random random = new Random();
        for (int i = 0; i < parents.length; i += 2) {
            int crossoverPoint = random.nextInt(n);
            for (int j = 0; j < n; j++) {
                if (j < crossoverPoint) {
                    children[i][j] = parents[i][j];
                    children[i + 1][j] = parents[i + 1][j];
                } else {
                    children[i][j] = parents[i][j];
                    children[i + 1][j] = parents[i + 1][j];
                }
            }
        }
        return children;
    }

    // Mutation: mutates some of the children chromosomes by randomly changing a gene (queen position) in the chromosome
    private static int[][] mutation(int[][] children, double mutationRate, int populationSize, int n) {
        Random random = new Random();
        for (int i = 0; i < children.length; i++) {
            if (random.nextDouble() < mutationRate) {
                int mutationPoint = random.nextInt(n);
                children[i][mutationPoint] = random.nextInt(n);
            }
        }
        return children;
    }

    private static int[][] elitistReplacement(int[][] population, int[][] children, int populationSize, int n) {
        int[][] combined = new int[populationSize + children.length][n];

        // Combine the parent and child populations
        System.arraycopy(population, 0, combined, 0, populationSize);
        System.arraycopy(children, 0, combined, populationSize, children.length);

        // Sort the combined population by fitness in ascending order
        Arrays.sort(combined, Comparator.comparingInt(c -> Util.calculateFitness(c, n)));

        // Copy the elite chromosomes into the new population
        int[][] newPopulation = Arrays.copyOfRange(combined, 0, populationSize);

        return newPopulation;
    }

    public static int[][] replacePopulation(int[][] population, int[][] children, int n) {
        int populationSize = population.length;
        int childrenSize = children.length;
        int[][] newPopulation = new int[populationSize][];

        // Sort the population by fitness in descending order
        Arrays.sort(population, (o1, o2) -> {
            int fitness1 = Util.calculateFitness(o1,n);
            int fitness2 = Util.calculateFitness(o2,n);
            return Integer.compare(fitness1, fitness2);
        });

        // Calculate the elite size as a fraction of the current population
        int eliteSize = Math.max(1, populationSize / 10); // set as 10% of the population size

        // Copy the elite individuals from the current population to the new population
        for (int i = 0; i < eliteSize; i++) {
            newPopulation[i] = population[i];
        }

        // Replace the worst individuals with the children
        int childIndex = 0;
        for (int i = eliteSize; i < populationSize; i++) {
            if (childIndex < childrenSize && Util.calculateFitness(children[childIndex],n) < Util.calculateFitness(population[i], n)) {
                // Replace the ith individual with the child
                newPopulation[i] = children[childIndex++];
            } else {
                // Keep the ith individual from the original population
                newPopulation[i] = population[i];
            }
        }

        return newPopulation;
    }

    public static Result2 geneticAlgorithm(int n, int populationSize, int maxGenerations, double mutationRate, double selectionRate) {
        int[][] population = initializePopulation(populationSize, n);
        double totalSuccessRate = 0.0;
        int numSuccesses = 0; // Track the number of successful solutions

        int generation;
        int[] bestIndividual = new int[0];
        int minFitness = 0;
        for (generation = 1; generation <= maxGenerations; generation++) {
            // Evaluate fitness of the population
            int[] fitness = new int[populationSize];
            for (int i = 0; i < populationSize; i++) {
                fitness[i] = Util.calculateFitness(population[i], n);
            }

            // Check if solution has been found
            minFitness = Integer.MAX_VALUE;
            bestIndividual = new int[n];
            for (int i = 0; i < populationSize; i++) {
                if (fitness[i] < minFitness) {
                    minFitness = fitness[i];
                    bestIndividual = population[i];
                }
            }
            if (minFitness == 0) {
                numSuccesses++; // Increment the number of successes
            }

            // Select parents for the next generation
            // (Code for selection, crossover, mutation, and replacement)

            // Calculate success rate for this generation
            double successRateForGen = (double) numSuccesses / generation; // Calculate success rate up to this generation
            totalSuccessRate += successRateForGen;
        }

        // Calculate average success rate over all generations
        double averageSuccessRate = totalSuccessRate / generation;

        return new Result2(averageSuccessRate * 100, bestIndividual, generation, minFitness);
    }



    /*public static Map<String, Integer> tuneGeneticAlgorithm(int n) {
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
    }*/

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