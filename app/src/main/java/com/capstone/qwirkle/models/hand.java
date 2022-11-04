package com.capstone.qwirkle.models;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.capstone.qwirkle.views.*;

public class hand {

    int width;
    int height;
    private List<Tile> tiles = new ArrayList<>(6);
    private List<viewTile> tileViews = new ArrayList<>(6);

    public hand(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void initTileView(viewTile... viewTiles){
        tileViews.addAll(Arrays.asList(viewTiles));
    }

    public void setRandomAccel() {
        Random ran = new Random();

        for (viewTile dice : tileViews) {
            dice.setAnimVar(ran.nextInt(25) - 15, ran.nextInt(25) - 15);
        }
    }

    public void setDiceViews(List<viewTile> tileViews) {
        this.tileViews = tileViews;
    }


}
