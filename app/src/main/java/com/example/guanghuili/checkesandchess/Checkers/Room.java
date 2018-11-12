package com.example.guanghuili.checkesandchess.Checkers;

public class Room {
    private int id;
    private String databaseId;
    private Player player1;
    private Player player2;

    public Room(){
    }

    public Room(int id, Player player1){
        this.id = id;
        this.player1 = player1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }
}
