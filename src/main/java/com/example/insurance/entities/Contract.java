package com.example.insurance.entities;

import java.util.Date;

public class Contract {
    private final int contract_id;
    private final int employee_id;
    private final int passport_id;
    private final int Tariff_id;
    private final Date date_start;
    private final float time;
    private final float interest_rate;
    private final float payment_amount;

    public Contract(int contract_id, int employee_id, int passport_id, int tariff_id, Date date_start, float time, float interest_rate, float payment_amount) {
        this.contract_id = contract_id;
        this.employee_id = employee_id;
        this.passport_id = passport_id;
        this.Tariff_id = tariff_id;
        this.date_start = date_start;
        this.time = time;
        this.interest_rate = interest_rate;
        this.payment_amount = payment_amount;
    }

    public boolean insertContract(){
        return ConnectorDB.updateTable(String.format("INSERT INTO Insurance_contract(contract_id, employee_id, passport_id, tariff_id, date_start, time, interest_rate, payment_amount) " +
                "VALUES (%d, %d, %d, %d, NOW(), " + time + ", " + interest_rate + ", " + payment_amount + ")", contract_id, employee_id, passport_id, Tariff_id));
    }

    public int getContract_id() {
        return contract_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getPassport_id() {
        return passport_id;
    }

    public int getTariff_id() {
        return Tariff_id;
    }

    public Date getDate_start() {
        return date_start;
    }

    public float getTime() {
        return time;
    }

    public float getInterest_rate() {
        return interest_rate;
    }

    public float getPayment_amount() {
        return payment_amount;
    }
}


