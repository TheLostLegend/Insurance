package com.example.insurance.entities;

public class Client {
    private final int passport_id;
    private final String first_name;
    private final String middle_name;
    private final String last_name;
    private final String phone_number;
    private final String address;

    public Client(int passport_id, String first_name, String middle_name, String last_name, String phone_number, String address) {
        this.passport_id = passport_id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.address = address;
    }


    public boolean insertClient(){
        return ConnectorDB.updateTable(String.format("INSERT INTO Client(passport_id, first_name, middle_name, last_name, address, phone_number) " +
                "VALUES (%d, '%s', '%s', '%s', '%s', '%s')", passport_id, first_name, middle_name, last_name, address, phone_number));
    }

    public int getPassport_id() {
        return passport_id;
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

    public String getAddress() {
        return address;
    }
}


