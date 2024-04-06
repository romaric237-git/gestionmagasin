package com.neb.nebotools.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neb.nebotools.model.User;
import exception.EntityNotFoundException;

public class UserDao extends Dao<User> {

	public UserDao() throws SQLException {
		super();
		table = "user";
		idS="usr";
	}

	@Override
	public User create(User obj) throws SQLException, EntityNotFoundException {
		String sql = "INSERT INTO `user`(`id`, `login`, `firstname`, `lastname`, `password`, `mail`, `phone`, `role`, `nb_person`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, generateId());
		ps.setString(2, obj.getLogin());
		ps.setString(3, obj.getFirstname());
		ps.setString(4, obj.getLastname());
		ps.setString(5, obj.getPassword());
		ps.setString(6, obj.getMail());
		ps.setString(7, obj.getPhone());
		ps.setInt(8, obj.getRole().getId());
		ps.setInt(9, obj.getNbPerson());
		ps.executeUpdate();
		return getLast();
	}

	@Override
	public User update(User obj) throws SQLException, EntityNotFoundException {
		String sql = "UPDATE `user` SET "
				+ "`login`=?,"
				+ "`firstname`=?,"
				+ "`lastname`=?,"
				+ "`password`=?,"
				+ "`mail`=?,"
				+ "`phone`=?,"
				+ "`role`=?,"
				+ "`nb_person`=? "
				+ "WHERE `id` = ?; ";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, obj.getLogin());
		ps.setString(2, obj.getFirstname());
		ps.setString(3, obj.getLastname());
		ps.setString(4, obj.getPassword());
		ps.setString(5, obj.getMail());
		ps.setString(6, obj.getPhone());
		ps.setInt(7, obj.getRole().getId());
		ps.setInt(8, obj.getNbPerson());
		ps.setString(9, obj.getId());
		ps.executeUpdate();
		return find(obj.getId());
	}

	@Override
	public User find(String id) throws SQLException, EntityNotFoundException {
		String sql = "SELECT * FROM `"+table+"` WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new User(new User.UserBuilder().id(rs.getString("id"))
					.login(rs.getString("login"))
					.firstname(rs.getString("firstname"))
					.lastname(rs.getString("lastname"))
					.password(rs.getString("password"))
					.mail(rs.getString("mail"))
					.phone(rs.getString("phone"))
					.role(rs.getInt("role"))
					.nbPerson(rs.getInt("nb_person")));
		}
		throw new EntityNotFoundException("Utilisateur non trouvé");
	}


	public User login(String mail, String password) throws SQLException, EntityNotFoundException {
		String sql = "SELECT `id` FROM `user` WHERE (`login` = ? OR `mail` = ?) AND `password` = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, mail);
		ps.setString(2, mail);
		ps.setString(3, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return find(rs.getString(1));
		}
		return null;
	}

}
