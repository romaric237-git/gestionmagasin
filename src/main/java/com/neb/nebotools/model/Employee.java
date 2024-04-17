package com.neb.nebotools.model;

import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employee extends AbstractEntity {
    private static Employee employeeConnected;
    private static List<Employee> employees = new ArrayList<Employee>();
    private String login;
    private String firstname;
    private String lastname;
    private String password;
    private String mail;
    private String phone;
    private Date birth;
    private int sex;
    private Role role;

    static {
        try {
            employeeConnected = DaoFactory.getEmployeeDao().find("emp-023-001");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getOld() {
        return LocalDate.now().getYear() - birth.toLocalDate().getYear();
    }

    public Employee(UserBuilder builder) {
        id = builder.id;
        login = builder.login;
        firstname = builder.firstname;
        lastname = builder.lastname;
        password = builder.password;
        mail = builder.mail;
        phone = builder.phone;
        birth = builder.birth;
        sex = builder.sex;
        role = Role.getRole(builder.role);

        if (!employees.contains(this)) employees.add(this);
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

    public static Employee getEmployeeConnected() throws SQLException {
        return DaoFactory.getEmployeeDao().find("usr-023-001");
    }

    public static void setUserConnected(Employee employeeConnected) {
        Employee.employeeConnected = employeeConnected;
    }

    public static List<Employee> getList() {
        return employees;
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
        private int sex = 0;
        private Date birth = Date.valueOf(LocalDate.now());

        public UserBuilder id(String id) {
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

        public UserBuilder sex(int sex) {
            this.sex = sex;
            return this;
        }

        public UserBuilder birth(Date birth) {
            this.birth = birth;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public String getSexe(){
        return sex==1?"Homme":"Femme";
    }

    @Override
    public void setEntity(AbstractEntity t) {
        if(t instanceof Employee u) {
            login = u.login;
            firstname = u.firstname;
            lastname = u.lastname;
            password = u.password;
            mail = u.mail;
            phone = u.phone;
            role = u.role;
            sex = u.sex;
            birth = u.birth;
        }
    }

    @Override
    public String toString() {
        return lastname + " " + firstname;
    }

}
