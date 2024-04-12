package com.neb.nebotools.model;

import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.enumeration.Role;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person{

    public Customer(String id, String firstname, String lastname, String mail, String phone) {
        super(id, firstname, lastname, mail, phone);
    }
    public Customer() {
        super();
    }
}
