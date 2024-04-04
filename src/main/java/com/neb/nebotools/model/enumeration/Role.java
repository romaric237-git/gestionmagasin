package com.neb.nebotools.model.enumeration;

public enum Role {
    EMPLOYEE(1),OWNER(2) ;

    private int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String toString() {
        return "";
    }
    public static Role getRole(int id) {
        if(id == 2) return Role.OWNER;
        if(id == 1) return Role.EMPLOYEE;
        return null;
    }

}
