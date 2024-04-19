package com.neb.nebotools.controller.page.setting;

import com.neb.nebotools.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class SettingController {
    @FXML
    private DatePicker birth;

    @FXML
    private Button changePassword;

    @FXML
    private Button changePersonalInform;

    @FXML
    private Button changePin;

    @FXML
    private Label detailBirth;

    @FXML
    private Label detailFirstName;

    @FXML
    private Label detailLastName;

    @FXML
    private Label detailLogin;

    @FXML
    private Label detailMail;

    @FXML
    private Label detailName;

    @FXML
    private Label detailPhone;

    @FXML
    private Label detailRegistration;

    @FXML
    private Label detailSex;

    @FXML
    private Label detailType;

    @FXML
    private TextField digit1;

    @FXML
    private TextField digit2;

    @FXML
    private TextField digit3;

    @FXML
    private TextField digit4;

    @FXML
    private TextField digit5;

    @FXML
    private TextField digit6;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private PasswordField passwordP1;

    @FXML
    private TextField passwordT1;

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

    public void refresh(){
        try {
            Employee e = Employee.getEmployee();
            detailType.setText(e.getRole().name());
            detailName.setText(e.getLastname() + " " + e.getFirstname());
            detailRegistration.setText(e.getId());
            detailLogin.setText(e.getLogin());
            detailFirstName.setText(e.getFirstname());
            detailLastName.setText(e.getLastname());
            detailMail.setText(e.getMail());
            detailPhone.setText(e.getPhone());
            detailBirth.setText(DateTimeFormatter.ofPattern("EEEE, dd MMMM YYYY").format(e.getBirth().toLocalDate()));
            detailSex.setText(e.getSexe());



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
