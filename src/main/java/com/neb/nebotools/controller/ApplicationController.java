package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.component.HeaderController;
import com.neb.nebotools.controller.component.SidebarController;
import com.neb.nebotools.controller.enumeration.State;
import com.neb.nebotools.controller.page.customer.CustomerController;
import com.neb.nebotools.controller.page.customer.CustomerListController;
import com.neb.nebotools.controller.page.employee.EmployeeController;
import com.neb.nebotools.controller.page.employee.EmployeeListController;
import com.neb.nebotools.controller.page.invoice.InvoiceController;
import com.neb.nebotools.controller.page.invoice.InvoiceListController;
import com.neb.nebotools.controller.page.log.LogController;
import com.neb.nebotools.controller.page.product.ProductController;
import com.neb.nebotools.controller.page.product.ProductListController;
import com.neb.nebotools.controller.page.setting.SettingController;
import com.neb.nebotools.controller.page.supplier.SupplierController;
import com.neb.nebotools.controller.page.supplier.SupplierListController;
import com.neb.nebotools.model.*;
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
    CustomerListController customerListController;
    Parent customerList;
    CustomerController customerController;
    Parent customer;
    SupplierListController supplierListController;
    Parent supplierList;
    SupplierController supplierController;
    Parent supplier;
    InvoiceListController invoiceListController;
    Parent invoiceList;
    InvoiceController invoiceController;
    Parent invoice;
    SettingController settingController;
    Parent setting;
    LogController logCotroller;
    Parent log;
    VBox content;

    @FXML
    private BorderPane app;

    void switchPane(Parent child, String entity, State state) {
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
        setCustomer();
        setSupplier();
        setInvoice();
        setSetting();
        setLog();

        switchPane();

        employeeController.setState(State.ADD);
        switchPane(employee, "employee.entity", State.ADD);

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
            loader2.setLocation(HelloApplication.class.getResource("view/page/product/product.fxml"));

            loader1.setResources(Utils.getBundle());
            loader2.setResources(Utils.getBundle());
            productList = loader1.load();
            product = loader2.load();

            productListController = loader1.getController();
            productController = loader2.getController();


            ((ControllerListAbstract<Product>) productListController).getAdd().setOnAction(a -> {
                productController.setState(State.ADD);
                switchPane(product, "product.entity", State.ADD);
            });

            ((ControllerAbstract<Product>) productController).getCancelBtn().setOnAction(a -> {
                switchPane(productList, "product.entity", State.MANAGE);
            });
            productListController.setController(this, productController);
            productListController.setPage((VBox) product);
            productController.setController(this, productListController);

            sidebarController.productAdd().setOnAction(a -> {
                switchPane(product, "product.entity", State.ADD);
            });

            sidebarController.productList().setOnAction(a -> {
                switchPane(productList, "product.entity", State.MANAGE);
            });

        } catch (IllegalStateException | IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setEmployee() {
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


            ((ControllerListAbstract<Employee>) employeeListController).getAdd().setOnAction(a -> {
                employeeController.setState(State.ADD);
                switchPane(employee, "employee.entity", State.ADD);
            });

            ((ControllerAbstract<Employee>) employeeController).getCancelBtn().setOnAction(a -> {
                switchPane(employeeList, "employee.entity", State.MANAGE);
            });
            employeeListController.setController(this, employeeController);
            employeeListController.setPage((VBox) employee);
            employeeController.setController(this, employeeListController);

            sidebarController.employeeAdd().setOnAction(a -> {
                employeeController.setState(State.ADD);
                switchPane(employee, "employee.entity", State.ADD);
            });

            sidebarController.employeeList().setOnAction(a -> {
                switchPane(employeeList, "employee.entity", State.MANAGE);
            });
        } catch (IllegalStateException | IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setCustomer() {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/customer/customerList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/customer/customer.fxml"));

            loader1.setResources(Utils.getBundle());
            loader2.setResources(Utils.getBundle());
            customerList = loader1.load();
            customer = loader2.load();

            customerListController = loader1.getController();
            customerController = loader2.getController();


            ((ControllerListAbstract<Customer>) customerListController).getAdd().setOnAction(a -> {
                customerController.setState(State.ADD);
                switchPane(customer, "customer.entity", State.ADD);
            });

            ((ControllerAbstract<Customer>) customerController).getCancelBtn().setOnAction(a -> {
                customerController.setState(State.ADD);
                switchPane(customerList, "customer.entity", State.MANAGE);
            });
            customerListController.setController(this, customerController);
            customerListController.setPage((VBox) customer);
            customerController.setController(this, customerListController);

            sidebarController.customerAdd().setOnAction(a -> {
                customerController.setState(State.ADD);
                switchPane(customer, "customer.entity", State.ADD);
            });

            sidebarController.customerList().setOnAction(a -> {
                switchPane(customerList, "customer.entity", State.MANAGE);
            });
        } catch (IllegalStateException | IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setSupplier() {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/supplier/supplierList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/supplier/supplier.fxml"));

            loader1.setResources(Utils.getBundle());
            loader2.setResources(Utils.getBundle());
            supplierList = loader1.load();
            supplier = loader2.load();

            supplierListController = loader1.getController();
            supplierController = loader2.getController();


            ((ControllerListAbstract<Supplier>) supplierListController).getAdd().setOnAction(a -> {
                supplierController.setState(State.ADD);
                switchPane(supplier, "supplier.entity", State.ADD);
            });

            ((ControllerAbstract<Supplier>) supplierController).getCancelBtn().setOnAction(a -> {
                switchPane(supplierList, "supplier.entity", State.MANAGE);
            });
            supplierListController.setController(this, supplierController);
            supplierListController.setPage((VBox) supplier);
            supplierController.setController(this, supplierListController);

            sidebarController.supplierAdd().setOnAction(a -> {
                supplierController.setState(State.ADD);
                switchPane(supplier, "supplier.entity", State.ADD);
            });

            sidebarController.supplierList().setOnAction(a -> {
                switchPane(supplierList, "supplier.entity", State.MANAGE);
            });
        } catch (IllegalStateException | IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setInvoice() {
        try {
            FXMLLoader loader1 = new FXMLLoader();
            FXMLLoader loader2 = new FXMLLoader();

            loader1.setLocation(HelloApplication.class.getResource("view/page/invoice/invoiceList.fxml"));
            loader2.setLocation(HelloApplication.class.getResource("view/page/invoice/invoice.fxml"));

            loader1.setResources(Utils.getBundle());
            loader2.setResources(Utils.getBundle());
            invoiceList = loader1.load();
            invoice = loader2.load();

            invoiceListController = loader1.getController();
            invoiceController = loader2.getController();


            ((ControllerListAbstract<Invoice>) invoiceListController).getAdd().setOnAction(a -> {
                invoiceController.setState(State.ADD);
                switchPane(invoice, "invoice.entity", State.ADD);
            });

            ((ControllerAbstract<Invoice>) invoiceController).getCancelBtn().setOnAction(a -> {
                switchPane(invoiceList, "invoice.entity", State.MANAGE);
            });
            invoiceListController.setController(this, invoiceController);
            invoiceListController.setPage((VBox) invoice);
            invoiceController.setController(this, invoiceListController);

            sidebarController.invoiceAdd().setOnAction(a -> {
                invoiceController.setState(State.ADD);
                switchPane(invoice, "invoice.entity", State.ADD);
            });

            sidebarController.invoiceList().setOnAction(a -> {
                switchPane(invoiceList, "invoice.entity", State.MANAGE);
            });
        } catch (IllegalStateException | IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setSetting() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(HelloApplication.class.getResource("view/page/setting/setting.fxml"));
            loader.setResources(Utils.getBundle());
            setting = loader.load();
            settingController = loader.getController();

            sidebarController.account().setOnAction(a -> {
                settingController.refresh();
                switchPane(setting, "invoice.entity", State.DETAIL);
            });

        } catch (IllegalStateException | IOException e) {
            System.err.println("Une erreur s'est manifesté. Impossible de charger le composant: ");
            e.printStackTrace();
        }
    }

    private void setLog() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(HelloApplication.class.getResource("view/page/setting/setting.fxml"));
            loader.setResources(Utils.getBundle());
            log = loader.load();
            logCotroller = loader.getController();

            sidebarController.log().setOnMouseClicked(a -> {
                logCotroller.refresh();
                switchPane(log, "invoice.entity", State.DETAIL);
            });

        } catch (IllegalStateException | IOException e) {
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
