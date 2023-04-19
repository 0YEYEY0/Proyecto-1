module com.example.proyecto1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires firmata4j;

    opens com.example.proyecto1 to javafx.fxml;
    exports com.example.proyecto1;
}