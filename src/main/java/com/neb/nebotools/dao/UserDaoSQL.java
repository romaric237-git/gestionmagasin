package com.neb.nebotools.dao;

public class UserDaoSQL extends DaoSQL implements UserDao {

    @Override
    public boolean login(String mail, String password) {
        return true;
    }

    @Override
    public boolean getRole(String mail, String password) {
        return false;
    }
}
