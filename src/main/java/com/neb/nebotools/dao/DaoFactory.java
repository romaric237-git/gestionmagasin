package com.neb.nebotools.dao;

import java.sql.SQLException;

public abstract class DaoFactory {
	static EmployeeDao userDao;
	static ProductDao productDao;

	public static EmployeeDao getEmployeeDao() throws SQLException{
		if(userDao==null) userDao = new EmployeeDao();
		return userDao;
	}

	public static ProductDao getProductDao() throws SQLException{
		if(productDao==null) productDao = new ProductDao();
		return productDao;
	}

}