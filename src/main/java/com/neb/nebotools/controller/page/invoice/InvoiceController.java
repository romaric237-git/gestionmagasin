package com.neb.nebotools.controller.page.invoice;

import com.neb.nebotools.HelloApplication;
import com.neb.nebotools.controller.ControllerAbstract;
import com.neb.nebotools.controller.component.LineInvoiceController;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Invoice;
import com.neb.nebotools.model.InvoiceLine;
import com.neb.nebotools.utils.Utils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvoiceController extends ControllerAbstract<Invoice> {

    @FXML
    private Button addItem;
    @FXML
    private Label SubTotalTVA;

    @FXML
    private Label Total;

    @FXML
    private ComboBox<Customer> customer;

    @FXML
    private VBox items;

    @FXML
    private TextField search;

    @FXML
    private Label subTotal;

    @FXML
    private TextField date;

    @FXML
    private TextField invoiceField;
    private List<Customer> customers = new ArrayList<Customer>();

    List<LineInvoiceController> stockControllers = new ArrayList<LineInvoiceController>();

    @FXML
    public void addItem(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("view/component/lineInvoice.fxml"));

        loader.setResources(Utils.getBundle());
        VBox component = loader.load();

        LineInvoiceController invoiceController = loader.getController();
        invoiceController.setComponent(items, component, stockControllers, this);
        stockControllers.add(invoiceController);
        items.getChildren().add(component);
        refresh();
    }


    @Override
    public void refresh() {
        boolean isGood = true;
        int sum = 0;
        List<String> products = new ArrayList<String>();
        for (LineInvoiceController line : stockControllers) {
            if (!line.isValid() || products.contains(line.getItem().getId())) {
                isGood = false;
                break;
            }
            products.add(line.getItem().getId());
            sum += line.getPrice();
        }
        if (isGood && !stockControllers.isEmpty()) {
            subTotal.setText(sum + " CFA");
            SubTotalTVA.setText((sum * 0.1925) + " CFA");
            Total.setText((sum * 1.925) + " CFA");
            addBtn.setDisable(false);
        } else {
            subTotal.setText("0 CFA");
            SubTotalTVA.setText("0 CFA");
            Total.setText("0 CFA");
            addBtn.setDisable(true);
        }
        addBtn.setDisable(customer.getSelectionModel().getSelectedItem() == null || addBtn.isDisable());
    }

    @Override
    protected void setField() throws Exception {
        for(InvoiceLine line: entity.getInvoiceLineList()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("view/component/lineInvoice.fxml"));

            loader.setResources(Utils.getBundle());
            VBox component = loader.load();

            LineInvoiceController invoiceController = loader.getController();
            invoiceController.setComponent(items, component, stockControllers, this);
            stockControllers.add(invoiceController);
            items.getChildren().add(component);
            invoiceController.setEntity(line);
        }
        subTotal.setText(entity.getPrice() + " CFA");
        SubTotalTVA.setText((entity.getPrice()*0.1925) + " CFA");
        Total.setText((entity.getPrice()*1.925) + " CFA");
        invoiceField.setText(entity.getId());
        date.setText(entity.getDate().toString());
    }

    @Override
    protected void clearField() throws Exception {

    }

    @Override
    protected void disableField(boolean disable) {

        addItem.setVisible(!disable);
        customer.setDisable(disable);
        search.setDisable(disable);
    }

    @Override
    protected void buildEntity() throws Exception {
        if (entity == null)
            entity = new Invoice();
        entity.setId(invoiceField.getText());
        entity.setDate(Date.valueOf(LocalDate.now()));
        entity.setCustomer(customer.getSelectionModel().getSelectedItem().getId());
        entity.setPrice(Integer.parseInt(subTotal.getText().split(" CFA")[0]));
        for(LineInvoiceController invoiceController: stockControllers)
        entity.getInvoiceLineList().add(invoiceController.build());
    }

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getInvoiceDao();
    }

    @Override
    protected void initValidator() {

    }

    @Override
    protected void initComponent() throws SQLException, exception.EntityNotFoundException {
        date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, d MMM yyyy")));
        String text = "INV-";
        String listChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rd = new Random();
        for (int i = 0; i < 9; i++) {
            if ((i + 1) % 3 == 0 && i != 8)
                text += "-";
            text += listChar.charAt(rd.nextInt(0, 35));
        }
        invoiceField.setText(text);
        customers = DaoFactory.getCustomerDao().getAll(10000);
        customer.setItems(FXCollections.observableArrayList(customers));
        if (!customers.isEmpty())
            customer.getSelectionModel().selectFirst();
        search.textProperty().addListener(a -> {
            customer.setItems(FXCollections.observableArrayList(customers.stream().filter(b ->
                    b.getId().toLowerCase().contains(search.getText().toLowerCase()) ||
                            b.getLastname().toLowerCase().contains(search.getText().toLowerCase()) ||
                            b.getFirstname().toLowerCase().contains(search.getText().toLowerCase()) ||
                            b.getMail().toLowerCase().contains(search.getText().toLowerCase())).toList()));
            if (!customers.isEmpty())
                customer.getSelectionModel().selectFirst();
            refresh();
        });



    }

    public String getInvoice(){
        return invoiceField.getText();
    }


}
