package com.neb.nebotools.controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import lombok.Getter;

@Getter
public class SidebarController {

    @FXML
    private Accordion accordion;

    @FXML
    private Button customerAdd;

    @FXML
    private Button customerList;

    @FXML
    private Button employeeAdd;

    @FXML
    private Button employeeList;

    @FXML
    private Button invoiceAdd;

    @FXML
    private Button invoiceList;

    @FXML
    private Button orderList;

    @FXML
    private Button productAdd;

    @FXML
    private Button productCategory;

    @FXML
    private Button productList;

    @FXML
    private Button supplierAdd;

    @FXML
    private Button supplierList;

    @FXML
    private Button account;

    @FXML
    private TitledPane log;


    @FXML
    private TitledPane administration;


    @FXML
    private TitledPane employee;


    @FXML
    private TitledPane setting;


    public Button customerAdd(){
        return customerAdd;
    }

    public Button customerList(){
        return customerList;
    }

    public Button employeeAdd(){
        return employeeAdd;
    }

    public Button employeeList(){
        return employeeList;
    }

    public Button productAdd(){
        return productAdd;
    }

    public Button productCategory(){
        return productCategory;
    }

    public Button productList(){
        return productList;
    }

    public Button invoiceAdd(){
        return invoiceAdd;
    }

    public Button invoiceList(){
        return invoiceList;
    }

    public Button orderList(){
        return orderList;
    }

    public Button supplierAdd(){
        return supplierAdd;
    }

    public Button supplierList(){
        return supplierList;
    }

    public Button account() {
        return account;
    }
    public TitledPane log() {
        return log;
    }
}
