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
        defaultX = getX();
        defaultY = getY();
    }

    public viewTile(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        defaultX = getX();
        defaultY = getY();
    }

    public viewTile(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaultX = getX();
        defaultY = getY();
    }

    public void setTile(Tile tile) {
        this.tile = tile;
        switch (tile.getShape()){
            case CIRCLE:setCircle(tile.getColour());
            break;
            case CLUB:setClub(tile.getColour());
            break;
            case STAR:setStar(tile.getColour());
            break;
            case CROSS:setCross(tile.getColour());
            break;
            case SQUARE:setSquare(tile.getColour());
            break;
            case DIAMOND: setDiamond(tile.getColour());
            break;
        }
    }

    public Tile getTile(){
        return tile;
    }

    private void setDiamond(Tile.Colour colour) {
        switch(colour){
            case RED:setImageResource(R.drawable.red_diamond);
                break;
            case BLUE:setImageResource(R.drawable.blue_diamond);
                break;
            case GREEN:setImageResource(R.drawable.green_diamond);
                break;
            case ORANGE:setImageResource(R.drawable.orange_diamond);
                break;
            case PURPLE:setImageResource(R.drawable.purple_diamond);
                break;
            case YELLOW:setImageResource(R.drawable.yellow_diamond);
                break;
        }
    }

    private void setSquare(Tile.Colour colour) {
        switch(colour){
            case RED:setImageResource(R.drawable.red_square);
                break;
            case BLUE:setImageResource(R.drawable.blue_square);
                break;
            case GREEN:setImageResource(R.drawable.green_square);
                break;
            case ORANGE:setImageResource(R.drawable.orange_square);
                break;
            case PURPLE:setImageResource(R.drawable.purple_square);
                break;
            case YELLOW:setImageResource(R.drawable.yellow_square);
                break;
        }
    }

    private void setClub(Tile.Colour colour) {
        switch(colour){
            case RED:setImageResource(R.drawable.red_four_points_star);
                break;
            case BLUE:setImageResource(R.drawable.blue_four_points_star);
                break;
            case GREEN:setImageResource(R.drawable.green_four_points_star);
                break;
            case ORANGE:setImageResource(R.drawable.orange_four_points_star);
                break;
            case PURPLE:setImageResource(R.drawable.purple_four_points_star);
                break;
            case YELLOW:setImageResource(R.drawable.yellow_four_points_star);
                break;
        }
    }

    private void setStar(Tile.Colour colour) {
        switch(colour){
            case RED:setImageResource(R.drawable.red_eight_points_star);
                break;
            case BLUE:setImageResource(R.drawable.blue_eight_points_star);
                break;
            case GREEN:setImageResource(R.drawable.green_eight_points_star);
                break;
            case ORANGE:setImageResource(R.drawable.orange_eight_points_star);
                break;
            case PURPLE:setImageResource(R.drawable.purple_eight_points_star);
                break;
            case YELLOW:setImageResource(R.drawable.yellow_eight_points_star);
                break;
        }
    }

    private void setCross(Tile.Colour colour) {
        switch(colour){
            case RED:setImageResource(R.drawable.red_clover);
                break;
            case BLUE:setImageResource(R.drawable.blue_clover);
                break;
            case GREEN:setImageResource(R.drawable.green_clover);
                break;
            case ORANGE:setImageResource(R.drawable.orange_clover);
                break;
            case PURPLE:setImageResource(R.drawable.purple_clover);
                break;
            case YELLOW:setImageResource(R.drawable.yellow_clover);
                break;
        }
    }

    private void setCircle(Tile.Colour Colour ) {
        switch(Colour){
            case RED:setImageResource(R.drawable.red_circle);
            break;
            case BLUE:setImageResource(R.drawable.blue_circle);
            break;
            case GREEN:setImageResource(R.drawable.green_circle);
            break;
            case ORANGE:setImageResource(R.drawable.orange_circle);
            break;
            case PURPLE:setImageResource(R.drawable.purple_circle);
            break;
            case YELLOW:setImageResource(R.drawable.yellow_circle);
            break;
        }
    }

    public void setParentWidth(int parentWidth) {
        this.parentWidth = parentWidth;
    }

    public void setParentHeight(int parentHeight) {
        this.parentHeight = parentHeight;
    }

    public void setAnimVar(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void resetPos() {
        setX(defaultX);
        setY(defaultY);
    }
    public float getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(float defaultX) {
        this.defaultX = defaultX;
    }

    public float getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(float defaultY) {
        this.defaultY = defaultY;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }
}
