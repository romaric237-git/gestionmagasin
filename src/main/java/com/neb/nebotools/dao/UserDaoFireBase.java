package com.neb.nebotools.dao;

import com.neb.nebotools.model.User;

public class UserDaoFireBase extends DaoFireBase<User> implements UserDao {

    @Override
    public boolean login(String mail, String password) {
        return false;
    }

    @Override
    public boolean getRole(String mail, String password) {
        return false;
    }
}
