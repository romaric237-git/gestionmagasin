package com.neb.nebotools.model;

import java.sql.Date;
import java.util.List;

public class Invoice extends AbstractEntity{

    private String Customer;
    private Date date;
    private int price;
    private String employee;

    private List<InvoiceLine> invoiceLineList;

    @Override
    public void setEntity(AbstractEntity t) {

    }
}
