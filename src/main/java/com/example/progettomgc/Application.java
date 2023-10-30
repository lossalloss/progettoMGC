package com.example.progettomgc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view.fxml"));
        Parent root = loader.load();
        Controller controller = new Controller();
        loader.setController(controller);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Negozio di Elettronica");
    }

    public static void main (String[]args){
            launch(args);
        }

}
