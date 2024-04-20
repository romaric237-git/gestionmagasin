package com.neb.nebotools.controller.page.employee;

import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Employee;
import com.neb.nebotools.validator.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeController extends ControllerAbstract<Employee> {

    @FXML
    private DatePicker birth;

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
    private ChoiceBox<String> sex;

    @FXML
    void switchP(ActionEvent event) {
        passwordP.setVisible(!passwordP.isVisible());
        passwordT.setText((!passwordP.isVisible() ? passwordP.getText() : passwordT.getText()));
        passwordP.setText((passwordP.isVisible() ? passwordT.getText() : passwordP.getText()));//passwordT.getText());
        passwordP.setVisible(passwordP.isVisible());
        passwordT.setVisible(!passwordP.isVisible());
        if (!passwordP.isVisible()) {
            ((ToggleButton) ((AnchorPane) passwordP.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-show\";");
        } else {
            ((ToggleButton) ((AnchorPane) passwordP.getParent().getParent()).getChildren().get(1)).getGraphic().setStyle("-fx-icon-code: \"bx-hide\";");
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

    @Override
    public void refresh() {

    }

    @Override
    protected void setField() throws Exception {
        birth.setValue(entity.getBirth().toLocalDate());
        firstname.setText(entity.getFirstname());
        lastname.setText(entity.getLastname());
        login.setText(entity.getLogin());
        mail.setText(entity.getMail());
        passwordP.setText(entity.getPassword());
        passwordT.setText(entity.getPassword());
        passwordVP.setText(entity.getPassword());
        passwordVT.setText(entity.getPassword());
        phone.setText(entity.getPhone());
        sex.getSelectionModel().select(entity.getSex());

    }

    @Override
    protected void clearField() throws Exception {
        sex.getSelectionModel().selectFirst();
        birth.setValue(LocalDate.now().minusYears(15));

        firstname.setText("");
        lastname.setText("");
        login.setText("");
        mail.setText("");
        passwordP.setText("");
        passwordT.setText("");
        passwordVP.setText("");
        passwordVT.setText("");
        phone.setText("");
    }

    @Override
    protected void disableField(boolean disable) {
        sex.setDisable(disable);
        birth.setDisable(disable);

        firstname.setDisable(disable);
        lastname.setDisable(disable);
        login.setDisable(disable);
        mail.setDisable(disable);
        passwordP.setDisable(disable);
        passwordT.setDisable(disable);
        passwordVP.setDisable(disable);
        passwordVT.setDisable(disable);
        phone.setDisable(disable);
    }

    @Override
    protected void buildEntity() throws Exception {
        if (entity == null)
            entity = new Employee();
        entity.setSex(sex.getSelectionModel().getSelectedIndex());
        entity.setBirth(Date.valueOf(birth.getValue()));
        entity.setFirstname(firstname.getText());
        entity.setLastname(lastname.getText());
        entity.setLogin(login.getText());
        entity.setMail(mail.getText());
        entity.setPassword((passwordP.isVisible() ? passwordP.getText() : passwordT.getText()));
        entity.setPhone(phone.getText());
    }

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getEmployeeDao();
    }

    @Override
    protected void initValidator() {
        addValidator(new Validator(mail, "Le mail est obligatoire"));
        addValidator(new Validator(login, "Le login ne doit pas etre vide", "Le login doit avoir au moins 4 caractères", 4));
        addValidator(new Validator(lastname, "Le prenom ne doit pas etre vide", "Le prenom doit avoir au moins 4 caractères", 4));
        addValidator(new Validator(firstname, "Le nom ne doit pas etre vide", "Le nom doit avoir au moins 4 caractères", 4));
        addValidator(new Validator(phone, "Le téléphone est obligatoire"));

        passwordP.textProperty().addListener(a -> verify());
        passwordT.textProperty().addListener(a -> verify());
        passwordVP.textProperty().addListener(a -> verify());
        passwordVT.textProperty().addListener(a -> verify());


    }

    public boolean verify() {
        String password = (passwordP.isVisible() ? passwordP.getText() : passwordT.getText());
        String passwordVerify = (passwordVP.isVisible() ? passwordVP.getText() : passwordVT.getText());

        VBox parent = (VBox) passwordVT.getParent().getParent().getParent();
        VBox parent2 = (VBox) passwordT.getParent().getParent().getParent();

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

        return password.equals(passwordVerify);
    }


    @Override
    protected void initComponent() {
        sex.getItems().addAll("Femme", "Homme");
        sex.getSelectionModel().selectLast();

        birth.setValue(LocalDate.now().minusYears(15));
        birth.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(localDate.isAfter(LocalDate.now().minusYears(15)));
            }
        });
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d MMM yyyy");

            @Override
            public String toString(LocalDate localDate) {
                return (localDate == null) ? "" : dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return (s == null || s.isEmpty()) ? null : LocalDate.parse(s, dateTimeFormatter);
            }
        };
        birth.setConverter(converter);


        phone.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                if (!newVal.isBlank() &&
                        ((newVal.charAt(newVal.length() - 1) < '0' &&
                                newVal.charAt(newVal.length() - 1) > '9') &&
                                newVal.charAt(newVal.length() - 1) != ' ')) {
                    throw new Exception();
                }
                for (int i = 0; i < newVal.length(); i++)
                    if (newVal.charAt(i) == ' ' && (i - 1) % 3 != 0)
                        newVal = newVal.replaceAll(" ", "");


                if (newVal.length() >= 2 && newVal.charAt(1) != ' ')
                    phone.setText(newVal.substring(0, 1) + " " + newVal.substring(1, newVal.length()));

                if (newVal.length() >= 5 && newVal.charAt(4) != ' ')
                    phone.setText(newVal.substring(0, 4) + " " + newVal.substring(4, newVal.length()));

                if (newVal.length() >= 8 && newVal.charAt(7) != ' ')
                    phone.setText(newVal.substring(0, 7) + " " + newVal.substring(7, newVal.length()));

                if (newVal.length() >= 11 && newVal.charAt(10) != ' ')
                    phone.setText(newVal.substring(0, 10) + " " + newVal.substring(10, newVal.length()));

                if (newVal.length() > 13)
                    phone.setText(newVal.substring(0, 13));


            } catch (Exception e) {
                e.printStackTrace();
                phone.setText(oldVal != null ? oldVal : "");
            }

        });
    }
}
