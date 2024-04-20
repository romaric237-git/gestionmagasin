package com.neb.nebotools.controller.page.supplier;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.AbstractEntity;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Supplier;
import exception.EntityNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class SupplierListController extends ControllerListAbstract<Supplier> {
    @FXML
    private TableColumn<Customer, String> columnSupplier;

    @FXML
    private TableColumn<Customer, String> columnFirstname;

    @FXML
    private TableColumn<Customer, String> columnLastname;

    @FXML
    private TableColumn<?, ?> columnPayment;

    @FXML
    private TableColumn<Customer, String> columnPhone;

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getSupplierDao();
    }

    @Override
    protected void initTable() {
        columnSupplier.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
        columnFirstname.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstname"));
        columnLastname.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastname"));
//        columnPayment.setCellValueFactory(new PropertyValueFactory<Customer, String>("quantite"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
    }

    @Override
    protected void printSelection() throws SQLException, EntityNotFoundException, FileNotFoundException {

    }

    @Override
    public List<Supplier> search() {
        try {
            List<Supplier> list = dao.getAll(999).stream().filter(a ->
                    a.getId().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getFirstname().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getLastname().toLowerCase().contains(search.getText().toLowerCase()) ||
                            a.getPhone().toLowerCase().contains(search.getText().toLowerCase())
            ).toList();
            for(AbstractEntity abstractEntity: list){
                abstractEntity.setRow(list.indexOf(abstractEntity)+1);
            }
            return list;
        } catch (SQLException | exception.EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void initComponent() {

    }
}
