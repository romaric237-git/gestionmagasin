package com.neb.nebotools.dao;

import java.sql.SQLException;

public abstract class DaoFactory {
	static UserDao userDao;

	public static UserDao getUserDao() throws SQLException{
		if(userDao==null) userDao = new UserDao();
		return userDao;
	}

}