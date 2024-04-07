package com.neb.nebotools.utils;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class Utils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("english");
    private static Stage stage;
    private static String link;

    public static void switchToEnglish() {
        bundle = ResourceBundle.getBundle("english");
        refresh();
        Controller.changeLanguage();
    }

    public static void switchToFrench() {
        bundle = ResourceBundle.getBundle("french");
        refresh();
        Controller.changeLanguage();
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static void openApp(Stage stage_, String link_) {
        stage = stage_;
        link = link_;
        refresh();
    }

    public static void refresh() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelloApplication.class.getResource(link));
            fxmlLoader.setResources(Utils.getBundle());
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("light.css");
            stage.setTitle("Hello!");
            stage.hide();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setX(0);
            stage.setY(0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
