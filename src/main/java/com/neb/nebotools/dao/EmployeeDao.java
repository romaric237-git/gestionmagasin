package com.neb.nebotools.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neb.nebotools.model.Employee;
import exception.EntityNotFoundException;

public class EmployeeDao extends Dao<Employee> {

	public EmployeeDao() throws SQLException {
		super();
		table = "employee";
		idS="emp";
	}

	@Override
	public Employee create(Employee obj) throws SQLException, EntityNotFoundException {
		String sql = "INSERT INTO `employee`(`id`, `login`, `firstname`, `lastname`, `password`, `mail`, `phone`, `birth`, `sex`) VALUES "
				+ "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, generateId());
		ps.setString(2, obj.getLogin());
		ps.setString(3, obj.getFirstname());
		ps.setString(4, obj.getLastname());
		ps.setString(5, obj.getPassword());
		ps.setString(6, obj.getMail());
		ps.setString(7, obj.getPhone());
		ps.setDate(8, obj.getBirth());
		ps.setInt(9,obj.getSex());
		ps.executeUpdate();
		return getLast();
	}

	@Override
	public Employee update(Employee obj) throws SQLException, EntityNotFoundException {
		String sql = "UPDATE `employee` SET "
				+ "`login`=?,"
				+ "`firstname`=?,"
				+ "`lastname`=?,"
				+ "`password`=?,"
				+ "`mail`=?,"
				+ "`phone`=?,"
				+ "`birth`=?,"
				+ "`sex`=? "
				+ "WHERE `id` = ?; ";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, obj.getLogin());
		ps.setString(2, obj.getFirstname());
		ps.setString(3, obj.getLastname());
		ps.setString(4, obj.getPassword());
		ps.setString(5, obj.getMail());
		ps.setString(6, obj.getPhone());
		ps.setDate(7, obj.getBirth());
		ps.setInt(8, obj.getSex());
		ps.setString(9, obj.getId());
		System.out.println(obj.getId() + ":::" +obj.getLogin());
		ps.executeUpdate();
		return find(obj.getId());
	}

	@Override
	public Employee find(String id) throws SQLException {
		String sql = "SELECT * FROM `"+table+"` WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Employee(new Employee.UserBuilder().id(rs.getString("id"))
					.login(rs.getString("login"))
					.firstname(rs.getString("firstname"))
					.lastname(rs.getString("lastname"))
					.password(rs.getString("password"))
					.mail(rs.getString("mail"))
					.phone(rs.getString("phone"))
					.birth(rs.getDate("birth"))
					.sex(rs.getInt("sex"))
					.role(rs.getInt("role")));
		}
		return null;
	}


	public Employee login(String mail, String password) throws SQLException, EntityNotFoundException {
		String sql = "SELECT `id` FROM `employee` WHERE (`login` = ? OR `mail` = ?) AND `password` = ?";
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
