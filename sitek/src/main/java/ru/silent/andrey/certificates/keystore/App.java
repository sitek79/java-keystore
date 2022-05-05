package ru.silent.andrey.certificates.keystore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    //private Desktop desktop = Desktop.getDesktop();

    //final FileChooser fileChooser = new FileChooser();

    //final Button openButton = new Button("Open a Picture...");
    //final Button openMultipleButton = new Button("Open Pictures...");

    public App() {
        String name = Thread.currentThread().getName();
        System.out.println("App() constructor: " + name);
    }

    private static Scene scene;

    @Override
    public void init() {
        String name = Thread.currentThread().getName();
        System.out.println("init() method: " + name);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("anchor-pane-view.fxml"));
        //scene = new Scene(fxmlLoader.load(), 600, 400);
        scene = new Scene(loadFXML("start-view"), 900, 600);
        stage.setTitle("Keystore worker!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        String name = Thread.currentThread().getName();
        System.out.println("stop() method: " + name);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}