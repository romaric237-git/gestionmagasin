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
    requires java.sql;

    opens com.neb.nebotools to javafx.fxml;
    exports com.neb.nebotools;
    opens com.neb.nebotools.controller to javafx.fxml;
    exports com.neb.nebotools.controller.component to javafx.fxml;
    opens com.neb.nebotools.controller.component to javafx.fxml;
    exports com.neb.nebotools.controller.page.product to javafx.fxml;
    exports com.neb.nebotools.controller;
}