package com.neb.nebotools.controller.component;

import com.neb.nebotools.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LineStockController extends Controller implements Initializable {

    @FXML
    private VBox expiration_date;

    @FXML
    private VBox num_lot;

    @FXML
    private VBox price;

    @FXML
    private VBox quantity;

    @FXML
    private VBox status;

    @FXML
    private VBox supplier;

    @FXML
    private VBox variant;
    @Override
    public void refresh() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
