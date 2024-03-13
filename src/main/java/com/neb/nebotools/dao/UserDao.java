package com.neb.nebotools.dao;

public interface UserDao {

    boolean login(String mail, String password);
    boolean getRole(String mail, String password);
}
