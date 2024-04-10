package com.neb.nebotools.controller.page.employee;

import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class EmployeeController extends ControllerAbstract<Employee> {
    @FXML
    private Button addBtn;

    @FXML
    private TextField birth;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField login;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField passwordP;

    @FXML
    private TextField passwordT;

    @FXML
    private PasswordField passwordVP;

    @FXML
    private TextField passwordVT;

    @FXML
    private TextField phone;

    @FXML
    private ChoiceBox<?> sex;

    @FXML
    void switchP(ActionEvent event) {

    }

    @FXML
    void switchP2(ActionEvent event) {

    }

    @Override
    public void refresh() {

    }

    @Override
    protected void setField() throws Exception {

    }

    @Override
    protected void clearField() throws Exception {

    }

    @Override
    protected void disableField(boolean disable) {

    }

    @Override
    protected void buildEntity() throws Exception {

    }

    @Override
    protected void setDao() {

    }

    @Override
    protected void initValidator() {

    }

    @Override
    protected void initComponent() {

    }
}
