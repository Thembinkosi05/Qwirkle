package com.capstone.qwirkle.messages.server;

import com.capstone.qwirkle.messages.Message;

public class Left extends Message {
    private static final long serialVersionUID = 105L;

    public String lobbyID;
    public String playerName;

    public Left(String lobbyID, String playerName) {
        this.lobbyID = lobbyID;
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return String.format("Left('%s', '%s')", lobbyID, playerName);}
}
