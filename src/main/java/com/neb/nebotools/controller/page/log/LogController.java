package com.neb.nebotools.controller.page.log;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Log;
import exception.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class LogController extends ControllerListAbstract<Log> {
    @Override
    protected void setDao() throws SQLException {
dao = DaoFactory.ge
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
