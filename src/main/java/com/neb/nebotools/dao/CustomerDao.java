package com.neb.nebotools.dao;

import com.neb.nebotools.model.Customer;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends Dao<Customer> {
    public CustomerDao() throws SQLException {
        table = "customer";
        idS="cst";
    }

    @Override
    public Customer create(Customer obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `customer`(`id`, `firstname`, `lastname`, `phone`, `mail`) VALUES " +
                "(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, generateId());
        ps.setString(2, obj.getFirstname());
        ps.setString(3, obj.getLastname());
        ps.setString(4, obj.getPhone());
        ps.setString(5, obj.getMail());
        ps.executeUpdate();
        return getLast();
    }

    @Override
    public Customer update(Customer obj) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `customer` SET " +
                "`firstname`=?, " +
                "`lastname`=?, " +
                "`phone`=?, " +
                "`mail`=? "
                + "WHERE `id` = ?; ";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(5, obj.getId());
        ps.setString(1, obj.getFirstname());
        ps.setString(2, obj.getLastname());
        ps.setString(3, obj.getPhone());
        ps.setString(4, obj.getMail());
        ps.executeUpdate();
        return find(obj.getId());
    }

    @Override
    public Customer find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM `"+table+"` WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Customer(rs.getString("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("mail"),
                    rs.getString("phone"));
        }
        throw new EntityNotFoundException();
    }
}
