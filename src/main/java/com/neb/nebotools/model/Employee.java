package com.neb.nebotools.model;

import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.enumeration.Role;

import java.util.ArrayList;
import java.util.List;

public class Employee extends AbstractEntity{
    private static Employee userConnected;
    private static List<Employee> users = new ArrayList<Employee>();
    private String login;
    private String firstname;
    private String lastname;
    private String password;
    private String mail;
    private String phone;
    private Role role;

    static{
        try {
            userConnected= DaoFactory.getEmployeeDao().find("usr-023-001");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Employee(UserBuilder builder) {
        id = builder.id;
        login = builder.login;
        firstname = builder.firstname;
        lastname = builder.lastname;
        password = builder.password;
        mail = builder.mail;
        phone = builder.phone;
        role = Role.getRole(builder.role);

        if(!users.contains(this))users.add(this);
    }

    public Employee() {
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setRole(int role) {
        this.role = Role.getRole(role);
    }

    public static Employee getUserConnected() {
        return userConnected;
    }

    public static void setUserConnected(Employee userConnected) {
        Employee.userConnected = userConnected;
    }

    public static List<Employee> getList() {
        return users;
    }


    public static class UserBuilder {
        private String id = "";
        private int role = 0;
        private String login = "";
        private String firstname = "";
        private String lastname = "";
        private String password = "";
        private String mail = "";
        private String phone = "";

        public UserBuilder id (String id) {
            this.id = id;
            return this;
        }

        public UserBuilder role(int role) {
            this.role = role;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }


    @Override
    public void setEntity(AbstractEntity t) {
        Employee u = (Employee) t;

        login = u.login;
        firstname = u.firstname;
        lastname = u.lastname;
        password = u.password;
        mail = u.mail;
        phone = u.phone;
        role = u.role;
    }

    @Override
    public String toString() {
        return lastname + " " + firstname;
    }

}
