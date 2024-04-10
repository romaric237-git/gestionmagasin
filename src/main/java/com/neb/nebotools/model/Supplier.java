package com.neb.nebotools.model;

public class Supplier extends Person{
    private String firstname;
    private String lastname;
    private String description;
    private String mail;
    private String phone;

    public Supplier() {
    }

    public Supplier(String id, String firstname, String description, String lastname, String mail, String phone, int is_actif) {
        super(id,firstname,lastname,mail,phone,is_actif);
        this.description = description;
    }

    public Supplier(String firstname, String lastname, String mail, String phone, String description) {
        super(firstname,lastname,mail,phone);
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setEntity(AbstractEntity t) {
        Supplier u = (Supplier) t;

        firstname = u.firstname;
        lastname = u.lastname;
        description = u.description;
        mail = u.mail;
        phone = u.phone;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname;
    }

}
