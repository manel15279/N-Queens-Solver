package com.example.nqueens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;

/******************************************* EXPERIMENTATION  CLASS ***************************************************/

public class Main{

    public static void main(String[] args) throws IOException {

        /*FileWriter writerDFS = new FileWriter("NQueens_DFS.txt");
        FileWriter writerBFS = new FileWriter("NQueens_BFS.txt");
        FileWriter writerAstar1 = new FileWriter("NQueens_Astar1.txt");
        FileWriter writerAstar2 = new FileWriter("NQueens_Astar2.txt");
        FileWriter writerGA = new FileWriter("NQueens_GA.txt");

        BufferedWriter bufferDFS = new BufferedWriter(writerDFS);
        BufferedWriter bufferBFS = new BufferedWriter(writerBFS);
        BufferedWriter bufferAstar1 = new BufferedWriter(writerAstar1);
        BufferedWriter bufferAstar2 = new BufferedWriter(writerAstar2);
        BufferedWriter bufferGA = new BufferedWriter(writerGA);*/

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

        int populationSize = 500; // size of the population
        int maxGenerations = 1000; // maximum number of generations
        double mutationRate = 0.5; // probability of mutation
        double selectionRate = 0.7; // probability of selection


        int maxIterations = 1000; // maximum number of iterations prev 230
        int swarmSize = 500; // number of particles in the swarm prev 20
        double c1 = 0.4; // cognitive parameter
        double c2 = 0.9; // social parameter prev 2.0

        // n est le nombre de rienes a déposer dans le n*n échiquier
        for(int n = 14; n < 26; n += 2) {
            int moyFitness = 0;
            double moyFitnessTotale = 0;
            double moy, tot;

                float moyDFS = 0;
                float moyBFS = 0;
                float moyAstar1 = 0;
                float moyAstar2 = 0;
                double moyGA = 0;
                double moyPSO = 0;
                //Affichage

                moyFitness = 0;
                //GA
                for (int i = 0; i < 1; i++) {
                    long t9 = System.currentTimeMillis();
                    resultAstar1 = Astar2.successeursAstar(n);
                    long t10 = System.currentTimeMillis();
                    tempsExe4 = (float) (t10 - t9) / 1000;
                    moyGA += tempsExe4;
                }
                moy = (double) moyFitness;
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

            tot = (double) moyFitnessTotale ;
            moyFitnessTotale = 0;
            System.out.println(n + "," + moyGA);

        }
/*
        bufferDFS.close();
        bufferBFS.close();
        bufferAstar1.close();
        bufferAstar2.close();*/
    }
}
