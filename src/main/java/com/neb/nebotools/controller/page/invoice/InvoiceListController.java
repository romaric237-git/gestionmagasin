package com.neb.nebotools.controller.page.invoice;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Employee;
import com.neb.nebotools.model.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class InvoiceListController extends ControllerListAbstract<Invoice> {


    @FXML
    private TableColumn<Invoice, Customer> columnCustomer;

    @FXML
    private TableColumn<Invoice, Employee> columnEmployee;

    @FXML
    private TableColumn<Invoice, String> columnInvoice;

    @FXML
    private TableColumn<Invoice, Integer> columnPrice;

    @FXML
    private TableColumn<Invoice, Date> columnDate;

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getInvoiceDao();
    }

    @Override
    protected void initTable() {
        columnCustomer.setCellValueFactory(new PropertyValueFactory<Invoice, Customer>("customer"));
        columnEmployee.setCellValueFactory(new PropertyValueFactory<Invoice, Employee>("employee"));
        columnInvoice.setCellValueFactory(new PropertyValueFactory<Invoice, String>("id"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("price"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Invoice, Date>("dateString"));
    }

    @Override
    public List<Invoice> search() {
        return null;
    }

    @Override
    protected void initComponent() throws SQLException {

    }
}
