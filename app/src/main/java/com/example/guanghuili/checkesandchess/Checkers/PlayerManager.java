package com.example.guanghuili.checkesandchess.Checkers;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerManager {
    ArrayList <Player> playerList = new ArrayList<>();
    Player signedInPlayer = new Player();

    public PlayerManager(){

    }
    public Player createPlayer(String username, String email){
        Player player = new Player(username, email);
        playerList.add(player);
        return player;
    }

    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

   /*
    public String getUsername(String email){
        String username = "";
        for(Player player : playerList){
            if(player.getEmail().equals(email)){
                username = player.getUsername();
                break;
            }
        }
        return username;
    }
    */

   public Player getSignedInPlayer(String email){
       for(int i = 0; i < playerList.size(); i++){
           if(playerList.get(i).getEmail().equals(email)){
               signedInPlayer.setEmail(playerList.get(i).getEmail());
               signedInPlayer.setUsername(playerList.get(i).getUsername());
               break;
           }
       }
       return signedInPlayer;
   }


}
