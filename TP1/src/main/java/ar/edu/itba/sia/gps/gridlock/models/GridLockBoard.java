package ar.edu.itba.sia.gps.gridlock.models;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GridLockBoard implements Cloneable {
    private int size;
    private int[][] board;
    private HashMap<Integer, GridLockPiece> pieces;

    public static Integer BLANK_MARK = 0;
    public static Integer MAIN_PIECE_ID = -1;
    public static Integer MAIN_PIECE_SIZE = 2;

    /**
     * Constructor for the Initial Board
     *
     * @param size
     */
    public GridLockBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.pieces = new HashMap<>();
        // TODO: Set distribution of pieces

        // Main Piece
        GridLockPiece mainPiece = new GridLockPiece(MAIN_PIECE_ID, GridLockPieceType.MAIN, GridLockPieceDirection.HORIZONTAL, MAIN_PIECE_SIZE, 0, size/2);
        setPiece(mainPiece);
        this.pieces.put(MAIN_PIECE_ID, mainPiece);

        initBoard();
    }

    private void initBoard(){
        int id = 1;

        GridLockPiece one = new GridLockPiece(id, GridLockPieceType.SECONDARY, GridLockPieceDirection.VERTICAL, 2, 3, 2);
        this.pieces.put(id, one);
        setPiece(one);
        id++;

        GridLockPiece two = new GridLockPiece(id, GridLockPieceType.SECONDARY, GridLockPieceDirection.VERTICAL, 2, 4, 2);
        this.pieces.put(id, two);
        setPiece(two);
        id++;

        GridLockPiece three = new GridLockPiece(id, GridLockPieceType.SECONDARY, GridLockPieceDirection.HORIZONTAL, 3, 1, 0);
        this.pieces.put(id, three);
        setPiece(three);
        id++;
    }

    public GridLockBoard(int[][] board, HashMap<Integer, GridLockPiece> pieces) {
        this.board = board;
        this.size = board.length;
        this.pieces = pieces;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HashMap<Integer, GridLockPiece> clonedMap = new HashMap<>();
        this.pieces.entrySet().forEach(entry -> {
            Object clonedPiece;
            try {
                clonedPiece = entry.getValue().clone();
                if (!(clonedPiece instanceof GridLockPiece)){
                    throw new CloneNotSupportedException();
                }
            } catch (CloneNotSupportedException e){
                throw new IllegalStateException("Could not clone Pieces Map");
            }
            clonedMap.put(entry.getKey(), (GridLockPiece) clonedPiece);
        });
        int[][] newBoard = new int[size][size];
        for (int i = 0 ; i < size ; i++)
            for (int j = 0 ; j < size ; j++)
                newBoard[i][j] = new Integer(this.board[i][j]).intValue();
        return new GridLockBoard(newBoard, clonedMap);
    }

    public int getSize() {
        return size;
    }

    public int getCell(int x, int y){
        return this.board[y][x];
    }

    public void setCell(int x, int y, int value){
        this.board[x][y] = value;
    }

    public void setPiece(GridLockPiece piece){
        if (piece.getDirection() == GridLockPieceDirection.HORIZONTAL){
            for (int i = piece.getX() ; i < piece.getX() + piece.getSize() ; i++){
                this.board[i][piece.getY()] = piece.getId();
            }
        } else if (piece.getDirection() == GridLockPieceDirection.VERTICAL){
            for (int j = piece.getY() ; j < piece.getY() + piece.getSize() ; j++){
                this.board[piece.getX()][j] = piece.getId();
            }
        }
    }

    public GridLockPiece getPiece(int id){
        return pieces.get(id);
    }

    public void removePiece(int id){
        pieces.remove(id);
    }

    public List<GridLockPiece> getPieces() {
        return pieces.entrySet().stream().parallel().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridLockBoard that = (GridLockBoard) o;
        if (size != that.size) return false;
        for (int i = 0 ; i < board.length ; i++){
            if (!Arrays.equals(board[i], that.board[i]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0 ; i < board.length ; i++){
            result += Arrays.hashCode(board[i]);
        }
        return result;
    }
}
