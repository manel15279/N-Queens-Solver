package com.example.nqueens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public Result resultDFS;

    public void createBoard(ActionEvent event) throws IOException {
        // Get the selected size
        MenuItem selectedSize = (MenuItem) event.getSource();
        size = Integer.parseInt(selectedSize.getText().split("x")[0]);
        chessBoard.getChildren().clear();
        chessBoard.setStyle("-fx-padding: 10px; -fx-margin: 5px; -fx-alignment: center; ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle square = new Rectangle(50, 50);
                square.setFill((i + j) % 2 == 0 ? Color.web("#fffacd") : Color.web("#a52a2a"));
                chessBoard.add(square, j, i);
            }
        }
        chessBoard.setVisible(true);
    }

    public void searchDFS(ActionEvent event){
        resultDFS = DFS.successeursDFS(size);
    }

    // Display queens
    public void placeQueens(ActionEvent event) {

        for (int i = 0; i < size; i++) {
            Image image = new Image(getClass().getResource("chess-piece.png").toExternalForm());
            ImageView queenImageView = new ImageView(image);
            queenImageView.setFitHeight(45);
            queenImageView.setFitWidth(45);
            chessBoard.add(queenImageView, i, resultDFS.listeSol.echiq.get(i));
        }
    }

    /*public Result searchBFS(){

    }

    public Result searchDFS(){

    }


    public Result searchDFS(){

    }*/
}
