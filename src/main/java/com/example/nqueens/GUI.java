package com.example.nqueens;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {

    private static int boardSize = 8;
    public static void main(String[] args) {
        launch(args); //fait appel a start
    }

    //start == main du JAVAFX
    @Override
    public void start(Stage window) throws Exception {
        GridPane gridPane = new GridPane();

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                StackPane square = new StackPane();
                square.setPrefSize(50, 50);
                if ((row + col) % 2 == 0) {
                    square.setStyle("-fx-background-color: #fffacd;");
                } else {
                    square.setStyle("-fx-background-color: #a52a2a;");
                }
                gridPane.add(square, col, row);
            }
        }

        //menu to select the board size
        ComboBox<Integer> boardSizeMenu = new ComboBox<>();
        boardSizeMenu.getItems().addAll(8, 10, 12, 14, 16);
        boardSizeMenu.setPromptText("Seléctionnez la taille de l'échiquier :");
        boardSizeMenu.setLayoutX(500);
        boardSizeMenu.setLayoutY(100);
        boardSizeMenu.setOnAction(e -> {
            boardSize = boardSizeMenu.getValue();
            try {
                start(new Stage());
                window.close();
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });


        // menu to select the method to use
        ComboBox<String> methodMenu = new ComboBox<>();
        methodMenu.getItems().addAll("DFS", "BFS", "A*");
        methodMenu.setPromptText("Seléctionnez la méthode de résolution :");

        StackPane layout = new StackPane();
        layout.getChildren().add(gridPane);
        layout.getChildren().add(methodMenu);
        layout.getChildren().add(boardSizeMenu);

        Scene scene = new Scene(layout, 800, 600);
        window.setScene(scene);
        window.setTitle("N-Reines");
        window.show();
    }
}
