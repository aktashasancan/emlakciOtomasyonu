package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class Main extends Application { // This is the main class in which the application is executed

    private static Stage stage;

    //This is the start method of Application class which the application is executed
    //The execution time calculation process is calculated here
    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //This function changes the scene content
    public static void setScene(String fxml) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml)));
        stage.getScene().setRoot(parent);
    }

    public static void main(String[] args) {
        launch(args);
    }
}