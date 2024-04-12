package com.neb.nebotools.controller.page.employee;

import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeListController extends ControllerListAbstract<Employee> {
    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getEmployeeDao();
    }

    @Override
    protected void initTable() {

    }

    @Override
    public List<Employee> search() {
        return null;
    }

    @Override
    protected void initComponent() {

    }
}
