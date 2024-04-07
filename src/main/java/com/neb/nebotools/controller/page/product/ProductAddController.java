package com.neb.nebotools.controller.page.product;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.ControllerAddAbstract;
import com.neb.nebotools.model.Product;
import com.neb.nebotools.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProductAddController extends ControllerAddAbstract<Product> {

    @FXML
    private ListView<Parent> addStock;

    @FXML
    private VBox barcode;

    @FXML
    private VBox basePrice;

    @FXML
    private VBox category;

    @FXML
    private VBox description;

    @FXML
    private ListView<?> inStock;

    @FXML
    private VBox minimumPrice;

    @FXML
    private VBox name;

    @FXML
    private VBox nameCategory;

    @FXML
    void addAnotherLot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("view/component/lineStock.fxml"));

        loader.setResources(Utils.getBundle());
        VBox component = loader.load();

//        productListController = loader1.getController();
//        productAddController = loader2.getController();
        addStock.getItems().add(component);

        addStock.scrollTo(addStock.getItems().size()-1);
    }

    @FXML
    void addCategory(ActionEvent event) {

    }

    @FXML
    void saveLot(ActionEvent event) {

    }

    @Override
    public void refresh() {

    }
}
