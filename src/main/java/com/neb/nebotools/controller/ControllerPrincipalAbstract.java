package com.neb.nebotools.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ControllerPrincipalAbstract<T> extends Controller implements Initializable {


    protected abstract void initComponent();

    public abstract void initialize(URL arg0, ResourceBundle arg1);
}
