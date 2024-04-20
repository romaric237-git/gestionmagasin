package com.neb.nebotools.dao;

import com.neb.nebotools.model.Log;
import com.neb.nebotools.model.Lot;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LotDao extends Dao<Lot> {
    public LotDao() throws SQLException {
        this.table = "lot";
        this.idS = "lot";
    }

    @Override
    public Lot create(Lot obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `lot`(`id`, `product`, `num_lot`, `quantity`, `variant`, `delivery_date`, `expiration_date`, `supplier`, `price`, `status`) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        String id = generateId();
        ps.setString(1, id);
        ps.setString(2, obj.getProduct());
        ps.setString(3, obj.getNum_lot());
        ps.setInt(4, obj.getQuantity());
        ps.setString(5, obj.getVariant());
        ps.setDate(6, obj.getDelivery_date());
        ps.setDate(7, obj.getExpiration_date());
        ps.setString(8, obj.getSupplier());
        ps.setInt(9, obj.getPrice());
        ps.setInt(10, obj.getStatus());
        ps.executeUpdate();

        DaoFactory.getLogDao().create(Log.builder()
                .type("CREATE")
                .entity("LOT")
                .entityID(obj.getId())
                .build());
        return getLast();
    }

    @Override
    public Lot update(Lot obj) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `lot` SET "
                + "`product`=?, "
                + "`num_lot`=?, "
                + "`quantity`=?, "
                + "`variant`=?, "
                + "`delivery_date`=?, "
                + "`expiration_date`=?, "
                + "`supplier`=?, "
                + "`price`=? "
                + "WHERE `id` = ?; ";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(9, obj.getId());
        ps.setString(1, obj.getProduct());
        ps.setString(2, obj.getNum_lot());
        ps.setInt(3, obj.getQuantity());
        ps.setString(4, obj.getVariant());
        ps.setDate(5, obj.getDelivery_date());
        ps.setDate(6, obj.getExpiration_date());
        ps.setString(7, obj.getSupplier());
        ps.setInt(8, obj.getPrice());
        ps.executeUpdate();


        DaoFactory.getLogDao().create(Log.builder()
                .type("UPDATE")
                .entity("LOT")
                .entityID(obj.getId())
                .build());
        return find(obj.getId());
    }

    public Lot updateStatus(Lot obj) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `lot` SET "
                + "`status`=? "
                + "WHERE `id` = ? AND status = 0; ";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, obj.getStatus());
        ps.setString(2, obj.getId());
        ps.executeUpdate();
        return find(obj.getId());
    }

    @Override
    public Lot find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM `" + table + "` WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Lot(
                    rs.getString("id"),
                    rs.getString("product"),
                    rs.getString("num_lot"),
                    rs.getInt("quantity"),
                    rs.getString("variant"),
                    rs.getDate("delivery_date"),
                    rs.getDate("expiration_date"),
                    rs.getString("supplier"),
                    rs.getInt("price"),
                    rs.getInt("status"),
                    rs.getInt("is_actif"));
        }
        throw new EntityNotFoundException("Lot non trouvé");
    }

    public List<Lot> findByProduct(String product) throws SQLException, EntityNotFoundException {
        List<Lot> lots=new ArrayList<Lot>();
        String sql = "SELECT * FROM `" + table + "` WHERE `product` = ? and `is_actif` = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, product);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lots.add(new Lot(
                    rs.getString("id"),
                    rs.getString("product"),
                    rs.getString("num_lot"),
                    rs.getInt("quantity"),
                    rs.getString("variant"),
                    rs.getDate("delivery_date"),
                    rs.getDate("expiration_date"),
                    rs.getString("supplier"),
                    rs.getInt("price"),
                    rs.getInt("status"),
                    rs.getInt("is_actif")));
        }
        return lots;
    }
}
