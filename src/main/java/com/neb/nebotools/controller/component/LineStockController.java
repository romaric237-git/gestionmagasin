package com.neb.nebotools.controller.component;

import com.neb.nebotools.controller.Controller;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Lot;
import com.neb.nebotools.model.Product;
import com.neb.nebotools.model.Supplier;
import exception.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class LineStockController extends Controller implements Initializable {
    @FXML
    private Button archive;

    @FXML
    private DatePicker expiration_date;

    @FXML
    private DatePicker delivery_date;

    @FXML
    private TextField num_lot;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private Button remove;

    @FXML
    private ChoiceBox<String> status;

    @FXML
    private ChoiceBox<Supplier> supplier;

    @FXML
    private TextField variant;

    private ListView<VBox> component = new ListView<VBox>();
    private VBox view;

    private Product product;

    @Override
    public void refresh() {

    }

    public void setProduct(Product product) {
        this.product = product;

        Integer.parseInt(quantity.getText());
        variant.getText();
        Date.valueOf(delivery_date.getValue());
        Date.valueOf(expiration_date.getValue());
        supplier.getSelectionModel().getSelectedItem().getId();
    }

    public void setComponent(ListView<VBox> component, VBox view, Lot lot) throws SQLException, EntityNotFoundException {
        remove.setVisible(false);
        num_lot.setText(lot.getNum_lot());
        quantity.setText(lot.getQuantity() + "");
        variant.setText(lot.getVariant());
        delivery_date.setValue(lot.getDelivery_date().toLocalDate());
        expiration_date.setValue(lot.getExpiration_date().toLocalDate());
        supplier.getSelectionModel().select(DaoFactory.getSupplierDao().find(lot.getSupplier()));
        price.setText(lot.getPrice() + "");
        status.getSelectionModel().select(lot.getStatus());


        num_lot.setDisable(true);
        quantity.setDisable(true);
        variant.setDisable(true);
        delivery_date.setDisable(true);
        expiration_date.setDisable(true);
        supplier.setDisable(true);
        price.setDisable(true);
        status.setDisable(true);

        archive.setOnAction(a -> {
            try {
                DaoFactory.getLotDao().delete(lot.getId());
                component.getItems().remove(view);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void setComponent(ListView<VBox> component, VBox view, Product product, List<LineStockController> stockControllers) {
        this.product = product;
        remove.setOnAction(a -> {
            component.getItems().remove(view);
            stockControllers.remove(this);
        });
    }

    public void save() throws SQLException, EntityNotFoundException {
        if (product != null)
            DaoFactory.getLotDao().create(new Lot(product.getId(), num_lot.getText(), Integer.parseInt(quantity.getText()), variant.getText(), Date.valueOf(delivery_date.getValue()), Date.valueOf(expiration_date.getValue()), supplier.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(price.getText()), status.getSelectionModel().getSelectedIndex()));
    }

    public Button getRemove() {
        return remove;
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 18; i++) {
            // Générer un caractère alphanumérique aléatoire
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            sb.append(Character.toUpperCase(randomChar));
            // Ajouter un tiret après chaque groupe de trois caractères, sauf pour le dernier groupe
            if ((i + 1) % 3 == 0 && i != 17) {
                sb.append("-");
            }
        }

        return sb.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            supplier.getItems().addAll(FXCollections.observableArrayList(DaoFactory.getSupplierDao().getAll(10000)));
            if (!supplier.getItems().isEmpty())
                supplier.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        expiration_date.setValue(LocalDate.now().plusWeeks(1));
        expiration_date.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate localDate, boolean b) {
                super.updateItem(localDate, b);
                setDisable(localDate.isBefore(LocalDate.now().plusWeeks(1)));
            }
        });
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, d MMM yyyy");

            @Override
            public String toString(LocalDate localDate) {
                return (localDate == null) ? "" : dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return (s == null || s.isEmpty()) ? null : LocalDate.parse(s, dateTimeFormatter);
            }
        };
        expiration_date.setConverter(converter);
        price.setText("0");
        quantity.setText("0");
        price.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                Integer.parseInt(newVal);
                if (newVal.isEmpty() || newVal.isBlank()) price.setText("0");
                else if ((newVal.charAt(0) == '0' && newVal.length() > 1) || newVal.charAt(0) == '-')
                    price.setText(newVal.substring(1));
            } catch (Exception e) {
                price.setText("0");

            }
        });
        quantity.textProperty().addListener((observable, oldVal, newVal) -> {
            try {
                Integer.parseInt(newVal);
                if (newVal.isEmpty() || newVal.isBlank()) quantity.setText("0");
                else if ((newVal.charAt(0) == '0' && newVal.length() > 1) || newVal.charAt(0) == '-')
                    quantity.setText(newVal.substring(1));
            } catch (Exception e) {
                quantity.setText("0");
            }
        });

        status.getItems().addAll("EN COURS", "TERMINER");
        status.getSelectionModel().selectLast();
        delivery_date.setValue(LocalDate.now());
        delivery_date.setDisable(true);
        num_lot.setText(generateRandomString());
    }
}
