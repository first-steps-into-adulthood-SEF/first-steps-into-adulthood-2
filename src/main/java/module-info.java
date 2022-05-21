module com.example.firststepsintoadulthood2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    //requires commons.validator;

    opens com.example.firststepsintoadulthood2 to javafx.fxml;
    exports com.example.firststepsintoadulthood2;
    exports com.example.firststepsintoadulthood2.controllers;
    exports com.example.firststepsintoadulthood2.model to com.fasterxml.jackson.databind;
    opens com.example.firststepsintoadulthood2.controllers to javafx.fxml;
    //exports com.example.firststepsintoadulthood2.swtch;
    //opens com.example.firststepsintoadulthood2.swtch to javafx.fxml;
}