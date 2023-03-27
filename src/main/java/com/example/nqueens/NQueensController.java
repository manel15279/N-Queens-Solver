package com.example.nqueens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class NQueensController {

    @FXML
    private GridPane chessBoard;

    public int size;
    public String method;

    public Result resultDFS, resultBFS;
    public Result1 resultAstar1;

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
        resultDFS = DFS.successeursDFS(size);
    }

    public void searchBFS(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        resultBFS = BFS.successeursBFS(size);
    }

    public void searchAstar1(ActionEvent event){
        MenuItem selectedMethod = (MenuItem) event.getSource();
        method = (String)selectedMethod.getText();
        resultAstar1 = Astar1.successeursAstar1(size);
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
                chessBoard.add(queenImageView, i, resultAstar1.listeSol.echiq.get(i));
            if("A*2".equals(method))
                chessBoard.add(queenImageView, i, resultAstar1.listeSol.echiq.get(i));
            if("DFS".equals(method))
                chessBoard.add(queenImageView, i, resultDFS.listeSol.echiq.get(i));
            if("BFS".equals(method))
                chessBoard.add(queenImageView, i, resultBFS.listeSol.echiq.get(i));
        }
    }



}