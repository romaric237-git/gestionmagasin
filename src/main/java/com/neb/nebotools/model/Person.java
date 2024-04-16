package com.neb.nebotools.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public abstract class Person extends AbstractEntity{
    private String firstname;
    private String lastname;
    private String mail;
    private String phone;

    public Person(String id, String firstname, String lastname, String mail, String phone, int is_actif) {
        super(id, is_actif);
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;

    }

    public Person(String firstname, String lastname, String mail, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
    }

    public Person(String id, String firstname, String lastname, String mail, String phone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
        this.phone = phone;
    }
    public Person() {
        super();
        this.firstname = "";
        this.lastname = "";
        this.mail = "";
        this.phone = "";
    }


    @Override
    public void setEntity(AbstractEntity t) {
        Person u = (Person) t;

        firstname = u.firstname;
        lastname = u.lastname;
        mail = u.mail;
        phone = u.phone;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname;
    }
}
