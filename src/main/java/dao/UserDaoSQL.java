package dao;

import java.util.List;

public class UserDaoSQL extends DaoSQL implements UserDao {

    @Override
    public boolean login(String mail, String password) {
        return true;
    }
}
