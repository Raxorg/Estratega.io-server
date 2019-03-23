package com.frontanilla.estrategaioserver.firebase;

import com.badlogic.gdx.graphics.Color;

public class Player {

    private String phoneID;
    private String name;
    private Color color;
    private int money;
    private int turn;

    public Player(String phoneID, String name, Color color, int money, int turn) {
        this.phoneID = phoneID;
        this.name = name;
        this.color = color;
        this.money = money;
        this.turn = turn;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getMoney() {
        return money;
    }

    public int getTurn() {
        return turn;
    }

    public void set(Player player) {
        set(player.phoneID, player.name, player.color, player.money, player.turn);
    }

    public void set(String phoneID, String name, Color color, int money, int turn) {
        this.phoneID = phoneID;
        this.name = name;
        this.color = color;
        this.money = money;
        this.turn = turn;
    }

    @Override
    public boolean equals(Object o) {
        Player other;
        if (o instanceof Player) {
            other = (Player) o;
        } else {
            return false;
        }
        return other.phoneID.equals(phoneID);
    }
}
