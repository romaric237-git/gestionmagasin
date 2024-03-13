package com.neb.nebotools.dao;

public class DaoFireBaseFactory implements AbstractDaoFactory{
    private UserDaoFireBase userDaoFireBase;
    @Override
    public UserDao getUserDao() {
        if(userDaoFireBase==null) userDaoFireBase = new UserDaoFireBase();
        return userDaoFireBase;
    }
}
