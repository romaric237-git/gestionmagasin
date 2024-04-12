package com.neb.nebotools.dao;

import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Supplier;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDao extends Dao<Supplier> {
    public SupplierDao() throws SQLException {
        super();
        table = "supplier";
        idS = "sup";
    }

    @Override
    public Supplier create(Supplier obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `supplier`(`id`, `firstname`, `lastname`, `phone`, `mail`, `description`) VALUES " +
                "(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, generateId());
        ps.setString(2, obj.getFirstname());
        ps.setString(3, obj.getLastname());
        ps.setString(4, obj.getPhone());
        ps.setString(5, obj.getMail());
        ps.setString(6, obj.getDescription());
        ps.executeUpdate();
        return getLast();
    }

    @Override
    public Supplier update(Supplier obj) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `supplier` SET " +
                "`firstname`=?, " +
                "`lastname`=?, " +
                "`phone`=?, " +
                "`mail`=? " +
                "`description`=? "
                + "WHERE `id` = ?; ";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(6, obj.getId());
        ps.setString(1, obj.getFirstname());
        ps.setString(2, obj.getLastname());
        ps.setString(3, obj.getPhone());
        ps.setString(4, obj.getMail());
        ps.setString(5, obj.getDescription());
        ps.executeUpdate();
        return find(obj.getId());
    }

    @Override
    public Supplier find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM `" + table + "` WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Supplier(rs.getString("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("mail"),
                    rs.getString("phone"),
                    rs.getString("description"));
        }
        throw new EntityNotFoundException();
    }
}
