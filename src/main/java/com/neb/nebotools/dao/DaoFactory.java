package com.neb.nebotools.dao;

import java.sql.SQLException;

public abstract class DaoFactory {
	static EmployeeDao userDao;

	public static EmployeeDao getEmployeeDao() throws SQLException{
		if(userDao==null) userDao = new EmployeeDao();
		return userDao;
	}

}