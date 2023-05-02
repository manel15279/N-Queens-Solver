package com.example.nqueens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    private Label label3;
    @FXML
    private Label solution;

    public int size;
    public String method;
    public float tempsExe;
    public Result resultDFS, resultBFS;
    public Result1 resultAstar1;
    public Result1 resultAstar2;
    public Result2  resultGA;

    int populationSize = 5; // size of the population
    int maxGenerations = 20; // maximum number of generations
    double mutationRate = 0.2; // probability of mutation
    double selectionRate = 0.8; // probability of selection

    public void createBoard(ActionEvent event) throws IOException {
        // Get the selected size
        MenuItem selectedSize = (MenuItem) event.getSource();
        size = Integer.parseInt(selectedSize.getText().split("x")[0]);
        chessBoard.getChildren().clear();
        chessBoard.setStyle("-fx-padding: 10px; -fx-margin: 5px; -fx-alignment: center; ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle square = new Rectangle(50, 50);
                square.setFill((i + j) % 2 == 0 ? Color.web("#F0F0F0") : Color.web("#000000"));
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
    // Display queens
    public void placeQueens(ActionEvent event) {
        for (int i = 0; i < size; i++) {
            Image image = new Image(getClass().getResource("la-monarchie.png").toExternalForm());
            ImageView queenImageView = new ImageView(image);
            queenImageView.setFitHeight(48);
            queenImageView.setFitWidth(47);
            queenImageView.setStyle("-fx-alignment: center;");
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
        }
        //display stats
        time.setText("Temps d'exécution : " + tempsExe + " s");
        if("A*1".equals(method)) {
            label1.setText("Nombre de noeuds générés : " + resultAstar1.nbrNodeGenAvPremSol);
            label2.setText("Nombre de noeuds développés : " + resultAstar1.nbrNodeDev);
            solution.setText("Solution : " + resultAstar1.listeSol);
        }
        if("A*2".equals(method)){
            label1.setText("Nombre de noeuds générés : " + resultAstar2.nbrNodeGenAvPremSol);
            label2.setText("Nombre de noeuds développés : " + resultAstar2.nbrNodeDev);
            solution.setText("Solution : " + resultAstar2.listeSol);
        }
        if("DFS".equals(method)){
            label1.setText("Nombre de noeuds générés : " + resultDFS.nbrNodeGenAvPremSol);
            label2.setText("Nombre de noeuds développés : " + resultDFS.nbrNodeDev);
            solution.setText("Solution : " + resultDFS.listeSol);
        }
        if("BFS".equals(method)){
            label1.setText("Nombre de noeuds générés : " + resultBFS.nbrNodeGenAvPremSol);
            label2.setText("Nombre de noeuds développés : " + resultBFS.nbrNodeDev);
            solution.setText("Solution : " + resultBFS.listeSol);
        }
        if("GA".equals(method)){
            label1.setText("Taux de succès : " + (int)(resultGA.successRate) + "%");
            label2.setText("Fitness score : " + (resultGA.fitnessScore));
            label3.setText("Solution trouvée après : " + resultGA.nbrGenerations + " générations");
            solution.setText("Solution : " + Arrays.toString(resultGA.solution));
        }
    }


}