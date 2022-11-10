package com.capstone.qwirkle.messages.server;

import com.capstone.qwirkle.controller.GameController;
import com.capstone.qwirkle.messages.Message;
import com.capstone.qwirkle.models.Player;

import java.util.List;

public class GameStarted extends Message {
    private static final long serialVersionUID = 103L;

    public Object gameController;
    public String toString() {
        return String.format("game started for gameID: %s",gameController);
    }
}
