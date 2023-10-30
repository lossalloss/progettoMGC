module com.example.progettomgc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.jena.arq;
    requires org.apache.jena.core;
    requires java.net.http;

    opens com.example.progettomgc to javafx.fxml;
    exports com.example.progettomgc;
}