package com.neb.nebotools.dao;

import com.neb.nebotools.model.Employee;
import com.neb.nebotools.model.Log;
import com.neb.nebotools.model.Product;
import exception.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDao extends Dao<Log>{
    public LogDao() throws SQLException {
        this.table = "log";
        this.idS = "log";
    }

    @Override
    public Log create(Log obj) throws SQLException, EntityNotFoundException {
        String sql = "INSERT INTO `log`(`id`, `employee`, `type`, `entity`, `entity_id`) VALUES "
                + "(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, generateId());
        ps.setString(2, Employee.getEmployee().getId());
        ps.setString(3, obj.toString());
        ps.setString(4, obj.getEntity());
        ps.setString(5, obj.getEntityID());
        ps.executeUpdate();
        return getLast();
    }

    @Override
    public Log update(Log obj) throws SQLException, EntityNotFoundException {
        return null;
    }

    @Override
    public Log find(String id) throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM `log` " +
                " WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Log l = Log.builder()
                    .employee(rs.getString("employee"))
                    .type(rs.getString("type"))
                    .entity(rs.getString("entity"))
                    .entityID(rs.getString("entity_id"))
                    .date_log(rs.getDate("date_log"))
                    .build();
            l.setId(id);

            return l;
        }
        throw new EntityNotFoundException("Log non trouvé");
    }
}
