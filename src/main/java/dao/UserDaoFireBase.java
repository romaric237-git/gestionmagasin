package dao;

import java.util.List;

public class UserDaoFireBase extends DaoFireBase implements UserDao{

    @Override
    public boolean login(String mail, String password) {
        return false;
    }
}
