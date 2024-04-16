package com.neb.nebotools.controller.page.customer;

import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.validator.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CustomerController extends ControllerAbstract<Customer> {

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField mail;

    @FXML
    private TextField phone;

    @Override
    public void refresh() {

    }

    @Override
    protected void setField() throws Exception {
        firstname.setText(entity.getFirstname());
        lastname.setText(entity.getLastname());
        mail.setText(entity.getMail());
        phone.setText(entity.getPhone());

    }

    @Override
    protected void clearField() throws Exception {
        firstname.setText("");
        lastname.setText("");
        mail.setText("");
        phone.setText("");
    }

    @Override
    protected void disableField(boolean disable) {
        firstname.setDisable(disable);
        lastname.setDisable(disable);
        mail.setDisable(disable);
        phone.setDisable(disable);
    }

    @Override
    protected void buildEntity() throws Exception {
        if (entity == null)
            entity = new Customer();
        entity.setFirstname(firstname.getText());
        entity.setLastname(lastname.getText());
        entity.setPhone(phone.getText());
        entity.setMail(mail.getText());
    }

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getCustomerDao();
    }

    @Override
    protected void initValidator() {
        addValidator(new Validator(lastname, "Le prenom est obligatoire", "Le nom a au moins 6 caracteres", 6));
        addValidator(new Validator(firstname, "Le nom est obligatoire", "Le nom a au moins 6 caracteres", 6));
        addValidator(new Validator(mail, "Le mail est obligatoire"));
        addValidator(new Validator(phone, "Le téléphone est obligatoire"));
    }

    @Override
    protected void initComponent() {
        phone.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                if (!newVal.isBlank() &&
                        ((newVal.charAt(newVal.length() - 1) < '0' &&
                                newVal.charAt(newVal.length() - 1) > '9') &&
                                newVal.charAt(newVal.length() - 1) != ' ')) {
                    throw new Exception();
                }
                for(int i = 0; i<newVal.length(); i++)
                    if(newVal.charAt(i)==' ' && (i-1)%3!=0)
                        newVal = newVal.replaceAll(" ", "");


                if (newVal.length() >= 2 && newVal.charAt(1) != ' ')
                    phone.setText(newVal.substring(0,1) + " " + newVal.substring(1,newVal.length()));

                if (newVal.length() >= 5 && newVal.charAt(4) != ' ')
                    phone.setText(newVal.substring(0,4) + " " + newVal.substring(4,newVal.length()));

                if (newVal.length() >= 8 && newVal.charAt(7) != ' ')
                    phone.setText(newVal.substring(0,7) + " " + newVal.substring(7,newVal.length()));

                if (newVal.length() >= 11 && newVal.charAt(10) != ' ')
                    phone.setText(newVal.substring(0,10) + " " + newVal.substring(10,newVal.length()));

                if (newVal.length() > 13)
                    phone.setText(newVal.substring(0,13));



            } catch (Exception e) {
                e.printStackTrace();
                phone.setText(oldVal != null ? oldVal : "");
            }

        });
    }
}
