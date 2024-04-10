package com.neb.nebotools.controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SidebarController {

    @FXML
    private Button employeeAdd;

    @FXML
    private Button employeeList;

    @FXML
    private Button productAdd;

    @FXML
    private Button productCategory;

    @FXML
    private Button productList;


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
}
