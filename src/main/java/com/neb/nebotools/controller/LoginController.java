package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Employee;
import exception.EntityNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.neb.nebotools.validator.Validator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField mail;

    @FXML
    private PasswordField passwordP;

    @FXML
    private TextField passwordT;

    @FXML
    private CheckBox rememberMe;

    @FXML
    void signIn(ActionEvent event) throws IOException, SQLException {
        try {
            Employee.setUserConnected(DaoFactory.getEmployeeDao().login(mail.getText(),passwordP.getText()));
            if(Employee.getEmployeeConnected()!=null){
            Stage stage = ((Stage) (mail.getScene().getWindow()));
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/twoStep.fxml"));
            ResourceBundle bundle = ResourceBundle.getBundle("english");
            fxmlLoader.setResources(bundle);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("light.css");
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            }

        } catch (EntityNotFoundException e) {
            System.err.println("Connexion impossible");
        }
    }

    @FXML
    void switchP(ActionEvent event) {
        passwordP.setVisible(!passwordP.isVisible());
        passwordT.setText((!passwordP.isVisible()?passwordP.getText():passwordT.getText()));
        passwordP.setText((passwordP.isVisible()?passwordT.getText():passwordP.getText()));//passwordT.getText());
        passwordP.setVisible(passwordP.isVisible());
        passwordT.setVisible(!passwordP.isVisible());
        if(!passwordP.isVisible()) {
            ((ToggleButton) ((AnchorPane) passwordP.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-show\";");
        }
        else {
            ((ToggleButton) ((AnchorPane) passwordP.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-hide\";");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Validator(mail, "Le mail ne doit pas etre vide");
        new Validator(passwordP, "Le mot de passe ne doit pas etre vide", "Le mot de passe doit avoir au moins 8 caractères", 8);
    }
}
