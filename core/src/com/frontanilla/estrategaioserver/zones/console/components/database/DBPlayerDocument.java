package com.frontanilla.estrategaioserver.zones.console.components.database;

public class DBPlayerDocument {
    private String phoneID;
    private String color;
    private int money;
    private String name;
    private int turn;

    public DBPlayerDocument(String phoneID, String color, int money, String name, int turn) {
        this.phoneID = phoneID;
        this.color = color;
        this.money = money;
        this.name = name;
        this.turn = turn;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public String getColor() {
        return color;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getTurn() {
        return turn;
    }

    public void set(DBPlayerDocument playerDocument) {
        phoneID = playerDocument.phoneID;
        color = playerDocument.color;
        money = playerDocument.money;
        name = playerDocument.name;
        turn = playerDocument.turn;
    }
}
