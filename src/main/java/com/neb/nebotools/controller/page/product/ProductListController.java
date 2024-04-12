package com.neb.nebotools.controller.page.product;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Product;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductListController extends ControllerListAbstract<Product> {

    @Override
    public void refresh() {

    }

    @Override
    public List<Product> search() {
        return null;
    }

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getProductDao();
    }

    @Override
    protected void initTable() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    protected void initComponent() {

    }
}
