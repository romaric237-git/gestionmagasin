package com.neb.nebotools.dao;

public class UserDaoFireBase extends DaoFireBase implements UserDao{

    @Override
    public boolean login(String mail, String password) {
        return false;
    }

    @Override
    public boolean getRole(String mail, String password) {
        return false;
    }
}
