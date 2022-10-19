package ru.silent.andrey.javakeytool;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ru.silent.andrey.javakeytool.App;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label welcomeText;

    @FXML
    private Label sysInfo;

    @FXML
    private Label workingDir;

    @FXML
    protected void switchToAppController() throws IOException {
        App.setRoot("certificate_worker_GUI");
        System.out.println("Switched to the App");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        String osName = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");
        sysInfo.setText("Ver. JavaFX: " + javafxVersion + ", Running on Java ver.: " + javaVersion + ", OS: " + osName);
        workingDir.setText("Working Dir: " + userDir);
        System.out.println(sysInfo);
        //

        //

        /*@FXML
        private Button btn;

        @FXML
        public EventHandler onActionHandler = new EventHandler<>() {
            btn.setText("You've clicked!");
        }*/
    }
}
