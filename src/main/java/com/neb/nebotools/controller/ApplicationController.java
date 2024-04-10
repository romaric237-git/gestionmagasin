package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.component.HeaderController;
import com.neb.nebotools.controller.component.SidebarController;
import com.neb.nebotools.controller.enumeration.State;
import com.neb.nebotools.controller.page.employee.EmployeeController;
import com.neb.nebotools.controller.page.employee.EmployeeListController;
import com.neb.nebotools.controller.page.product.ProductController;
import com.neb.nebotools.controller.page.product.ProductListController;
import com.neb.nebotools.utils.Utils;
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

public class ApplicationController extends Controller implements Initializable {

    SidebarController sidebarController;
    Parent sidebar;

    HeaderController headerController;
    Parent header;
    ProductListController productListController;
    Parent productList;
    ProductController productController;
    Parent product;
    EmployeeListController employeeListController;
    Parent employeeList;
    EmployeeController employeeController;
    Parent employee;
    VBox content;

    @FXML
    private BorderPane app;

    private void switchPane(Parent child, String entity, State state) {
        content.getChildren().clear();
        headerController.changeHeader(entity, state.getName());
        content.getChildren().add(child);
        VBox.setVgrow(child, Priority.ALWAYS);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSidebar();
        setCenter();
        setNavigation();
        setProduct();
        setEmployee();
        switchPane();

        switchPane(product, "product.entity", State.ADD);

    }

    private void setNavigation() {
    }

    public void switchPane() {
        content.getChildren().clear();
        content.getChildren().add(productList);
    }

    private void setProduct(){
        try {
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/product/productList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/product/product.fxml"));

            loader1.setResources(Utils.getBundle());
            loader2.setResources(Utils.getBundle());
            productList = loader1.load();
            product = loader2.load();

            productListController = loader1.getController();
            productController = loader2.getController();


            ((ControllerListAbstract) productListController).getAdd().setOnAction(a -> {
                switchPane(product, "product.entity", State.ADD);
            });

            ((ControllerAbstract) productController).getAddBtn().setOnAction(a -> {
                switchPane(productList, "product.entity", State.MANAGE);
            });

            ((ControllerAbstract) productController).getCancelBtn().setOnAction(a -> {
                switchPane(productList, "product.entity", State.MANAGE);
            });

            sidebarController.productAdd().setOnAction(a->{
                switchPane(product, "product.entity", State.ADD);
            });

            sidebarController.productList().setOnAction(a->{
                switchPane(productList, "product.entity", State.MANAGE);
            });
        } catch (IllegalStateException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setEmployee(){
        try {
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/employee/employeeList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/employee/employee.fxml"));

            loader1.setResources(Utils.getBundle());
            loader2.setResources(Utils.getBundle());
            employeeList = loader1.load();
            employee = loader2.load();

            employeeListController = loader1.getController();
            employeeController = loader2.getController();


            ((ControllerListAbstract) employeeListController).getAdd().setOnAction(a -> {
                switchPane(employee, "employee.entity", State.ADD);
            });

            ((ControllerAbstract) employeeController).getAddBtn().setOnAction(a -> {
                switchPane(employeeList, "employee.entity", State.MANAGE);
            });

            ((ControllerAbstract) employeeController).getCancelBtn().setOnAction(a -> {
                switchPane(employeeList, "employee.entity", State.MANAGE);
            });

            sidebarController.employeeAdd().setOnAction(a->{
                switchPane(employee, "employee.entity", State.ADD);
            });

            sidebarController.employeeList().setOnAction(a->{
                switchPane(employeeList, "employee.entity", State.MANAGE);
            });
        } catch (IllegalStateException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setCenter() {
        VBox center = new VBox();
        center.getStyleClass().add("justify-content-top-center");

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

    @Override
    public void refresh() {

    }
}
