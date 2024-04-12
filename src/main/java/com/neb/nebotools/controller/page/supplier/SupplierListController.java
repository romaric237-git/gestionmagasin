package com.neb.nebotools.controller.page.supplier;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierListController extends ControllerListAbstract<Supplier> {
    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getSupplierDao();
    }

    @Override
    protected void initTable() {

    }

    @Override
    public List<Supplier> search() {
        return null;
    }

    @Override
    protected void initComponent() {

    }
}
