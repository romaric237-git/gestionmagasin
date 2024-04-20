package com.neb.nebotools.model;

import com.neb.nebotools.dao.DaoFactory;
import lombok.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Builder
public class Log extends AbstractEntity {
    private String employee;
    private String type;
    private String entity;
    private String entityID;
    private Date date_log;

    public Employee getEmployee() throws SQLException {
        return DaoFactory.getEmployeeDao().find(employee);
    }
    public String getDateString() {
        return new SimpleDateFormat("EEE, dd MMMM yyyy").format(date_log);
    }
    @Override
    public void setEntity(AbstractEntity t) {

    }

    @AllArgsConstructor
    public enum Type{
        ADD("ADD"),UPDATE("UPDATE"),DELETE("DELETE"),LOGIN("LOGIN");
        String type;

    }
}
