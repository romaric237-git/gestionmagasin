module com.neb.nebotools {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.neb.nebotools to javafx.fxml;
    exports com.neb.nebotools;
    exports com.neb.nebotools.controller to javafx.fxml;
    opens com.neb.nebotools.controller to javafx.fxml;
    exports com.neb.nebotools.controller.component to javafx.fxml;
    opens com.neb.nebotools.controller.component to javafx.fxml;
}