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
    requires org.kordamp.ikonli.boxicons;

    opens com.neb.nebotools to javafx.fxml,javafx.graphics;
    exports com.neb.nebotools to javafx.fxml,javafx.graphics;

    opens com.neb.nebotools.controller.enumeration to javafx.fxml,javafx.graphics;
    exports com.neb.nebotools.controller.enumeration to javafx.fxml,javafx.graphics;

    opens com.neb.nebotools.controller to javafx.fxml,javafx.graphics;
    exports com.neb.nebotools.controller;

    exports com.neb.nebotools.controller.component to javafx.fxml,javafx.graphics;
    opens com.neb.nebotools.controller.component to javafx.fxml,javafx.graphics;

    exports com.neb.nebotools.controller.page.product to javafx.fxml,javafx.graphics;
    opens com.neb.nebotools.controller.page.product to javafx.fxml,javafx.graphics;

    exports com.neb.nebotools.controller.page.employee to javafx.fxml,javafx.graphics;
    opens com.neb.nebotools.controller.page.employee to javafx.fxml,javafx.graphics;

    exports com.neb.nebotools.controller.page.customer to javafx.fxml,javafx.graphics;
    opens com.neb.nebotools.controller.page.customer to javafx.fxml,javafx.graphics;

    exports com.neb.nebotools.controller.page.supplier to javafx.fxml,javafx.graphics;
    opens com.neb.nebotools.controller.page.supplier to javafx.fxml,javafx.graphics;

    opens com.neb.nebotools.model to javafx.base;
    exports com.neb.nebotools.model to javafx.base;
}