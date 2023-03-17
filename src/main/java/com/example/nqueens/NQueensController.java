package com.example.nqueens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class NQueensController {

    @FXML
    private GridPane chessBoard;

    public void createBoard(ActionEvent event) throws IOException {
        // Get the selected size
        MenuItem selectedSize = (MenuItem) event.getSource();
        int size = Integer.parseInt(selectedSize.getText().split("x")[0]);
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

}
