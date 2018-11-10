package com.example.guanghuili.checkesandchess.Checkers;

import java.util.ArrayList;

public class RoomManager {
    private static ArrayList<Room> roomList = new ArrayList<>();
    private static ArrayList<Integer> idList = new ArrayList<>();

    public RoomManager() {

    }

    public void createRoom(Player player1) {
        int id = idGenerator();
        Room room = new Room(id, player1);
    }

    public void joinRoom(int id, Player player2){
        for(int i = 0; i < roomList.size(); i++){
            if(id == roomList.get(i).getId()){
                roomList.get(i).setPlayer2(player2);
                break;
            }
        }
    }

    public int idGenerator() {
        boolean generating = true;
        int min = 1;
        int max = 5000;
        int range = (5000 - 1) + 1;
        int random = (int) (Math.random() * range) + min;
        while (generating){
            for (int i = 0; i < roomList.size(); i++) {
                if (random == roomList.get(i).getId()) {
                    random = (int) (Math.random() * range) + min;
                    break;
                }
            }
        }
        return random;
    }
}
