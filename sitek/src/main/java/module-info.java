module ru.silent.andrey.certificates.keystore {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.silent.andrey.certificates.keystore to javafx.fxml;
    exports ru.silent.andrey.certificates.keystore;
}