package com.neb.nebotools.controller.page.log;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Log;
import exception.EntityNotFoundException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.sql.SQLException;
import java.util.List;

public class LogController extends ControllerListAbstract<Log> {


    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnEmploye;

    @FXML
    private TableColumn<?, ?> columnEntity;

    @FXML
    private TableColumn<?, ?> columnEntityId;

    @FXML
    private TableColumn<?, ?> columnLog;

    @FXML
    private TableColumn<?, ?> columnLogin;

    @FXML
    private TableColumn<?, ?> columnType;

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

    }
}
