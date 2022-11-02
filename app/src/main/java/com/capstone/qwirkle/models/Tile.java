package com.capstone.qwirkle.models;

import java.io.Serializable;

public class Tile  implements Serializable {

    public enum Colour{ PURPLE,BLUE,GREEN,YELLOW,ORANGE,RED}

    public enum Shape{ CLUB,STAR,SQUARE,DIAMOND,CROSS,CIRCLE}

    public enum State{ IN_HAND,PLACED,PLACING,IN_BAG}

    public int col,row;
    public Shape Shape;
    public Colour Colour;
    public State state;


    public Tile(Colour colour,Shape shape, State state){
        this.Colour = colour;
        this.Shape = shape;
        this.state = state;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Tile.Shape getShape() {
        return Shape;
    }

    public void setShape(Tile.Shape shape) {
        Shape = shape;
    }

    public Tile.Colour getColour() {
        return Colour;
    }

    public void setColour(Tile.Colour colour) {
        Colour = colour;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
