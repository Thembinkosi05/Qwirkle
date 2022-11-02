package com.capstone.qwirkle.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;

import com.capstone.qwirkle.R;
import com.capstone.qwirkle.models.Tile;

public class viewTile extends AppCompatImageButton {

    private Tile tile;
    private float defaultX, defaultY;
    private float dx, dy;
    private int parentWidth, parentHeight;
    private final static float ACCELERATION = 0.4f, FRICTION = 0.005F;

    public viewTile(@NonNull Context context) {
        super(context);
    }

    public viewTile(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public viewTile(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        setImageResource(R.drawable.blue_circle);
        switch (tile.getShape()){
            case CIRCLE:setShape(tile.getColour());
        }
    }

    private void setShape(Tile.Colour Colour ) {
        switch(Colour){
            case RED:
        }
    }

    public void setAnimVar(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

}
