package com.example.matrixcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try{
            Parent root = new FXMLLoader(getClass().getResource("sample.fxml")).load();

            stage.setTitle("Matrix Calculator");
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}