package com.neb.nebotools.model;

public class Supplier extends Person{
    private String description;

    public Supplier() {
    }

    public Supplier(String id, String firstname, String description, String lastname, String mail, String phone, int is_actif) {
        super(id,firstname,lastname,mail,phone,is_actif);
        this.description = description;
    }

    public Supplier(String id, String firstname, String lastname, String mail, String phone, String description) {
        super(id, firstname,lastname,mail,phone);
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
        super.setEntity(t);
        Supplier u = (Supplier) t;
        description = u.description;
    }

    @Override
    public String toString() {
        return getLastname() + " " + getFirstname();
    }

}
