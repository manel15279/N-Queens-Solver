package com.example.nqueens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;

public class Main{


    public static void main(String[] args) throws IOException {

        FileWriter writerDFS = new FileWriter("C:/Users/Asus Zenbook Flip/OneDrive/Bureau/NQueens_DFS.txt");
        FileWriter writerBFS = new FileWriter("C:/Users/Asus Zenbook Flip/OneDrive/Bureau/NQueens_BFS.txt");
        FileWriter writerAstar1 = new FileWriter("C:/Users/Asus Zenbook Flip/OneDrive/Bureau/NQueens_Astar1.txt");
        FileWriter writerAstar2 = new FileWriter("C:/Users/Asus Zenbook Flip/OneDrive/Bureau/NQueens_Astar2.txt");
        FileWriter writerGA = new FileWriter("C:/Users/Asus Zenbook Flip/OneDrive/Bureau/NQueens_GA.txt");

        BufferedWriter bufferDFS = new BufferedWriter(writerDFS);
        BufferedWriter bufferBFS = new BufferedWriter(writerBFS);
        BufferedWriter bufferAstar1 = new BufferedWriter(writerAstar1);
        BufferedWriter bufferAstar2 = new BufferedWriter(writerAstar2);
        BufferedWriter bufferGA = new BufferedWriter(writerGA);

        Result resultDFS = null;
        Result resultBFS = null;
        Result1 resultAstar1 = null;
        Result1 resultAstar2 = null;
        Result2 resultGA = null;
        Result2 resultPSO = null;

        float tempsExe;
        float tempsExe1;
        float tempsExe2;
        float tempsExe3;
        float tempsExe4;
        float tempsExe5;

        //int populationSize = 500; // size of the population
        int maxGenerations = 1000; // maximum number of generations
        double mutationRate = 0.5; // probability of mutation
        double selectionRate = 0.2; // probability of selection


        //int maxIterations = 1000; // maximum number of iterations prev 230
        int swarmSize = 900; // number of particles in the swarm prev 20
        /*double c1 = 2.0; // cognitive parameter
        double c2 = 3.0; // social parameter prev 2.0
        double w = 1.7; // inertia weight prev 0.7*/

        // n est le nombre de rienes a déposer dans le n*n échiquier
        for(int populationSize = 100; populationSize < 1600; populationSize += 200) {
            int moyFitness = 0;
            double moyFitnessTotale = 0;
            double moy, tot;
            for(int n = 8; n < 21; n += 4) {

                float moyDFS = 0;
                float moyBFS = 0;
                float moyAstar1 = 0;
                float moyAstar2 = 0;
                double moyGA = 0;
                double moyPSO = 0;
                //Affichage

                moyFitness = 0;
                //GA
                for (int i = 0; i < 10; i++) {
                    long t9 = System.currentTimeMillis();
                    resultGA = GeneticAlgorithm.geneticAlgorithm(n, populationSize, maxGenerations, (float)mutationRate, selectionRate);
                    long t10 = System.currentTimeMillis();
                    tempsExe4 = (float) (t10 - t9) / 1000;
                    moyGA += tempsExe4;
                    moyFitness += resultGA.fitnessScore;
                }
                moy = (double) moyFitness / 10;
                moyFitnessTotale += moy;
                /*System.out.println("Temps d'exécution : " + moyGA / 2 + " s");
                //System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: " + resultDFS.nbrNodeGenAvPremSol);
                //System.out.println("Le nombre de noeuds developpés : " + resultDFS.nbrNodeDev);
                System.out.println(resultGA.toString());
                //Util.printBoard(resultGA.solution);
                //bufferDFS.write(+n+ "&" + moyDFS / 20 + "&" + resultDFS.nbrNodeGenAvPremSol + "&" + resultDFS.nbrNodeDev + "&" + resultDFS.listeSol + "\n");
*/

            /*//PSO
            for(int i = 0; i < 5; i++) {
                long t11 = System.currentTimeMillis();
                resultPSO = ParticleSwarmOptimization.PSO(n, maxIterations, swarmSize);
                long t12 = System.currentTimeMillis();
                tempsExe5 = (float) (t12 - t11) / 1000;
                moyPSO += tempsExe5;
                moyFitness += resultPSO.fitnessScore;
            }
            moyFitness = moyFitness / 5;
            moyFitnessTotale += moyFitness;
            System.out.println("Temps d'exécution : " + moyPSO / 1 + " s");
            //System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: " + resultDFS.nbrNodeGenAvPremSol);
            //System.out.println("Le nombre de noeuds developpés : " + resultDFS.nbrNodeDev);
            System.out.println(resultPSO.toString());
            Util.printBoard(resultPSO.solution);
            //bufferDFS.write(+n+ "&" + moyDFS / 20 + "&" + resultDFS.nbrNodeGenAvPremSol + "&" + resultDFS.nbrNodeDev + "&" + resultDFS.listeSol + "\n");
*/
            }
            tot = (double) moyFitnessTotale / 4;
            moyFitnessTotale = 0;
            System.out.println(populationSize + "," + tot);

        }
/*
        bufferDFS.close();
        bufferBFS.close();
        bufferAstar1.close();
        bufferAstar2.close();*/
    }
}
