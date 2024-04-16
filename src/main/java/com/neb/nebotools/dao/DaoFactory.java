package com.neb.nebotools.dao;

import java.sql.SQLException;

public abstract class DaoFactory {
	static EmployeeDao employeeDao;
	static ProductDao productDao;
	static CustomerDao customerDao;
	static SupplierDao supplierDao;
	static LotDao lotDao;
	static InvoiceDao invoiceDao;
	static LineInvoiceDao lineInvoiceDao;

	public static EmployeeDao getEmployeeDao() throws SQLException{
		if(employeeDao==null) employeeDao = new EmployeeDao();
		return employeeDao;
	}

	public static ProductDao getProductDao() throws SQLException{
		if(productDao==null) productDao = new ProductDao();
		return productDao;
	}

	public static CustomerDao getCustomerDao() throws SQLException{
		if(customerDao==null) customerDao = new CustomerDao();
		return customerDao;
	}

	public static SupplierDao getSupplierDao() throws SQLException{
		if(supplierDao==null) supplierDao = new SupplierDao();
		return supplierDao;
	}

	public static LotDao getLotDao() throws SQLException{
		if(lotDao==null) lotDao = new LotDao();
		return lotDao;
	}

	public static InvoiceDao getInvoiceDao() throws SQLException{
		if(invoiceDao==null) invoiceDao = new InvoiceDao();
		return invoiceDao;
	}

	public static LineInvoiceDao getLineInvoiceDao() throws SQLException{
		if(lineInvoiceDao==null) lineInvoiceDao = new LineInvoiceDao();
		return lineInvoiceDao;
	}

}