package com.capstone.qwirkle.models;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.capstone.qwirkle.views.*;

public class hand {

    public static int initialTileNumber = 108;

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


}
