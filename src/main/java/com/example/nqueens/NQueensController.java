package com.example.nqueens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import java.io.IOException;
import java.util.Arrays;
public class NQueensController {

    @FXML
    private GridPane chessBoard;
    @FXML
    public Label time;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label solution;

    public int size;
    public String method;
    public float tempsExe;
    public Result resultDFS, resultBFS;
    public Result1 resultAstar1;
    public Result1 resultAstar2;
    public Result2  resultGA;
    public Result2  resultPSO;

    int populationSize = 100; // size of the population
    int maxGenerations = 5000; // maximum number of generations
    double mutationRate = 0.2; // probability of mutation
    double selectionRate = 0.5; // probability of selection


    int maxIterations = 1000; // maximum number of iterations prev 230
    int swarmSize = 100; // number of particles in the swarm prev 20
    double c1 = 5.0; // cognitive parameter
    double c2 = 3.0; // social parameter prev 2.0

    public void createBoard(ActionEvent event) throws IOException {
        // Get the selected size
        MenuItem selectedSize = (MenuItem) event.getSource();
        size = Integer.parseInt(selectedSize.getText().split("x")[0]);
        chessBoard.getChildren().clear();
        chessBoard.setStyle("-fx-padding: 10px; -fx-margin: 5px; -fx-alignment: center; ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle square = new Rectangle(50, 50);
                square.setFill((i + j) % 2 == 0 ? Color.web("#ffcc9c") : Color.web("#cf8948"));
                chessBoard.add(square, j, i);
            }
        }
        chessBoard.setVisible(true);
    }

    public void searchDFS(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        long t1 = System.currentTimeMillis();
        resultDFS = DFS.successeursDFS(size);
        long t2 = System.currentTimeMillis();
        tempsExe = (float) (t2 - t1) / 1000;
    }

    public void searchBFS(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        long t3 = System.currentTimeMillis();
        resultBFS = BFS.successeursBFS(size);
        long t4 = System.currentTimeMillis();
        tempsExe = (float) (t4 - t3) / 1000;
    }

    public void searchAstar1(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        long t5 = System.currentTimeMillis();
        resultAstar1 = Astar1.successeursAstar(size);
        long t6 = System.currentTimeMillis();
        tempsExe = (float) (t6 - t5) / 1000;
    }
    public void searchAstar2(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        long t7 = System.currentTimeMillis();
        resultAstar2 = Astar2.successeursAstar(size);
        long t8 = System.currentTimeMillis();
        tempsExe = (float) (t8 - t7) / 1000;
    }
    public void searchGA(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        long t9 = System.currentTimeMillis();
        resultGA = GeneticAlgorithm.geneticAlgorithm(size, populationSize, maxGenerations, mutationRate, selectionRate);
        long t10 = System.currentTimeMillis();
        tempsExe = (float) (t10 - t9) / 1000;
    }
    public void searchPSO(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        long t11 = System.currentTimeMillis();
        resultPSO = ParticleSwarmOptimization.PSO(size, maxIterations, swarmSize, c1, c2);
        long t12 = System.currentTimeMillis();
        tempsExe = (float) (t12 - t11) / 1000;
    }
    // Display queens
    public void placeQueens(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            Image image = new Image(getClass().getResource("queen-chess.png").toExternalForm());
            ImageView queenImageView = new ImageView(image);
            queenImageView.setFitHeight(38);
            queenImageView.setFitWidth(42);
            GridPane.setHalignment(queenImageView, HPos.CENTER);
            GridPane.setValignment(queenImageView, VPos.CENTER);
            if("A*1".equals(method))
                chessBoard.add(queenImageView, i, resultAstar1.listeSol.echiq[i]);
            if("A*2".equals(method))
                chessBoard.add(queenImageView, i, resultAstar2.listeSol.echiq[i]);
            if("DFS".equals(method))
                chessBoard.add(queenImageView, i, resultDFS.listeSol.echiq[i]);
            if("BFS".equals(method))
                chessBoard.add(queenImageView, i, resultBFS.listeSol.echiq[i]);
            if("GA".equals(method))
                chessBoard.add(queenImageView, i, resultGA.solution[i]);
            if("PSO".equals(method))
                chessBoard.add(queenImageView, i, resultPSO.solution[i]);
        }
        //display stats
        time.setText("Execution Time : " + tempsExe + " s");
        if("A*1".equals(method)) {
            label1.setText("Generated Nodes : " + resultAstar1.nbrNodeGenAvPremSol);
            label2.setText("Developed Nodes : " + resultAstar1.nbrNodeDev);
            solution.setText("Solution : " + resultAstar1.listeSol);
        }
        if("A*2".equals(method)){
            label1.setText("Generated Nodes : " + resultAstar2.nbrNodeGenAvPremSol);
            label2.setText("Developed Nodes : " + resultAstar2.nbrNodeDev);
            solution.setText("Solution : " + resultAstar2.listeSol);
        }
        if("DFS".equals(method)){
            label1.setText("Generated Nodes : " + resultDFS.nbrNodeGenAvPremSol);
            label2.setText("Developed Nodes : " + resultDFS.nbrNodeDev);
            solution.setText("Solution : " + resultDFS.listeSol);
        }
        if("BFS".equals(method)){
            label1.setText("Generated Nodes : " + resultBFS.nbrNodeGenAvPremSol);
            label2.setText("Developed Nodes : " + resultBFS.nbrNodeDev);
            solution.setText("Solution : " + resultBFS.listeSol);
        }
        if("GA".equals(method)){
            label1.setText("Fitness Score : " + (resultGA.fitnessScore));
            label2.setText("Generations before solution : " + resultGA.nbrGenerations + " generations");
            solution.setText("Solution : " + Arrays.toString(resultGA.solution));
        }
        if("PSO".equals(method)){
            label1.setText("Fitness Score : " + (resultPSO.fitnessScore));
            label2.setText("Generations before solution  : " + resultPSO.nbrGenerations + " generations");
            solution.setText("Solution : " + Arrays.toString(resultPSO.solution));
        }
    }


}