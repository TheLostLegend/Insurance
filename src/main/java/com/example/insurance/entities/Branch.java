package com.example.insurance.entities;

public class Branch {
    private final int branch_id;
    private final String branch_name;
    private final String address;
    private final String phone_number;




    public Branch(int ID, String title, String address, String phone) {
        this.branch_id = ID;
        this.branch_name = title;
        this.address = address;
        this.phone_number = phone;
    }

    public boolean insertBranch(){
        return ConnectorDB.updateTable(String.format("INSERT INTO Branch(branch_id, branch_name, address, phone_number) " +
                "VALUES (%d, '%s', '%s', '%s')", branch_id, branch_name, address, phone_number));
    }

    public int getBranch_id() {
        return branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }
}


