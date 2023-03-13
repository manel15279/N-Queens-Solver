module com.example.nqueens {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nqueens to javafx.fxml;
    exports com.example.nqueens;
}