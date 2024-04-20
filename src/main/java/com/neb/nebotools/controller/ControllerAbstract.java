package com.neb.nebotools.controller;

import com.neb.nebotools.controller.enumeration.State;
import com.neb.nebotools.controller.page.employee.EmployeeController;
import com.neb.nebotools.dao.Dao;
import com.neb.nebotools.model.Employee;
import com.neb.nebotools.validator.Validator;
import exception.EntityNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class ControllerAbstract<T> extends ControllerPrincipalAbstract<T> {
    protected Dao<T> dao;
    protected List<Validator> validators;

    private ApplicationController principal;

    private ControllerListAbstract<T> listController;

    protected State state;

    protected boolean isUpdate = false;

    protected T entity;

    @FXML
    protected Button addBtn;

    @FXML
    protected Button cancelBtn;


    public Button getCancelBtn() {
        return cancelBtn;
    }

    protected abstract void setField() throws Exception;

    protected abstract void clearField() throws Exception;

    protected abstract void disableField(boolean disable);

    protected abstract void buildEntity() throws Exception;

    protected abstract void setDao() throws SQLException;

    public void setEntity(T entity) throws SQLException, exception.EntityNotFoundException, IOException {
        this.entity = entity;
        try {
            setField();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setState(State state) throws Exception {
        this.state = state;
        if (state == State.DETAIL) {
            ((Pane) cancelBtn.getParent()).getChildren().remove(addBtn);
            disableField(true);
        }else{
            if(!((Pane)cancelBtn.getParent()).getChildren().contains(addBtn))
                ((Pane)cancelBtn.getParent()).getChildren().add(addBtn);
        }

        if(state == State.MODIFY) {
            addBtn.setText("Modifier");
            disableField(false);
        }
        if(state == State.ADD){
            addBtn.setText("Ajouter");
            disableField(false);
            clearField();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setDisable(true);
        validators = new ArrayList<Validator>();
        initValidator();
        try {
            setDao();
            initComponent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        addBtn.setOnAction(e -> {
            try {
                buildEntity();
                if (state == State.ADD) {
                    listController.addObj(dao.create(entity));
                    cancelBtn.fire();
                } else if (state == State.MODIFY) {

                    listController.replaceObj(dao.update(entity));
                    cancelBtn.fire();
                }
                //isUpdate = true;
                //backBtn.fire();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    protected abstract void initValidator();

    protected void addValidator(Validator validator) {

        validators.add(validator);
        if (validator.getControl() instanceof TextField) {
            TextField textField = (TextField) validator.getControl();
            textField.setOnKeyReleased(a -> {
                addBtn.setDisable(!isValid());
            });
        }
    }

    private boolean isValid() {
        for (Validator validator : validators)
            if (!validator.isValid()) return false;
        if(entity instanceof Employee e)
           return ((EmployeeController)this).verify() && true;
        return true;
    }

    public void setController(ApplicationController principal, ControllerListAbstract<T> listController) {
        this.principal = principal;
        this.listController = listController;
    }

    private boolean isValidForm() {
        for (Validator validator : validators)
            if (!validator.isValid()) return false;
        return true;
    }
}
