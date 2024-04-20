package com.neb.nebotools.controller.page.setting;

import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Employee;
import exception.EntityNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
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
    private ChoiceBox<String> sex;

    @FXML
    void switchP(ActionEvent event) {
        passwordP1.setVisible(!passwordP1.isVisible());
        passwordT1.setText((!passwordP1.isVisible() ? passwordP1.getText() : passwordT1.getText()));
        passwordP1.setText((passwordP1.isVisible() ? passwordT1.getText() : passwordP1.getText()));//passwordT.getText());
        passwordP1.setVisible(passwordP1.isVisible());
        passwordT1.setVisible(!passwordP1.isVisible());
        if (!passwordP1.isVisible()) {
            ((ToggleButton) ((AnchorPane) passwordP1.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-show\";");
        } else {
            ((ToggleButton) ((AnchorPane) passwordP1.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-hide\";");
        }
    }

    @FXML
    void switchP2(ActionEvent event) {
        passwordVP.setVisible(!passwordVP.isVisible());
        passwordVT.setText((!passwordVP.isVisible() ? passwordVP.getText() : passwordVT.getText()));
        passwordVP.setText((passwordVP.isVisible() ? passwordVT.getText() : passwordVP.getText()));//passwordT.getText());
        passwordVP.setVisible(passwordVP.isVisible());
        passwordVT.setVisible(!passwordVP.isVisible());
        if (!passwordVP.isVisible()) {
            ((ToggleButton) ((AnchorPane) passwordVP.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-show\";");
        } else {
            ((ToggleButton) ((AnchorPane) passwordVP.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-hide\";");
        }
    }

    private boolean digit(TextField digit, String oldValue, String newValue) {
        if (!newValue.isEmpty() && newValue.charAt(0) >= '0' && newValue.charAt(0) <= '9')
            digit.setText((oldValue.length() == 1) ? oldValue : newValue);
        else
            digit.setText("");
        return !digit.getText().isEmpty();
    }

    public void refresh() {
        sex.getItems().clear();

        sex.getItems().addAll("Femme", "Homme");
        sex.getSelectionModel().selectLast();

        digit1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (digit(digit1, oldValue, newValue))
                digit2.requestFocus();
        });
        digit2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (digit(digit2, oldValue, newValue))
                digit3.requestFocus();
        });
        digit3.textProperty().addListener((observable, oldValue, newValue) -> {
            if (digit(digit3, oldValue, newValue))
                digit4.requestFocus();
        });
        digit4.textProperty().addListener((observable, oldValue, newValue) -> {
            if (digit(digit4, oldValue, newValue))
                digit5.requestFocus();
        });
        digit5.textProperty().addListener((observable, oldValue, newValue) -> {
            if (digit(digit5, oldValue, newValue))
                digit6.requestFocus();
        });
        digit6.textProperty().addListener((observable, oldValue, newValue) -> {
            digit(digit6, oldValue, newValue);
        });

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


            birth.setValue(e.getBirth().toLocalDate());
            firstname.setText(e.getFirstname());
            lastname.setText(e.getLastname());
            passwordVP.setText(e.getPassword());
            passwordVT.setText(e.getPassword());
            passwordP1.setText(e.getPassword());
            passwordT1.setText(e.getPassword());
            phone.setText(e.getPhone());
            sex.getSelectionModel().select(e.getSex());

            changePersonalInform.setOnAction(a -> {
                try {
                    e.setSex(sex.getSelectionModel().getSelectedIndex());
                    e.setBirth(Date.valueOf(birth.getValue()));
                    e.setFirstname(firstname.getText());
                    e.setLastname(lastname.getText());
                    e.setPhone(phone.getText());
                    DaoFactory.getEmployeeDao().update(e);
                    refresh();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (EntityNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        passwordP1.textProperty().addListener(a -> verify());
        passwordT1.textProperty().addListener(a -> verify());
        passwordVP.textProperty().addListener(a -> verify());
        passwordVT.textProperty().addListener(a -> verify());

        changePin.setOnAction(a -> {
            try {
                int nbre = Integer.parseInt(digit1.getText()) * 100000;
                nbre += Integer.parseInt(digit2.getText()) * 10000;
                nbre += Integer.parseInt(digit3.getText()) * 1000;
                nbre += Integer.parseInt(digit4.getText()) * 100;
                nbre += Integer.parseInt(digit5.getText()) * 10;
                nbre += Integer.parseInt(digit6.getText());
                DaoFactory.getEmployeeDao().update(nbre);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Code PIN Modifié avec success");
                alert.setHeaderText("Code PIN Modifié avec success");
                alert.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        changePassword.setOnAction(a -> {
            try {
                DaoFactory.getEmployeeDao().update( (passwordP1.isVisible() ? passwordP1.getText() : passwordT1.getText()));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mot de passe Modifié avec success");
                alert.setHeaderText("Mot de passe Modifié avec success");
                alert.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public boolean verify() {
        String password = (passwordP1.isVisible() ? passwordP1.getText() : passwordT1.getText());
        String passwordVerify = (passwordVP.isVisible() ? passwordVP.getText() : passwordVT.getText());

        VBox parent = (VBox) passwordVT.getParent().getParent().getParent();
        VBox parent2 = (VBox) passwordT1.getParent().getParent().getParent();

        if (parent.getChildren().size() == 1) {
            Label error = new Label("Les mots de passes ne correspondent pas");
            error.getStyleClass().add("label-danger");
            error.setVisible(false);
            parent.getChildren().add(error);

            Label error2 = new Label("Les mots de passes ne correspondent pas");
            error2.getStyleClass().add("label-danger");
            error2.setVisible(false);
            parent2.getChildren().add(error2);
        }

        ((Label) parent.getChildren().get(1)).setVisible(false);
        ((Label) parent2.getChildren().get(1)).setVisible(false);
        if (!password.equals(passwordVerify)) {
            ((Label) parent.getChildren().get(1)).setVisible(true);
        }

        if (password.length() < 8) {
            ((Label) parent2.getChildren().get(1)).setVisible(true);
            ((Label) parent2.getChildren().get(1)).setText("Mot de passe trop court. Au moins 8 caractères");
        }
        changePassword.setDisable(!password.equals(passwordVerify));
        return password.equals(passwordVerify);
    }

}
