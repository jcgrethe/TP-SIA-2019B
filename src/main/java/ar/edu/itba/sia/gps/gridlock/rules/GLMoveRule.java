package ar.edu.itba.sia.gps.gridlock.rules;


import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPieceDirection;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by eric on 15/03/17.
 */
public abstract class GLMoveRule implements Rule {

    @Override
    public Integer getCost() {
        return 1;
    }

    public Optional<State> apply(State state, GridLockPiece piece, GLMoveDirection direction){
        if (!(state instanceof GridLockState))
            throw new IllegalStateException("Invalid state class");
        GridLockState GLState = (GridLockState) state;
        try{
            Object potentialState = GLState.clone();
            if (!(potentialState instanceof GridLockState)){
                throw new CloneNotSupportedException();
            }
            GridLockState newState = (GridLockState) potentialState;
            return validateAndMove(newState, piece, direction);
        } catch (CloneNotSupportedException e){
            throw new IllegalStateException("There was some problem cloning the state.");
        }
    }

    private Optional<State> validateAndMove(GridLockState state, GridLockPiece piece, GLMoveDirection direction){
        GridLockBoard board = state.getBoard();
        int potentialCell, toFreeCell;
        if (piece.getDirection() == GridLockPieceDirection.HORIZONTAL){
            if (direction == GLMoveDirection.RIGHT){
                potentialCell = piece.getX() + piece.getSize();
                if (board.getCell(potentialCell, piece.getY()) != GridLockBoard.BLANK_MARK){
                    return Optional.empty();
                }
                toFreeCell = piece.getX();
                board.setCell(toFreeCell, piece.getY(), GridLockBoard.BLANK_MARK);
                board.setCell(potentialCell, piece.getY(), piece.getId());
                board.getPiece(piece.getId()).setNewPosition(piece.getX() + 1, piece.getY());
                return Optional.of(state);
            } else if (direction == GLMoveDirection.LEFT){
                potentialCell = piece.getX() - 1;
                if (board.getCell(potentialCell, piece.getY()) != GridLockBoard.BLANK_MARK){
                    return Optional.empty();
                }
                toFreeCell = piece.getX() + piece.getSize() - 1;
                board.setCell(toFreeCell, piece.getY(), GridLockBoard.BLANK_MARK);
                board.setCell(potentialCell, piece.getY(), piece.getId());
                board.getPiece(piece.getId()).setNewPosition(piece.getX() - 1, piece.getY());
                return Optional.of(state);
            } else return Optional.empty();
        }else if (piece.getDirection() == GridLockPieceDirection.VERTICAL){
            if (direction == GLMoveDirection.UP){
                potentialCell = piece.getY() - 1;
                if (board.getCell(piece.getX(), potentialCell) != GridLockBoard.BLANK_MARK){
                    return Optional.empty();
                }
                toFreeCell = piece.getY() + piece.getSize() - 1;
                board.setCell(piece.getX(), toFreeCell, GridLockBoard.BLANK_MARK);
                board.setCell(piece.getX(), potentialCell, piece.getId());
                board.getPiece(piece.getId()).setNewPosition(piece.getX(), piece.getY() - 1);
                return Optional.of(state);
            } else if (direction == GLMoveDirection.DOWN){
                potentialCell = piece.getY() + piece.getSize();
                if (board.getCell(piece.getX(), potentialCell) != GridLockBoard.BLANK_MARK){
                    return Optional.empty();
                }
                toFreeCell = piece.getY();
                board.setCell(piece.getX(), toFreeCell, GridLockBoard.BLANK_MARK);
                board.setCell(piece.getX(), potentialCell, piece.getId());
                board.getPiece(piece.getId()).setNewPosition(piece.getX(), piece.getY() + 1);
                return Optional.of(state);
            } else return Optional.empty();
        } else return Optional.empty();
    }
}
