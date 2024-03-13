package com.neb.nebotools.dao;

public class DaoFactory {

    /* 0 For MySQL DAO*/
    private static int typeDao = 0;

    private static DaoSQLFactory daoSQL;
    private static DaoFireBaseFactory daoFireBase;

    private static AbstractDaoFactory verifyDao(){
        if(typeDao == 0 ) {
            if(daoSQL == null) daoSQL = new DaoSQLFactory();
            return daoSQL;
        }

        if(daoFireBase == null) daoFireBase = new DaoFireBaseFactory();

        return daoFireBase;

    }
    public static UserDao getUserDao(){
        return verifyDao().getUserDao();
    }
}
