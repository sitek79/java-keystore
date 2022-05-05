package ru.silent.andrey.certificates.keystore.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.silent.andrey.certificates.keystore.App;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void switchToAppController() throws IOException {
        App.setRoot("certificate_worker_GUI");
        System.out.println("Switched to the App");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}