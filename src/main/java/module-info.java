module com.example.matrixcalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires java.logging;


    opens com.example.matrixcalc to javafx.fxml;
    exports com.example.matrixcalc;
}