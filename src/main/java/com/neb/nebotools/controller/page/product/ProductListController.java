package com.neb.nebotools.controller.page.product;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.AbstractEntity;
import com.neb.nebotools.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ProductListController extends ControllerListAbstract<Product> {


    @FXML
    private TableColumn<Product, String> columnBarcode;

    @FXML
    private TableColumn<Product, String> columnCategory;

    @FXML
    private TableColumn<Product, Integer> columnPrice;

    @FXML
    private TableColumn<Product, String> columnProduct;

    @FXML
    private TableColumn<Product, Integer> columnQuantity;

    @FXML
    private TableColumn<Product, String> columnStatus;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, String> columnBrand;

    @FXML
    private TableColumn<Product, Integer> columnStock;

    @Override
    public void refresh() {

    }

    @Override
    public List<Product> search() {
        try {
            List<Product> list = dao.getAll(999).stream().filter(a ->
                    a.getName().toLowerCase().contains(search.getText().toLowerCase())
            ).toList();
            for (AbstractEntity abstractEntity : list) {
                abstractEntity.setRow(list.indexOf(abstractEntity) + 1);
            }
            return list;
        } catch (SQLException | exception.EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getProductDao();
    }

    @Override
    protected void initTable() {
        columnProduct.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("base_price"));
        columnBarcode.setCellValueFactory(new PropertyValueFactory<Product, String>("barcode"));
        columnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brand"));
        columnStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Product, String>("status"));
    }

    @Override
    protected void initComponent() {

    }
}
