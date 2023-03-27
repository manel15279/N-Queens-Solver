package com.example.nqueens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NQueensApplication extends Application {

    public Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("NQueens-view.fxml"));
        primaryStage = primaryStage;
        primaryStage.setTitle("N-Reines");
        primaryStage.setScene(new Scene(root, 750, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
