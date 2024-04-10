package com.neb.nebotools.controller;

import com.neb.nebotools.controller.enumeration.State;
import com.neb.nebotools.dao.Dao;
import com.neb.nebotools.validator.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    public Button getAddBtn(){
        return addBtn;
    }

    public Button getCancelBtn(){
        return cancelBtn;
    }

    protected abstract void setField() throws Exception;

    protected abstract void clearField() throws Exception;

    protected abstract void disableField(boolean disable);

    protected abstract void buildEntity() throws Exception;

    protected abstract void setDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBtn.setDisable(true);
        validators = new ArrayList<Validator>();
        initValidator();
        initComponent();
        setDao();
        addBtn.setOnAction(e->{
            try {
                buildEntity();
                if(state == State.ADD) {
                    listController.addObj(dao.create(entity));
                    //principal.backPane(page, manageController, state, "Utilisateur ajouté avec succès");
                    //mainBtn.setDisable(true);
                }
                else if(state == State.MODIFY) {
                    //manageController.replaceObj(dao.update(entity));
                    //principal.backPane(page, manageController, state, "Utilisateur modifié avec succès");
                }
                //isUpdate = true;
                //backBtn.fire();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (exception.EntityNotFoundException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    protected abstract void initValidator();

    protected void addValidator(Validator validator){
        validators.add(validator);
    }

    public void setController(ApplicationController principal, ControllerListAbstract<T> listController) {
        this.principal = principal;
        this.listController = listController;
    }

    private boolean isValidForm(){
        for(Validator validator: validators)
            if(!validator.isValid()) return false;
        return true;
    }
}
