package com.neb.nebotools.dao;

import com.neb.nebotools.model.AbstractEntity;
import com.neb.nebotools.model.Log;
import com.neb.nebotools.model.Product;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends Dao<Product> {

    public ProductDao() throws SQLException {
        super();
        table = "product";
        idS = "pro";
    }

    @Override
    public Product create(Product obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `product`(`id`, `name`, `brand`, `barcode`, `category`, `description`, `base_price`, `min_price`, `minimum`) VALUES "
                + "(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        String id = generateId();
        ps.setString(1, id);
        ps.setString(2, obj.getName());
        ps.setString(3, obj.getBrand());
        ps.setString(4, obj.getBarcode());
        ps.setString(5, obj.getCategory());
        ps.setString(6, obj.getDescription());
        ps.setInt(7, obj.getBase_price());
        ps.setInt(8, obj.getMin_price());
        ps.setInt(9, obj.getMinimum());
        ps.executeUpdate();

        DaoFactory.getLogDao().create(Log.builder()
                .type("CREATE")
                .entity("PRODUCT")
                .entityID(obj.getId())
                .build());
        return getLast();
    }

    @Override
    public Product update(Product obj) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `product` SET "
                + "`name`=?, "
                + "`brand`=?, "
                + "`barcode`=?, "
                + "`category`=?, "
                + "`description`=?, "
                + "`base_price`=?, "
                + "`min_price`=?, "
                + "`minimum`=? "
                + "WHERE `id` = ?; ";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(9, obj.getId());
        ps.setString(1, obj.getName());
        ps.setString(2, obj.getBrand());
        ps.setString(3, obj.getBarcode());
        ps.setString(4, obj.getCategory());
        ps.setString(5, obj.getDescription());
        ps.setInt(6, obj.getBase_price());
        ps.setInt(7, obj.getMin_price());
        ps.setInt(8, obj.getMinimum());
        ps.executeUpdate();

        DaoFactory.getLogDao().create(Log.builder()
                .type("UPDATE")
                .entity("PRODUCT")
                .entityID(obj.getId())
                .build());
        return find(obj.getId());
    }

    @Override
    public Product find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT p.*, COUNT(l.id) AS stock, SUM(l.quantity) as quantity " +
                "FROM product p, lot l " +
                "WHERE l.product = p.id " +
                "AND p.id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Product p = Product.builder()
                    .name(rs.getString("name"))
                    .brand(rs.getString("brand"))
                    .barcode(rs.getString("barcode"))
                    .category(rs.getString("category"))
                    .description(rs.getString("description"))
                    .base_price(rs.getInt("base_price"))
                    .min_price(rs.getInt("min_price"))
                    .stock(rs.getInt("stock"))
                    .quantity(rs.getInt("quantity"))
                    .minimum(rs.getInt("minimum"))
                    .build();
            p.setId(id);
            p.setMinimum(rs.getInt("minimum"));

            return p;
        }
        throw new EntityNotFoundException("Produit non trouvé");
    }

    public List<String> getCategory() throws SQLException {
        List<String> category = new ArrayList<String>();
        String sql = "SELECT category FROM `" + table + "` ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            category.add(rs.getString("category"));
        }
        return category;
    }

}
