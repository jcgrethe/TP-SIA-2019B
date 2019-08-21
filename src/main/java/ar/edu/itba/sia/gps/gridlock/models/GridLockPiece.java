package ar.edu.itba.sia.gps.gridlock.models;

import java.util.List;

public class GridLockPiece {
    private GridLockPieceType type;
    private GridLockPieceDirection direction;
    private int size;
    private int id;

    // Upper Left Side start of the piece
    private int x;
    private int y;

    public GridLockPiece(int id, GridLockPieceType type, GridLockPieceDirection direction, int size, int x, int y) {
        this.id = id;
        this.type = type;
        this.direction = direction;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public GridLockPieceType getType() {
        return type;
    }

    public void setType(GridLockPieceType type) {
        this.type = type;
    }

    public GridLockPieceDirection getDirection() {
        return direction;
    }

    public void setDirection(GridLockPieceDirection direction) {
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
