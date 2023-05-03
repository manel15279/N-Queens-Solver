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

        int populationSize = 500; // size of the population
        int maxGenerations = 5000; // maximum number of generations
        double mutationRate = 0.05; // probability of mutation
        double selectionRate = 0.5; // probability of selection


        int maxIterations = 1000; // maximum number of iterations prev 230
        int swarmSize = 125; // number of particles in the swarm prev 20
        /*double c1 = 2.0; // cognitive parameter
        double c2 = 3.0; // social parameter prev 2.0
        double w = 1.7; // inertia weight prev 0.7*/

        // n est le nombre de rienes a déposer dans le n*n échiquier
        for(int n = 100; n < 101; n++) {
            float moyDFS = 0;
            float moyBFS = 0;
            float moyAstar1 = 0;
            float moyAstar2 = 0;
            float moyGA = 0;
            float moyPSO = 0;
            //Affichage
            /*//DFS
            for(int i = 0; i < 20; i++) {
                long t1 = System.currentTimeMillis();
                resultDFS = DFS.successeursDFS(n);
                long t2 = System.currentTimeMillis();
                tempsExe = (float) (t2 - t1) / 1000;
                moyDFS += tempsExe;
            }
            System.out.println("Temps d'exécution : " + moyDFS / 20 + " s");
            System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: " + resultDFS.nbrNodeGenAvPremSol);
            System.out.println("Le nombre de noeuds developpés : " + resultDFS.nbrNodeDev);
            System.out.println("La liste des solutions : " + resultDFS.listeSol);
            //Util.printEchiq(resultDFS, n);
            bufferDFS.write(+n+ "&" + moyDFS / 20 + "&" + resultDFS.nbrNodeGenAvPremSol + "&" + resultDFS.nbrNodeDev + "&" + resultDFS.listeSol + "\n");

            //BFS
            for(int i = 0; i < 20; i++) {
                long t7 = System.currentTimeMillis();
                resultBFS = BFS.successeursBFS(n);
                long t8 = System.currentTimeMillis();
                tempsExe3 = (float) (t8 - t7) / 1000;
                moyBFS += tempsExe3;
            }
            System.out.println("Temps d'exécution : " + moyBFS / 20 + " s");
            System.out.println("Le nbr de nodes générés avant la premiere solution avec BFS: " + resultBFS.nbrNodeGenAvPremSol);
            System.out.println("Le nombre de noeuds developpés : " + resultBFS.nbrNodeDev);
            System.out.println("La liste des solutions : " + resultBFS.listeSol);
            //Util.printEchiq(resultBFS, n);
            //bufferBFS.write(+n+ "&" + moyBFS / 20 + "&" + resultBFS.nbrNodeGenAvPremSol + "&" + resultBFS.nbrNodeDev + "&" + resultBFS.listeSol + "\n");

            //Astar1
            for(int i = 0; i < 20; i++) {
                long t3 = System.currentTimeMillis();
                resultAstar1 = Astar1.successeursAstar(n);
                long t4 = System.currentTimeMillis();
                tempsExe1 = (float) (t4 - t3) / 1000;
                moyAstar1 += tempsExe1;
            }
            System.out.println("Temps d'exécution : " + moyAstar1 / 20 + " s");
            System.out.println("Le nbr de nodes générés avant la premiere solution avec Astar1: " + resultAstar1.nbrNodeGenAvPremSol);
            System.out.println("Le nombre de noeuds developpés : " + resultAstar1.nbrNodeDev);
            System.out.println("La liste des solutions : " + resultAstar1.listeSol);
            //Util.printEchiq1(resultAstar1, n);
            bufferAstar1.write(+n+ "&" + moyAstar1 / 20 + "&" + resultAstar1.nbrNodeGenAvPremSol + "&" + resultAstar1.nbrNodeDev + "&" + resultAstar1.listeSol + "\n");

            //Astar2
            for(int i = 0; i < 20; i++) {
                long t5 = System.currentTimeMillis();
                resultAstar2 = Astar2.successeursAstar(n);
                long t6 = System.currentTimeMillis();
                tempsExe2 = (float) (t6 - t5) / 1000;
                moyAstar2 += tempsExe2;
            }
            System.out.println("Temps d'exécution : " + moyAstar2 / 20 + " s");
            System.out.println("Le nbr de nodes générés avant la premiere solution avec Astar2: " + resultAstar2.nbrNodeGenAvPremSol);
            System.out.println("Le nombre de noeuds developpés : " + resultAstar2.nbrNodeDev);
            System.out.println("La liste des solutions : " + resultAstar2.listeSol);
            //Util.printEchiq1(resultAstar2, n);
            bufferAstar2.write(+n+ "&" + moyAstar2 / 20 + "&" + resultAstar2.nbrNodeGenAvPremSol + "&" + resultAstar2.nbrNodeDev + "&" + resultAstar2.listeSol + "\n");


            //GA
            for(int i = 0; i < 1; i++) {
                long t9 = System.currentTimeMillis();
                resultGA = GeneticAlgorithm.geneticAlgorithm(n, populationSize, maxGenerations, mutationRate, selectionRate);
                long t10 = System.currentTimeMillis();
                tempsExe4 = (float) (t10 - t9) / 1000;
                moyGA += tempsExe4;
            }
            System.out.println("Temps d'exécution : " + moyGA / 1 + " s");
            //System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: " + resultDFS.nbrNodeGenAvPremSol);
            //System.out.println("Le nombre de noeuds developpés : " + resultDFS.nbrNodeDev);
            System.out.println(resultGA.toString());
            Util.printBoard(resultGA.solution);
            //bufferDFS.write(+n+ "&" + moyDFS / 20 + "&" + resultDFS.nbrNodeGenAvPremSol + "&" + resultDFS.nbrNodeDev + "&" + resultDFS.listeSol + "\n");
            */

            //PSO
            for(int i = 0; i < 1; i++) {
                long t11 = System.currentTimeMillis();
                resultPSO = ParticleSwarmOptimization.PSO(n, maxIterations, swarmSize);
                long t12 = System.currentTimeMillis();
                tempsExe5 = (float) (t12 - t11) / 1000;
                moyPSO += tempsExe5;
            }
            System.out.println("Temps d'exécution : " + moyPSO / 1 + " s");
            //System.out.println("Le nbr de nodes générés avant la premiere solution avec DFS: " + resultDFS.nbrNodeGenAvPremSol);
            //System.out.println("Le nombre de noeuds developpés : " + resultDFS.nbrNodeDev);
            System.out.println(resultPSO.toString());
            Util.printBoard(resultPSO.solution);
            //bufferDFS.write(+n+ "&" + moyDFS / 20 + "&" + resultDFS.nbrNodeGenAvPremSol + "&" + resultDFS.nbrNodeDev + "&" + resultDFS.listeSol + "\n");


        }
/*
        bufferDFS.close();
        bufferBFS.close();
        bufferAstar1.close();
        bufferAstar2.close();*/
    }
}
