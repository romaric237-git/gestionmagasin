package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.component.SidebarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    SidebarController sidebarController;
    Parent sidebar;


    Parent header;
    @FXML
    private BorderPane app;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSidebar();
        setCenter();
    }

    private void setCenter() {

    }

    private void setSidebar() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("view/component/sidebar.fxml"));
            sidebar = loader.load();
            sidebarController = loader.getController();
            app.setLeft(sidebar);
        }catch (IOException | IllegalStateException e){
            System.err.println("Une erreur s'est manifesté. Impossible de trouver la sideBar: " + e.getStackTrace()[2]);
        }


    }
}
