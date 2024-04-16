package com.neb.nebotools.dao;

import com.neb.nebotools.model.Invoice;
import com.neb.nebotools.model.InvoiceLine;
import com.neb.nebotools.model.Lot;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineInvoiceDao extends Dao<InvoiceLine> {
    public LineInvoiceDao() throws SQLException {
        table="line_invoice";
        idS="lns";
    }

    @Override
    public InvoiceLine create(InvoiceLine obj) throws SQLException, EntityNotFoundException {
        System.out.println("ttoto");
        String sql = "INSERT INTO `line_invoice`(`id`, `invoice`, `product`, `quantity`, `price`) VALUES" +
                "(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, generateId());
        System.out.println(obj.getInvoiceString());
        ps.setString(2, obj.getInvoiceString());
        ps.setString(3, obj.getProduct().getId());
        ps.setInt(4, obj.getQuantity());
        ps.setInt(5, obj.getPrice());
        ps.executeUpdate();
        System.out.println(DaoFactory.getLotDao().findByProduct(obj.getProduct().getId()).size()+":::");
        for(Lot lot: DaoFactory.getLotDao().findByProduct(obj.getProduct().getId())){
            if(lot.getQuantity()>=obj.getQuantity()) {
                lot.setQuantity(lot.getQuantity() - obj.getQuantity());
                DaoFactory.getLotDao().update(lot);
                break;
            }else{
                obj.setQuantity(obj.getQuantity()-lot.getQuantity());
                lot.setQuantity(0);
                Lot l = DaoFactory.getLotDao().update(lot);
                DaoFactory.getLotDao().delete(lot.getId());
                System.out.println(l.getId());
                System.out.println(l.getQuantity());
            }
        }
        return getLast();
    }

    @Override
    public InvoiceLine update(InvoiceLine obj) throws SQLException, EntityNotFoundException {
        return null;
    }

    @Override
    public InvoiceLine find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM line_invoice " +
                "WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new InvoiceLine(
                    rs.getString("id"),
                    rs.getString("invoice"),
                    rs.getString("product"),
                    rs.getInt("quantity"),
                    rs.getInt("price"),
                    rs.getInt("is_actif"));
        }
        throw new EntityNotFoundException("Ligne Vente non trouvé");
    }

    public List<InvoiceLine> findByInvoice(String invoice) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM line_invoice " +
                "WHERE invoice = ?";
        List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, invoice);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
            invoiceLines.add(find(rs.getString("id")));
        return invoiceLines;
    }
}
