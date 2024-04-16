package com.neb.nebotools.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class ControllerPrincipalAbstract<T> extends Controller implements Initializable {


    protected abstract void initComponent() throws SQLException, exception.EntityNotFoundException;

    public abstract void initialize(URL arg0, ResourceBundle arg1);
}
