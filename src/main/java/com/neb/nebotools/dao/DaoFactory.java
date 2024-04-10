package com.neb.nebotools.dao;

import java.sql.SQLException;

public abstract class DaoFactory {
	static EmployeeDao employeeDao;
	static ProductDao productDao;

	public static EmployeeDao getEmployeeDao() throws SQLException{
		if(employeeDao==null) employeeDao = new EmployeeDao();
		return employeeDao;
	}

	public static ProductDao getProductDao() throws SQLException{
		if(productDao==null) productDao = new ProductDao();
		return productDao;
	}

}