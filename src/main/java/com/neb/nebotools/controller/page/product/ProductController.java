package com.neb.nebotools.controller.page.product;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.controller.component.LineStockController;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.dao.ProductDao;
import com.neb.nebotools.model.Lot;
import com.neb.nebotools.model.Product;
import com.neb.nebotools.utils.Utils;
import com.neb.nebotools.validator.Validator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    private TextField brand;

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
    private TextField minimum;

    Set<String> uniqueItems = new TreeSet<>();
    List<LineStockController> stockControllers = new ArrayList<LineStockController>();

    @FXML
    void addAnotherLot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("view/component/lineStock.fxml"));

        loader.setResources(Utils.getBundle());
        VBox component = loader.load();

        LineStockController stockController = loader.getController();
        stockControllers.add(stockController);
        stockController.setComponent(addStock, component,entity, stockControllers);
        addStock.getItems().add(component);

        addStock.scrollTo(addStock.getItems().size() - 1);
    }

    @FXML
    void addCategory(ActionEvent event) {

    }

    @FXML
    void saveLot(ActionEvent event) throws SQLException, exception.EntityNotFoundException {
        for (LineStockController controller : stockControllers) {
            controller.save();
            controller.getRemove().fire();
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void setField() throws Exception {
        name.setText(entity.getName());
        brand.setText(entity.getBrand());
        basePrice.setText(entity.getBase_price() + "");
        minimumPrice.setText(entity.getMin_price() + "");
        barcode.setText(entity.getBarcode());
        category.getSelectionModel().select(entity.getCategory());
        description.setHtmlText(entity.getDescription());
        minimum.setText(entity.getMinimum()+"");
    }

    @Override
    protected void clearField() throws Exception {
        name.setText("");
        brand.setText("");
        basePrice.setText("1");
        minimumPrice.setText("1");
        barcode.setText("");
        category.getSelectionModel().selectFirst();
        description.setHtmlText("");
        minimum.setText("1");
    }

    @Override
    protected void disableField(boolean disable) {
        name.setDisable(disable);
        brand.setDisable(disable);
        basePrice.setDisable(disable);
        minimumPrice.setDisable(disable);
        barcode.setDisable(disable);
        category.setDisable(disable);
        description.setDisable(disable);
        minimum.setDisable(disable);
    }

    @Override
    protected void buildEntity() throws Exception {
        if (entity == null)
            entity = new Product();
        entity.setName(name.getText());
        entity.setBrand(brand.getText());
        entity.setCategory(category.getSelectionModel().getSelectedItem());
        entity.setBase_price(Integer.parseInt(basePrice.getText()));
        entity.setMin_price(Integer.parseInt(minimumPrice.getText()));
        entity.setBarcode(barcode.getText());
        entity.setDescription(description.getHtmlText());
        entity.setMinimum(Integer.parseInt(minimum.getText()));
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
        addValidator(new Validator(name, "Le nom du produit est obligatoire", "Le nom a au moins 4 caracteres", 4));
        addValidator(new Validator(barcode, "Le code barre est obligatoire", "Le code barre moins 4 caracteres", 4));
        addValidator(new Validator(brand, "La marque est obligatoire", "La marque barre moins 4 caracteres", 4));
        addCategory.setDisable(true);
    }

    @Override
    protected void initComponent() throws SQLException {

        category.getItems().addAll(FXCollections.observableArrayList(((ProductDao) dao).getCategory()));
        category.getItems().add("BASE");
        category.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty() && !uniqueItems.contains(newValue)) {
                uniqueItems.add(newValue);
                category.setItems(FXCollections.observableArrayList(uniqueItems));
                category.getSelectionModel().selectFirst();
            }
        });

        category.getSelectionModel().selectFirst();
        nameCategory.setOnKeyReleased(a -> {
            addCategory.setDisable(!nameCategory.getText().isBlank() && nameCategory.getText().length() < 4);
        });
        addCategory.setOnAction(a -> {
            category.getItems().add(nameCategory.getText().toUpperCase());
            category.getSelectionModel().select(category.getItems().size() - 1);
            nameCategory.setText("");
        });

        SpinnerValueFactory<Integer> baseFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
        baseFactory.setValue(50);

        SpinnerValueFactory<Integer> minimumFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE);
        minimumFactory.setValue(50);

        basePrice.setText("0");
        basePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int val = Integer.parseInt(newValue);
                if (newValue.isEmpty() || newValue.isBlank()) basePrice.setText("0");
                else if ((newValue.charAt(0) == '0' && newValue.length() > 1) || newValue.charAt(0) == '-')
                    basePrice.setText(newValue.substring(1));

                if (Integer.parseInt(minimumPrice.getText()) > val) minimumPrice.setText(String.valueOf(val));

            } catch (Exception e) {
                basePrice.setText(oldValue);
            }
        });

        minimumPrice.setText("0");
        minimumPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int val = Integer.parseInt(newValue);

                if (newValue.isEmpty() || newValue.isBlank()) minimumPrice.setText("0");

                else if ((newValue.charAt(0) == '0' && newValue.length() > 1) || newValue.charAt(0) == '-')
                    minimumPrice.setText(newValue.substring(1));

                if (Integer.parseInt(basePrice.getText()) < val) basePrice.setText(String.valueOf(val));

            } catch (Exception e) {
                minimumPrice.setText(oldValue);
            }
        });
    }

    @Override
    public void setEntity(Product entity) throws SQLException, exception.EntityNotFoundException, IOException {
        super.setEntity(entity);
        inStock.getItems().clear();
        for(Lot lot: DaoFactory.getLotDao().findByProduct(entity.getId())){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("view/component/lineStock.fxml"));

            loader.setResources(Utils.getBundle());
            VBox component = loader.load();

            LineStockController stockController = loader.getController();
            stockController.setComponent(inStock, component, lot);
            stockControllers.add(stockController);
            stockController.setComponent(addStock, component,entity,stockControllers);
            inStock.getItems().add(component);

            inStock.scrollTo(inStock.getItems().size() - 1);
        }
    }
}
