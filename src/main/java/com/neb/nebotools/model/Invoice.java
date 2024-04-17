package com.neb.nebotools.model;

import com.neb.nebotools.dao.DaoFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Invoice extends AbstractEntity {

    private String customer;
    private Date date;
    private int price;
    private String employee;

    private List<InvoiceLine> invoiceLineList = new ArrayList<InvoiceLine>();

    public Invoice(String id, String customer, Date date, int price, String employee, int is_actif) {
        super(id, is_actif);
        this.customer = customer;
        this.date = date;
        this.price = price;
        this.employee = employee;
    }

    public Customer getCustomer() throws SQLException, exception.EntityNotFoundException {
        return DaoFactory.getCustomerDao().find(customer);
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public Employee getEmployee() throws SQLException {
        return DaoFactory.getEmployeeDao().find(employee);
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDateString() {
        return new SimpleDateFormat("EEE, dd MMMM yyyy").format(date);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        this.invoiceLineList = invoiceLineList;
    }

    @Override
    public void setEntity(AbstractEntity t) {

    }
}
