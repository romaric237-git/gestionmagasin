package com.neb.nebotools.controller.page.invoice;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Invoice;

import java.sql.SQLException;
import java.util.List;

public class InvoiceListController extends ControllerListAbstract<Invoice> {
    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getInvoiceDao();
    }

    @Override
    protected void initTable() {

    }

    @Override
    public List<Invoice> search() {
        return null;
    }

    @Override
    protected void initComponent() throws SQLException {

    }
}
