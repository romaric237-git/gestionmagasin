package com.neb.nebotools.controller.component;

import com.neb.nebotools.controller.page.invoice.InvoiceController;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.InvoiceLine;
import com.neb.nebotools.model.Product;
import exception.EntityNotFoundException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LineInvoiceController implements Initializable {

    private static List<Product>products;

    @FXML
    private TextField cost;

    @FXML
    private ComboBox<Product> item;

    @FXML
    private Label price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField search;
    private VBox parent;
    private Parent line;

    private InvoiceController invoiceController;

    private List<LineInvoiceController> controllers;

    static {
        try {
            products = DaoFactory.getProductDao().getAll(10000);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public InvoiceLine build(){
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setPrice(Integer.parseInt(cost.getText()));
        invoiceLine.setQuantity(Integer.parseInt(quantity.getText()));
        invoiceLine.setProduct(item.getSelectionModel().getSelectedItem().getId());
        invoiceLine.setInvoice(invoiceController.getInvoice());
        return invoiceLine;
    }
    public void remove(ActionEvent event){
        parent.getChildren().remove(line);
        controllers.remove(this);
    }
    public static void refresh() throws SQLException, EntityNotFoundException {
        products = DaoFactory.getProductDao().getAll(10000);
    }

    public void setComponent(VBox parent, Parent child, List<LineInvoiceController> controllers, InvoiceController invoiceController) {
        this.parent = parent;
        this.line = child;
        this.controllers = controllers;
        this.invoiceController = invoiceController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        item.setItems(FXCollections.observableArrayList(products));
        item.setOnAction(a->{
            if(item.getSelectionModel().getSelectedItem() != null) {
                cost.setText(item.getSelectionModel().getSelectedItem().getBase_price() + "");
                quantity.setText("1");
                price.setText(item.getSelectionModel().getSelectedItem().getBase_price() + " CFA");

                quantity.setDisable(false);
                cost.setDisable(false);
                price.setDisable(false);
            }
        });
        search.textProperty().addListener(val->{
            item.setItems(FXCollections.observableArrayList(products.stream().filter(a->
                    (a.getName().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getId().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getBrand().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getCategory().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getBarcode().toLowerCase().contains(search.getText().toLowerCase()))
                    && a.getQuantity()>0
            ).toList()));

            if(!item.getItems().isEmpty()) {
                item.getSelectionModel().selectFirst();
                System.out.println(item.getSelectionModel().getSelectedItem().getBase_price());
                cost.setText(item.getSelectionModel().getSelectedItem().getBase_price() + "");
                quantity.setText("1");
                price.setText(item.getSelectionModel().getSelectedItem().getBase_price() + " CFA");

                quantity.setDisable(false);
                cost.setDisable(false);
            }else{
                price.setText(" CFA");

                quantity.setDisable(true);
                cost.setDisable(true);
                price.setDisable(true);
            }

        });
        if(!item.getItems().isEmpty()) {
            item.getSelectionModel().selectFirst();
            cost.setText(item.getSelectionModel().getSelectedItem().getBase_price() + "");
            quantity.setText("1");
            price.setText(item.getSelectionModel().getSelectedItem().getBase_price() + " CFA");
        }


        cost.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int val = Integer.parseInt(newValue);
                if (newValue.isEmpty() || newValue.isBlank() ||
                        val <item.getSelectionModel().getSelectedItem().getMin_price())
                    cost.setText(item.getSelectionModel().getSelectedItem().getBase_price()+"");
                else if ((newValue.charAt(0) == '0' && newValue.length() > 1) || newValue.charAt(0) == '-')
                    cost.setText(newValue.substring(1));
            } catch (Exception e) {
                cost.setText(oldValue);
            }finally {
                setPrice();
            }
        });

        quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int val = Integer.parseInt(newValue);

                if (newValue.isEmpty() || newValue.isBlank() ||
                        val >item.getSelectionModel().getSelectedItem().getQuantity())
                    quantity.setText("1");

                else if ((newValue.charAt(0) == '0' && newValue.length() > 1) || newValue.charAt(0) == '-')
                    quantity.setText(newValue.substring(1));
            } catch (Exception e) {
                quantity.setText(oldValue);
            }finally {
                setPrice();
            }
        });
    }

    private void setPrice(){
        try {
            price.setText(getPrice()+" CFA");
        }catch (IllegalStateException e){
            price.setText(" CFA");
        }finally {
            invoiceController.refresh();
        }

    }

    public int getPrice() throws IllegalStateException{
        if(quantity.getText().isBlank() || cost.getText().isBlank())
            throw new IllegalStateException();
        return Integer.parseInt(quantity.getText()) * Integer.parseInt(cost.getText());
    }

    public boolean isValid(){
        return !quantity.isDisable() && !cost.isDisable();
    }

    public Product getItem(){
        if(item.getSelectionModel().getSelectedItem()!=null)
            return item.getSelectionModel().getSelectedItem();
        throw new IllegalStateException();
    }
}
