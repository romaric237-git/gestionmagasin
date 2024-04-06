package com.neb.nebotools.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class ControllerAddAbstract<T> extends ControllerAbstract<T> {

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
}
