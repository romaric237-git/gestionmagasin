package com.neb.nebotools.controller.page.employee;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.AbstractEntity;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Employee;
import exception.EntityNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeListController extends ControllerListAbstract<Employee> {

    @FXML
    private TableColumn<Employee, String> columnEmployee;

    @FXML
    private TableColumn<Employee, String> columnFirstname;

    @FXML
    private TableColumn<Employee, String> columnLastname;

    @FXML
    private TableColumn<Employee, Integer> columnOld;

    @FXML
    private TableColumn<Employee, String> columnPhone;

    @FXML
    private TableColumn<Employee, String> columnSex;

    @FXML
    private ComboBox<String> filterSex;

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getEmployeeDao();
    }

    @Override
    protected void initTable() {
        columnEmployee.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        columnFirstname.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstname"));
        columnLastname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastname"));
        columnOld.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("old"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        columnSex.setCellValueFactory(new PropertyValueFactory<Employee, String>("sexe"));
    }

    @Override
    protected void printSelection() throws SQLException, EntityNotFoundException, FileNotFoundException {

    }

    @Override
    public List<Employee> search() {
        try {
            List<Employee> list = dao.getAll(999).stream().filter(a ->
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
        filterSex.getItems().addAll("Tout", "Homme", "Femme");
        filterSex.getSelectionModel().selectFirst();
        filterSex.setOnAction(a->searchInTable());
    }
}
