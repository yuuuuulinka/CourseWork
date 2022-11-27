module com.example.homedeviceskursova {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.homedeviceskursova to javafx.fxml;
    exports com.example.homedeviceskursova;
    exports com.example.homedeviceskursova.dbconnection;
    opens com.example.homedeviceskursova.dbconnection to javafx.fxml;
}