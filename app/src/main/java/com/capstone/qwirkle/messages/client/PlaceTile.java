package com.capstone.qwirkle.messages.client;

import androidx.annotation.NonNull;

import com.capstone.qwirkle.messages.Message;
import com.capstone.qwirkle.models.Player;
import com.capstone.qwirkle.models.Tile;

import java.util.Locale;

public class PlaceTile extends Message {
    private static final long serialVersionUID = 2L;
    public Tile tile;
    public int row;
    public int col;
    public Player player;

    public PlaceTile(Tile tile , int row, int col, Player player) {
        this.tile = tile;
        this.row = row;
        this.col = col;
        //this.posDiceInPool = posDiceInPool;
        this.player = player;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"tile=>%s placed at (%d, %d))", tile, row, col);
    }
}
