package com.example.guanghuili.checkesandchess.Checkers;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room implements Serializable {
    private int id;
    private Player player1;
    private Player player2;
    private Boolean turn = true;
    private List<List<Checker>> checkerList;

    public Room(){
    }

    public Room(int id, Player player1){
        this.id = id;
        this.player1 = player1;
        this.checkerList = new ArrayList<List<Checker>>();
        Checker [][] checkerArray = new Checker[][]
                        {{null, new RedChecker(0,1), null, new RedChecker(0,3), null, new RedChecker(0,5), null, new RedChecker(0,7)},
                        {new RedChecker(1,0), null, new RedChecker(1,2),null, new RedChecker(1,4), null, new RedChecker(1,6), null},
                        {null, new RedChecker(2,1), null, new RedChecker(2,3), null, new RedChecker(2,5), null, new RedChecker(2,7)},
                        {null, null, null, null,null, null,null, null},
                        {null, null, null, null,null, null,null, null},
                        {new BlackChecker(5,0), null, new BlackChecker(5,2),null, new BlackChecker(5,4), null, new BlackChecker(5,6), null},
                        {null, new BlackChecker(6,1), null, new BlackChecker(6,3), null, new BlackChecker(6,5), null, new BlackChecker(6,7)},
                        {new BlackChecker(7,0), null, new BlackChecker(7,2),null, new BlackChecker(7,4), null, new BlackChecker(7,6), null}
                        };

        for(int r = 0; r < checkerArray.length; r++){
            List <Checker> row = new ArrayList <Checker>();
            for(int c = 0; c < checkerArray[r].length; c++){
                row.add(checkerArray[r][c]);
            }
            this.checkerList.add(row);
        }

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


    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public List<List<Checker>> getCheckerList() {
        return checkerList;
    }

    public void setCheckerList(List<List<Checker>> checkerList) {
        this.checkerList = checkerList;
    }

}
