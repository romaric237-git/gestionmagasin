package com.neb.nebotools.controller.page.supplier;

import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.model.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SupplierController extends ControllerAbstract<Supplier> {

    @FXML
    private TextField birth;

    @FXML
    private Button cancelBtn;

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

    }

    @Override
    protected void clearField() throws Exception {

    }

    @Override
    protected void disableField(boolean disable) {

    }

    @Override
    protected void buildEntity() throws Exception {

    }

    @Override
    protected void setDao() {

    }

    @Override
    protected void initValidator() {

    }

    @Override
    protected void initComponent() {

    }
}
