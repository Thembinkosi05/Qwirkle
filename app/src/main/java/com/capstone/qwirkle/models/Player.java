package com.capstone.qwirkle.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private ArrayList<Tile> hand;
    public String username;
    public int points;
    private int gameID;
    public  int playerNo;

    public Player(ArrayList<Tile> hand, int playerNo) {
        //this.username=username;
        points = 0;
        this.hand = hand;
        this.playerNo = playerNo;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public ArrayList<Tile> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Tile> hand) {
        this.hand = hand;
    }

    public int getGameID() {
        return gameID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public void placeTile(){

    }
}
