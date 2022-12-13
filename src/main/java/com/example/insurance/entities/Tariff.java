package com.example.insurance.entities;

public class Tariff {
    private final int ID;
    private final String title;
    private final float yearH_IR;
    private final float year_IR;
    private final float year3_IR;


    public Tariff(int ID, String title, float yearH_IR, float year_IR, float year3_IR) {
        this.ID = ID;
        this.title = title;
        this.yearH_IR = yearH_IR;
        this.year_IR = year_IR;
        this.year3_IR = year3_IR;
    }

    public boolean insertTariff(){
        return ConnectorDB.updateTable(String.format("INSERT INTO Tariff(Tariff_id, title, yearH_IR, year_IR, year3_IR) " +
                "VALUES (%d, '%s', "+ yearH_IR +", " + year_IR + ", " + year3_IR + ")", ID, title));
    }

    public int getID(){
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public float getYearH_IR() {
        return yearH_IR;
    }

    public float getYear_IR() {
        return year_IR;
    }

    public float getYear3_IR() {
        return year3_IR;
    }
}


