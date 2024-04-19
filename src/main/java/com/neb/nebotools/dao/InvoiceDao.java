package com.neb.nebotools.dao;

import com.neb.nebotools.model.*;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceDao extends Dao<Invoice> {
    public InvoiceDao() throws SQLException {
        table = "invoice";
        idS = "inv";
    }

    @Override
    public Invoice create(Invoice obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `invoice`(`id`, `employee`, `customer`, `price`) VALUES " +
                "(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, obj.getId());
        ps.setString(2, Employee.getEmployee().getId());
        ps.setString(3, obj.getCustomer().getId());
        ps.setInt(4, obj.getPrice());
        ps.executeUpdate();
        for (InvoiceLine invoiceLine: obj.getInvoiceLineList())
            DaoFactory.getLineInvoiceDao().create(invoiceLine);

        return getLast();
    }

    @Override
    public Invoice update(Invoice obj) throws SQLException, EntityNotFoundException {
        return null;
    }

    @Override
    public Invoice find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM invoice " +
                "WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Invoice invoice = new Invoice(
                    rs.getString("id"),
                    rs.getString("customer"),
                    rs.getDate("created_date"),
                    rs.getInt("price"),
                    rs.getString("employee"),
                    rs.getInt("is_actif"));

            invoice.setInvoiceLineList(DaoFactory.getLineInvoiceDao().findByInvoice(id));
            return invoice;

        }
        throw new EntityNotFoundException("Vente non trouvé");
    }
}
