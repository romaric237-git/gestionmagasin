package com.neb.nebotools.controller.page.log;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Employee;
import com.neb.nebotools.model.Log;
import exception.EntityNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class LogController extends ControllerListAbstract<Log> {


    @FXML
    private TableColumn<Log, String> columnDate;

    @FXML
    private TableColumn<Log, Employee> columnEmploye;

    @FXML
    private TableColumn<Log, String> columnEntity;

    @FXML
    private TableColumn<Log, String> columnEntityId;

    @FXML
    private TableColumn<Log, String> columnLog;

    @FXML
    private TableColumn<Log, String> columnLogin;

    @FXML
    private TableColumn<Log, String> columnType;

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getLogDao();
    }

    @Override
    protected void initTable() {

    }

    @Override
    public List<Log> search() {
        return null;
    }

    @Override
    protected void initComponent() throws SQLException, EntityNotFoundException {
        columnDate.setCellFactory(new PropertyValueFactory<Log, String>("dateString"));
columnEmploye;
columnEntity;
columnEntityId;
columnLog;
columnLogin;
columnType;
    }
}
