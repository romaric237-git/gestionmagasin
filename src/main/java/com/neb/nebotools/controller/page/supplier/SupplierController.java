package com.neb.nebotools.controller.page.supplier;

import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Supplier;
import com.neb.nebotools.validator.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SupplierController extends ControllerAbstract<Supplier> {

    @FXML
    private TextArea description;

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
        description.setText(entity.getDescription());
    }

    @Override
    protected void clearField() throws Exception {
        firstname.setText("");
        lastname.setText("");
        mail.setText("");
        phone.setText("");
        description.clear();
    }

    @Override
    protected void disableField(boolean disable) {
        firstname.setDisable(disable);
        lastname.setDisable(disable);
        mail.setDisable(disable);
        phone.setDisable(disable);
        description.setDisable(disable);
    }

    @Override
    protected void buildEntity() throws Exception {
        if (entity == null)
            entity = new Supplier();
        entity.setFirstname(firstname.getText());
        entity.setLastname(lastname.getText());
        entity.setPhone(phone.getText());
        entity.setMail(mail.getText());
        entity.setDescription(description.getText());
    }

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getSupplierDao();
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

    }
}
