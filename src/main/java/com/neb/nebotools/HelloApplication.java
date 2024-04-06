package com.neb.nebotools;

import com.neb.nebotools.sql.DatabaseInitializer;
import com.neb.nebotools.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {

        DatabaseInitializer.destroy();
        DatabaseInitializer.init();


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/application.fxml"));
        fxmlLoader.setResources(Utils.getBundle());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("light.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}