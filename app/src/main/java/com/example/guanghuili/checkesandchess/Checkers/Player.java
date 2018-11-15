package com.example.guanghuili.checkesandchess.Checkers;

import java.io.Serializable;

public class Player extends RoomManager implements Serializable {
    private String username;
    private String email;
    private String id;
    private int win;
    private int loss;
    private double winningRate;

    public Player(){

    }
    public Player(String username, String email){
        this.username = username;
        this.email = email;
        this.win = 0;
        this.loss = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWin() {
        return win;
    }

    public void updateWin() {
        this.win++;
        updateWinningRate();

    }

    public int getLoss() {
        return loss;
    }

    public void updateLoss() {
        this.loss++;
        updateWinningRate();
    }

    public void updateWinningRate(){
        if((win + loss) != 0){
            this.winningRate = (double)Math.round(((double)win/(double)(win+loss))*100)/100;
        }
    }

    public double getWinningRate() {
        return winningRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
