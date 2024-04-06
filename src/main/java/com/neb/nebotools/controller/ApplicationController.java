package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.component.HeaderController;
import com.neb.nebotools.controller.component.SidebarController;
import com.neb.nebotools.controller.enumeration.State;
import com.neb.nebotools.controller.page.product.ProductAddController;
import com.neb.nebotools.controller.page.product.ProductListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

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
    ProductAddController productAddController;
    Parent productAdd;
    VBox content;

    @FXML
    private BorderPane app;

    private void switchPane(Parent child, String Entity, State state) {
        content.getChildren().clear();
        headerController.changeHeader(Entity, state.getName());
        content.getChildren().add(child);
        VBox.setVgrow(child, Priority.ALWAYS);

    }


//    public void backPane(VBox child, ManageControllerAbstract<?> manageController, State state, String message) {
//        main.getChildren().clear();
//        navigationControl.setManageType(state.getName());
//        //		manageController
//        main.getChildren().addAll(navigation, child);
//        manageController.refresh();
//        VBox.setVgrow(child, Priority.ALWAYS);
//        createAndShowPopUp(message);
//    }
//
//    public <T> void switchPane(VBox child, EntityControllerAbstract<T> control, State state, T entity) throws Exception {
//        main.getChildren().clear();
//        navigationControl.setManageType(state.getName());
//        control.setEntity(entity);
//        control.setState(state);
//        main.getChildren().addAll(navigation, child);
//    }
//
//    public <T> void switchPane(VBox child, MoreControllerAbstract<T> control, State state, T entity) throws Exception {
//        main.getChildren().clear();
//        navigationControl.setManageType(state.getName());
//        control.setEntity(entity);
//        main.getChildren().addAll(navigation, child);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSidebar();
        setCenter();
        setNavigation();
        setProduct();
        switchPane();

        switchPane(productList, "product.entity", State.MANAGE);

    }

    private void setNavigation() {
    }

    public void switchPane() {
        content.getChildren().clear();
        content.getChildren().add(productList);
    }

    private void setProduct() {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/product/productList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/product/productAdd.fxml"));

            productList = loader1.load();
            productAdd = loader2.load();

            productListController = loader1.getController();
            productAddController = loader2.getController();


            ((ControllerListAbstract) productListController).getAdd().setOnAction(a -> {
                switchPane(productAdd, "product.entity", State.ADD);
            });

            ((ControllerAddAbstract) productAddController).getAddBtn().setOnAction(a -> {
                switchPane(productList, "product.entity", State.MANAGE);
            });

            ((ControllerAddAbstract) productAddController).getCancelBtn().setOnAction(a -> {
                switchPane(productList, "product.entity", State.MANAGE);
            });

        } catch (IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de trouver le composant: ");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setCenter() {
        VBox center = new VBox();
        center.getStyleClass().add("justify-content-center");
        app.setCenter(center);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("view/component/header.fxml"));
            header = loader.load();
            headerController = loader.getController();

            VBox containHeader = new VBox();
            containHeader.getStyleClass().add("container-xxl");
            containHeader.getChildren().add(header);
            ((VBox) app.getCenter()).getChildren().add(containHeader);
        } catch (IOException | IllegalStateException e) {
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

        ((VBox) app.getCenter()).getChildren().add(scroll);

        VBox.setVgrow(content, Priority.ALWAYS);
    }

    private void setSidebar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("view/component/sidebar.fxml"));
            sidebar = loader.load();
            sidebarController = loader.getController();
            app.setLeft(sidebar);
        } catch (IOException | IllegalStateException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de trouver la sideBar: " + e.getStackTrace()[2]);
        }


    }
}
