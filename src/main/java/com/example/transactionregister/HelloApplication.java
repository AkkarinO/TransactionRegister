package com.example.transactionregister;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 540);
        stage.setTitle("Transactions & Contractors!");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}