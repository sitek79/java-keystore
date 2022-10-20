package ru.silent;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class AppController {

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private TextField pathToKeystoreFile;

    @FXML
    protected void buttonChooseKeystoreClicked(ActionEvent event) {
        System.out.println("choose file");
        // https://stackoverflow.com/questions/25491732/how-do-i-open-the-javafx-filechooser-from-a-controller-class
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Keystore File");
        Node node = (Node) event.getSource();
        //chooser.showOpenDialog(node.getScene().getWindow());
        File file = chooser.showOpenDialog(node.getScene().getWindow());
        System.out.println(file);
        // меняем текст в TextField
        pathToKeystoreFile.setText(String.valueOf(file));
        // выбор хранилища сертификатов
        // Reading the certificate store
        //ReadingTheCertificateStore readingTheCertificateStore = new ReadingTheCertificateStore();
        //readingTheCertificateStore.loadKeyStore(String.valueOf(file));
        //
        // Loading A Keystore
            char[] keyStorePassword = "password".toCharArray();
            try(InputStream keyStoreData = new FileInputStream(file)) {
                KeyStore ks = KeyStore.getInstance("pkcs12");
                ks.load(keyStoreData, keyStorePassword);
                System.out.println("Keystore is Loaded");
                // Reading a Single Entry with password
                char[] keyPassword = "password".toCharArray();
                KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword);
                System.out.println("KeyStore Contains Alias application: " + ks.containsAlias("application"));
                System.out.println("KeyStore Type: " + ks.getType());
                System.out.println("Aliases stored in the KeyStore: " + ks.aliases().nextElement());
                System.out.println("Your KeyStore contains " + ks.size() + " entries.");
                System.out.println("KeyStore Provider: " + ks.getProvider());
            } catch (Exception e) {
                System.out.println("The storage didn't load");
            }
        // Reading all entries
        try {
        char[] keyPassword = "password".toCharArray();
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword);
        KeyStore ke = KeyStore.getInstance("pkcs12");
        System.out.println(ke.getCertificate("application"));
        //KeyStore.Entry keyEntry = ks.getEntry("application", entryPassword);
        //ks.getEntry("application", entryPassword);
            KeyStore.Entry keyEntry = (KeyStore.Entry) ke.getCertificate("application");
        System.out.println("Alias is Read");
        } catch (Exception e) {
        System.out.println("All Entries was not readable");
        }
    }

    protected void setTime() {
        //time.setText(datetime);
        // или обновим содержимое label из потока, не относящегося к FX application thread.
        // так же можно использовать javafx.concurrent.Task
        //Platform.runLater(() -> time.setText(datetime));
        //Platform.runLater(() -> runTimer());
    }

    @FXML
    private void initialize() {
        System.out.println("Initialization AppController...");
        Platform.runLater(() -> setTime());
        System.out.println("AppController Initialized!");
    }
}
