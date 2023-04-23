package com.example.nqueens;
import java.util.Random;

public class GeneticAlgorithm {
    private static int N = 8; // size of the chessboard
    private static int POPULATION_SIZE = 100; // size of the population
    private static int MAX_GENERATIONS = 1000; // maximum number of generations
    private static double MUTATION_RATE = 0.01; // probability of mutation

    // Fitness function: calculates the number of conflicts in the placement of the queens
    private static int calculateFitness(int[] chromosome) {
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
    private static int[][] selection(int[][] population) {
        int[][] parents = new int[POPULATION_SIZE / 2][N];
        Random random = new Random();
        for (int i = 0; i < POPULATION_SIZE / 2; i++) {
            int index1 = random.nextInt(POPULATION_SIZE);
            int index2 = random.nextInt(POPULATION_SIZE);
            if (calculateFitness(population[index1]) < calculateFitness(population[index2])) {
                parents[i] = population[index1];
            } else {
                parents[i] = population[index2];
            }
        }
        return parents;
    }

    // Crossover: performs one-point crossover on the selected parents to create new offspring chromosomes
    private static int[][] crossover(int[][] parents) {
        int[][] offspring = new int[POPULATION_SIZE / 2][N];
        Random random = new Random();
        for (int i = 0; i < POPULATION_SIZE / 2; i += 2) {
            int crossoverPoint = random.nextInt(N);
            for (int j = 0; j < crossoverPoint; j++) {
                offspring[i][j] = parents[i][j];
                offspring[i + 1][j] = parents[i + 1][j];
            }
            for (int j = crossoverPoint; j < N; j++) {
                offspring[i][j] = parents[i + 1][j];
                offspring[i + 1][j] = parents[i][j];
            }
        }
        return offspring;
    }

    // Mutation: mutates some of the offspring chromosomes by randomly changing a gene (queen position) in the chromosome
    private static int[][] mutation(int[][] offspring) {
        Random random = new Random();
        for (int i = 0; i < POPULATION_SIZE / 2; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                int mutationPoint = random.nextInt(N);
                offspring[i][mutationPoint] = random.nextInt(N);
            }
        }
        return offspring;
    }

    public static void main(String[] args) {
        int[][] population = initializePopulation(POPULATION_SIZE, N);

        for (int generation = 1; generation <= MAX_GENERATIONS; generation++) {
            // Evaluate fitness of the population
            int[] fitness = new int[POPULATION_SIZE];
            for (int i = 0; i < POPULATION_SIZE; i++) {
                fitness[i] = calculateFitness(population[i]);
            }

            // Check if solution has been found
            int minFitness = Integer.MAX_VALUE;
            int[] bestIndividual = null;
            for (int i = 0; i < POPULATION_SIZE; i++) {
                if (fitness[i] < minFitness) {
                    minFitness = fitness[i];
                    bestIndividual = population[i];
                }
            }
            if (minFitness == 0) {
                System.out.println("Solution found after " + generation + " generations.");
                printBoard(bestIndividual);
                break;
            }

            // Select parents for the next generation
            int[][] parents = selection(population);

            // Create offspring through crossover
            int[][] offspring = crossover(parents);

            // Mutate some of the offspring chromosomes
            offspring = mutation(offspring);

            // Combine parents and offspring to form new population
            int[][] newPopulation = new int[POPULATION_SIZE][N];
            for (int i = 0; i < POPULATION_SIZE / 2; i++) {
                newPopulation[i] = parents[i];
                newPopulation[i + POPULATION_SIZE / 2] = offspring[i];
            }
            population = newPopulation;
        }

    }

    // Print the chess board with the positions of the queens
    private static void printBoard(int[] queens) {
        int n = queens.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
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

Create offspring through crossover: The algorithm creates offspring by performing crossover between pairs of parent solutions.
In crossover, the genetic material from two parent solutions is combined to form a new solution.
The algorithm uses the one-point crossover method, where a random point along the length of the chromosome is selected, and the genetic material from each parent is swapped at that point.

Mutate some of the offspring chromosomes: The algorithm introduces new genetic material into the population by randomly changing the position
of a queen in some of the offspring chromosomes. This is done by selecting a random chromosome and randomly changing the position of one
of the queens in the chromosome.

Combine parents and offspring to form new population: The algorithm combines the parent solutions and offspring solutions to form a new population
of solutions for the next generation. The population size remains constant, and the new population is formed by replacing the least fit solutions
from the current population with the offspring solutions.

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