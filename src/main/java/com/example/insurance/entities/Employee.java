package com.example.insurance.entities;

public class Employee {
    private final int employee_id;
    private final String password;
    private final String first_name;
    private final String middle_name;
    private final String last_name;
    private final String phone_number;
    private final String sex;
    private final String appointment;
    private final int branch_id;

    public Employee(int employee_id, String password, String first_name, String middle_name, String last_name, String phone_number, String sex, String appointment, int branch_id) {
        this.employee_id = employee_id;
        this.password = password;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.sex = sex;
        this.appointment = appointment;
        this.branch_id = branch_id;
    }


    public boolean insertEmployee(){
        return ConnectorDB.updateTable(String.format("INSERT INTO Employee(employee_id, password_emp, first_name, middle_name, last_name, phone_number, sex, appointment, branch_id) " +
                "VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)", employee_id, password, first_name, middle_name, last_name, phone_number, sex, appointment, branch_id));
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSex() {
        return sex;
    }

    public String getAppointment() {
        return appointment;
    }

    public int getBranch_id() {
        return branch_id;
    }
}


