package com.neb.nebotools.dao;

import com.neb.nebotools.model.Employee;
import com.neb.nebotools.model.Log;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao extends Dao<Employee> {

    public EmployeeDao() throws SQLException {
        super();
        table = "employee";
        idS = "emp";
    }

    @Override
    public Employee create(Employee obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `employee`(`id`, `login`, `firstname`, `lastname`, `password`, `mail`, `phone`, `birth`, `sex`) VALUES "
                + "(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        String id = generateId();
        ps.setString(1, id);
        ps.setString(2, obj.getLogin());
        ps.setString(3, obj.getFirstname());
        ps.setString(4, obj.getLastname());
        ps.setString(5, obj.getPassword());
        ps.setString(6, obj.getMail());
        ps.setString(7, obj.getPhone());
        ps.setDate(8, obj.getBirth());
        ps.setInt(9, obj.getSex());
        ps.executeUpdate();
        DaoFactory.getLogDao().create(Log.builder()
                .type("CREATE")
                .entity("EMPLOYEE")
                .entityID(id)
                .build());
        return getLast();
    }

    @Override
    public Employee update(Employee obj) throws SQLException, EntityNotFoundException {
        boolean passwordUpdate = !find(obj.getId()).getPassword().equals(obj.getPassword());
        String sql = "UPDATE `employee` SET "
                + "`login`=?,"
                + "`firstname`=?,"
                + "`lastname`=?,"
                + "`password`=?,"
                + "`mail`=?,"
                + "`phone`=?,"
                + "`birth`=?,"
                + "`sex`=? "
                + "WHERE `id` = ?; ";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, obj.getLogin());
        ps.setString(2, obj.getFirstname());
        ps.setString(3, obj.getLastname());
        ps.setString(4, obj.getPassword());
        ps.setString(5, obj.getMail());
        ps.setString(6, obj.getPhone());
        ps.setDate(7, obj.getBirth());
        ps.setInt(8, obj.getSex());
        ps.setString(9, obj.getId());
        ps.executeUpdate();

        if (passwordUpdate) {
            sql = "UPDATE `employee` SET "
                    + "`pin`=000000 "
                    + "WHERE `id` = ?; ";
            ps = con.prepareStatement(sql);
            ps.setString(1, obj.getId());
            ps.executeUpdate();
        }

        DaoFactory.getLogDao().create(Log.builder()
                .type("UPDATE")
                .entity("EMPLOYEE")
                .entityID(obj.getId())
                .build());
        return find(obj.getId());
    }

    @Override
    public Employee find(String id) throws SQLException {
        String sql = "SELECT * FROM `" + table + "` WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Employee(new Employee.UserBuilder().id(rs.getString("id"))
                    .login(rs.getString("login"))
                    .firstname(rs.getString("firstname"))
                    .lastname(rs.getString("lastname"))
                    .password(rs.getString("password"))
                    .mail(rs.getString("mail"))
                    .phone(rs.getString("phone"))
                    .birth(rs.getDate("birth"))
                    .sex(rs.getInt("sex"))
                    .role(rs.getInt("role")));
        }
        return null;
    }


    public Employee login(String mail, String password) throws SQLException, EntityNotFoundException {
        String sql = "SELECT `id` FROM `employee` WHERE (`login` = ? OR `mail` = ?) AND `password` = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, mail);
        ps.setString(2, mail);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return find(rs.getString(1));
        }
        return null;
    }


    public Employee loginWithPin(int pin) throws SQLException, EntityNotFoundException {
        String sql = "SELECT `id` FROM `employee` WHERE `id` = ? AND `pin` = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Employee.getEmployee().getId());
        ps.setInt(2, pin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Employee e = find(rs.getString(1));
            DaoFactory.getLogDao().create(Log.builder()
                    .type("LOGIN")
                    .entity("EMPLOYEE")
                    .entityID(Employee.getEmployee().getId())
                    .build());
            return e;
        }
        return null;
    }
    public Employee update(int pin) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `employee` SET "
                + "`pin`=? "
                + "WHERE `id` = ?; ";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, pin);
        ps.setString(2, Employee.getEmployee().getId());
        ps.executeUpdate();

        DaoFactory.getLogDao().create(Log.builder()
                .type("UPDATE PIN CODE")
                .entity("EMPLOYEE")
                .entityID(Employee.getEmployee().getId())
                .build());
        return find(Employee.getEmployee().getId());
    }
    public Employee update(String password) throws SQLException, EntityNotFoundException {
        String sql = "UPDATE `employee` SET "
                + "`pin`=000000, " +
                "`password`=?"
                + "WHERE `id` = ?; ";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, password);
        ps.setString(2, Employee.getEmployee().getId());
        ps.executeUpdate();

        DaoFactory.getLogDao().create(Log.builder()
                .type("UPDATE PASSWORD CODE")
                .entity("EMPLOYEE")
                .entityID(Employee.getEmployee().getId())
                .build());
        return find(Employee.getEmployee().getId());
    }

}
