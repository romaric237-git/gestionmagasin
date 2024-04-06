package com.neb.nebotools.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class ControllerListAbstract<T> extends ControllerAbstract<T>{
    @FXML
    protected Button btnAdd;

    public Button getAdd() {
        return btnAdd;
    }
}
