package com.neb.nebotools.dao;

import com.neb.nebotools.model.Employee;
import com.neb.nebotools.model.Product;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends Dao<Product> {

	public ProductDao() throws SQLException {
		super();
		table = "product";
		idS="pro";
	}

	@Override
	public Product create(Product obj) throws SQLException, EntityNotFoundException {
		String sql = "INSERT INTO `product`(`id`, `name`, `barcode`, `category`, `description`, `base_price`, `min_price`) VALUES "
				+ "(?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, generateId());
		ps.setString(2, obj.getName());
		ps.setString(3, obj.getBarcode());
		ps.setString(4, obj.getCategory());
		ps.setString(5, obj.getDescription());
		ps.setInt(6, obj.getBase_price());
		ps.setInt(7, obj.getMin_price());
		ps.executeUpdate();
		return getLast();
	}

	@Override
	public Product update(Product obj) throws SQLException, EntityNotFoundException {
		String sql = "UPDATE `product` SET "
				+ "`name`=?, "
				+ "`barcode`=?, "
				+ "`category`=?, "
				+ "`description`=?, "
				+ "`base_price`=?, "
				+ "`min_price`=? "
				+ "WHERE `id` = ?; ";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(7, generateId());
		ps.setString(1, obj.getName());
		ps.setString(2, obj.getBarcode());
		ps.setString(3, obj.getCategory());
		ps.setString(4, obj.getDescription());
		ps.setInt(5, obj.getBase_price());
		ps.setInt(6, obj.getMin_price());
		ps.executeUpdate();
		return find(obj.getId());
	}

	@Override
	public Product find(String id) throws SQLException, EntityNotFoundException {
		String sql = "SELECT * FROM `"+table+"` WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Product(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("barcode"),
					rs.getString("category"),
					rs.getString("description"),
					rs.getInt("base_price"),
					rs.getInt("min_price"),
					rs.getInt("is_actif"));
		}
		throw new EntityNotFoundException("Utilisateur non trouvé");
	}

}
