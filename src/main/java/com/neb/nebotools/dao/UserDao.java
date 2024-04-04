package com.neb.nebotools.dao;

import com.neb.nebotools.model.User;

public interface UserDao extends Dao<User> {

    boolean login(String mail, String password);
    boolean getRole(String mail, String password);
}
