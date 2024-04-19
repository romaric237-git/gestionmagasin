package com.neb.nebotools.dao;

import com.neb.nebotools.model.Log;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDao extends Dao<Log>{
    public LogDao() throws SQLException {
        this.table = "log";
        this.idS = "log";
    }

    @Override
    public Log create(Log obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `product`(`id`, `name`, `brand`, `barcode`, `category`, `description`, `base_price`, `min_price`, `minimum`) VALUES "
                + "(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, generateId());
        ps.setString(2, obj.getName());
        ps.setString(3, obj.getBrand());
        ps.setString(4, obj.getBarcode());
        ps.setString(5, obj.getCategory());
        ps.setString(6, obj.getDescription());
        ps.setInt(7, obj.getBase_price());
        ps.setInt(8, obj.getMin_price());
        ps.setInt(9, obj.getMinimum());
        ps.executeUpdate();
        return getLast();
    }

    @Override
    public Log update(Log obj) throws SQLException, EntityNotFoundException {
        return null;
    }

    @Override
    public Log find(String id) throws SQLException, EntityNotFoundException {
        return null;
    }
}
