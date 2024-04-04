package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.component.HeaderController;
import com.neb.nebotools.controller.component.SidebarController;
import com.neb.nebotools.controller.page.product.ProductListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

    SidebarController sidebarController;
    Parent sidebar;

    HeaderController headerController;
    Parent header;
    ProductListController productListController;
    Parent productList;
    ProductListController productAddController;
    Parent productAdd;
    VBox content;



    @FXML
    private BorderPane app;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSidebar();
        setCenter();
        setProduct();
        switchPane();
    }

    public void switchPane(){
        content.getChildren().clear();
        content.getChildren().add(productList);
    }
    private void setProduct() {
        try{
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/product/productList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/product/productAdd.fxml"));

            productList = loader1.load();
            productAdd = loader2.load();

            productListController = loader1.getController();
            productAddController = loader2.getController();

        }catch (IOException | IllegalStateException e){
            System.err.println("Une erreur s'est manifesté. Impossible de trouver le composant: " );
            e.printStackTrace();
        }
    }

    private void setCenter() {
        VBox center = new VBox();
        center.getStyleClass().add("justify-content-center");
        app.setCenter(center);

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("view/component/header.fxml"));
            header = loader.load();
            headerController = loader.getController();

            VBox containHeader = new VBox();
            containHeader.getStyleClass().add("container-xxl");
            containHeader.getChildren().add(header);
            ((VBox)app.getCenter()).getChildren().add(containHeader);
        }catch (IOException | IllegalStateException e){
            System.err.println("Une erreur s'est manifesté. Impossible de trouver la sideBar: " + e.getStackTrace()[2]);
        }

        ScrollPane scroll = new ScrollPane();
        VBox vBox = new VBox();
        scroll.setContent(vBox);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        vBox.getStyleClass().add("justify-content-center");
        //
        content = new VBox();

        content.getStyleClass().add("container-xxl");

        vBox.getChildren().add(content);

        ((VBox)app.getCenter()).getChildren().add(scroll);

        VBox.setVgrow(content, Priority.ALWAYS);
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
