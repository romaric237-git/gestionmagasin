package com.neb.nebotools.controller.page.product;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Product;
import com.neb.nebotools.utils.Utils;
import com.neb.nebotools.validator.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.sql.SQLException;

public class ProductController extends ControllerAbstract<Product> {

    @FXML
    private Button addCategory;
    @FXML
    private ListView<VBox> addStock;

    @FXML
    private TextField barcode;

    @FXML
    private TextField basePrice;

    @FXML
    private Button cancelBtn;

    @FXML
    private ChoiceBox<String> category;

    @FXML
    private HTMLEditor description;

    @FXML
    private ListView<VBox> inStock;

    @FXML
    private TextField minimumPrice;

    @FXML
    private TextField name;

    @FXML
    private TextField nameCategory;

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
        try {
            dao = DaoFactory.getProductDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void initValidator() {
        addValidator(new Validator(name, "Le nom du produit est obligatoire","Le nom a au moins 6 caracteres",6));
        addValidator(new Validator(barcode, "Le code barre est obligatoire","Le code barre moins 6 caracteres",6));
        addCategory.setDisable(true);
    }

    @Override
    protected void initComponent() {
        category.getItems().add("BASE");
        category.getSelectionModel().selectFirst();
        nameCategory.setOnKeyReleased(a->{
            addCategory.setDisable(!nameCategory.getText().isBlank() && nameCategory.getText().length()<4);
        });
        addCategory.setOnAction(a->{
            category.getItems().add(nameCategory.getText().toUpperCase());
            category.getSelectionModel().select(category.getItems().size()-1);
            nameCategory.setText("");
        });

        SpinnerValueFactory<Integer> baseFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
        baseFactory.setValue(50);

        SpinnerValueFactory<Integer> minimumFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
        minimumFactory.setValue(50);

        basePrice.setText("0");
        basePrice.textProperty().addListener((observable, oldValue, newValue )->{
            try {
                int val = Integer.parseInt(newValue);
                basePrice.setText(String.valueOf(val));
                if(val<0)
                    basePrice.setText("0");
                if(Integer.parseInt(minimumPrice.getText()) > val)
                    minimumPrice.setText(String.valueOf(val));

            }catch (Exception e){
                if(basePrice.getText().isBlank())
                    basePrice.setText("0");
                else basePrice.setText(oldValue);
            }
        });

        minimumPrice.setText("0");
        minimumPrice.textProperty().addListener((observable, oldValue, newValue )->{
            try {
                int val = Integer.parseInt(newValue);
                minimumPrice.setText(String.valueOf(val));
                if(val<0)
                    minimumPrice.setText("0");
                if(Integer.parseInt(basePrice.getText()) < val)
                    basePrice.setText(String.valueOf(val));
            }catch (Exception e){
                if(minimumPrice.getText().isBlank())
                    minimumPrice.setText("0");
                else minimumPrice.setText(oldValue);
            }
        });
        minimumFactory.valueProperty().addListener((observable,oldValue,newValue)->{
//            if(basePrice.getText()<newValue)
//                baseFactory.setValue(newValue);
        });
        baseFactory.valueProperty().addListener((observable,oldValue,newValue)->{
//            if(minimumPrice.getValue()>newValue)
//                minimumFactory.setValue(newValue);
        });
    }
}
