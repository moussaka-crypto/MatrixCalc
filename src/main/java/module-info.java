module com.example.matrixcalc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.matrixcalc to javafx.fxml;
    exports com.example.matrixcalc;
}