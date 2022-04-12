module com.example.firststepsintoadulthood2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.firststepsintoadulthood2 to javafx.fxml;
    exports com.example.firststepsintoadulthood2;
}