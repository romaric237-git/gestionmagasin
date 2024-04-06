package com.neb.nebotools.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.neb.nebotools.model.AbstractEntity;
import com.neb.nebotools.sql.BdConnexion;
import exception.EntityNotFoundException;



public abstract class Dao <T>{

	protected String table;
	protected String idS;
	protected Connection con = null;
	
	public Dao() throws SQLException{
		super();
		con = BdConnexion.getInstance();
	}

	public abstract T create(T obj) throws SQLException, EntityNotFoundException;
	
	public abstract T update(T obj) throws SQLException, EntityNotFoundException;

	public abstract T find(String id) throws SQLException, EntityNotFoundException;

//	public String genera
	public T getLast() throws SQLException, EntityNotFoundException {
		String sql = "SELECT `id` FROM " + table 
				+ " ORDER BY `id` desc LIMIT 1";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) return find(rs.getString("id"));
		return null;
	}
	public int delete(String id) throws SQLException{
		String sql = "UPDATE `"+table+" SET `is_actif` = 0 WHERE `id` = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, id);
		return pst.executeUpdate();
	};
	
	public ArrayList<T> getAll(int limit) throws SQLException, EntityNotFoundException{
		con.close();
		con = BdConnexion.getInstance();
		List<T> array = new ArrayList<T>();
		String sql = "SELECT `id` FROM " + table + " LIMIT ?;";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, 10000);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
				array.add(find(rs.getString("id")));
				try {
				((AbstractEntity) array.get(array.size()-1)).setRow(array.size());
				}catch(NullPointerException n) {
					System.out.println(n.getMessage());
				}
		}
		return (ArrayList<T>) array;
	}
	
	public int getSize() throws SQLException {
		String sql = "SELECT count(*) as nb FROM "+table+";";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next())
			return rs.getInt("nb");
		return 0;
	}
	public String generateId() throws SQLException {
		String id = idS+"-";
		id+= String.valueOf(LocalDate.now().getYear()).substring(1, 4) + "-";
		id+= String.format("%03d", getSize()+1);
		return id;
	}
	
}
