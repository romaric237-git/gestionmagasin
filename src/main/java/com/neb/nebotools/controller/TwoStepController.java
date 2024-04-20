package com.neb.nebotools.controller;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Employee;
import com.neb.nebotools.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TwoStepController implements Initializable {

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
    void verify(ActionEvent event) throws IOException {
        try {
            int nbre = Integer.parseInt(digit1.getText())*100000;
            nbre += Integer.parseInt(digit2.getText())*10000;
            nbre += Integer.parseInt(digit3.getText())*1000;
            nbre += Integer.parseInt(digit4.getText())*100;
            nbre += Integer.parseInt(digit5.getText())*10;
            nbre += Integer.parseInt(digit6.getText());
            if(DaoFactory.getEmployeeDao().loginWithPin(nbre)!=null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Code PIN Correct");
                alert.setHeaderText("Code PIN Correct");
                alert.showAndWait();
                Utils.openApp(((Stage) (digit1.getScene().getWindow())),"view/application.fxml");
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Code PIN incorrect");
                alert.setHeaderText("Code PIN incorrect");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.err.println("Connexion impossible");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        digit1.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit1,oldValue,newValue))
                digit2.requestFocus();
        });
        digit2.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit2,oldValue,newValue))
                digit3.requestFocus();
        });
        digit3.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit3,oldValue,newValue))
                digit4.requestFocus();
        });
        digit4.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit4,oldValue,newValue))
                digit5.requestFocus();
        });
        digit5.textProperty().addListener((observable,oldValue,newValue)->{
            if(digit(digit5,oldValue,newValue))
                digit6.requestFocus();
        });
        digit6.textProperty().addListener((observable,oldValue,newValue)->{
            digit(digit6,oldValue,newValue);
        });

    }

    private boolean digit(TextField digit, String oldValue, String newValue) {
        if(!newValue.isEmpty() && newValue.charAt(0)>='0' && newValue.charAt(0)<='9')
            digit.setText((oldValue.length() == 1)?oldValue:newValue);
        else
            digit.setText("");
        return !digit.getText().isEmpty();
    }


}
