package com.neb.nebotools.dao;

public class DaoSQLFactory implements AbstractDaoFactory{

    private UserDaoSQL userDaoSQL;
    @Override
    public UserDao getUserDao() {
        if(userDaoSQL == null) userDaoSQL = new UserDaoSQL();
        return userDaoSQL;
    }
}
