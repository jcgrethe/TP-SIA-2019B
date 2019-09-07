package ar.edu.itba.sia.gps.gridlock.heuristics;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;

public class GridLockAdvancedHeuristic implements Heuristic {

    @Override
    public Integer getValue(State state) {
        if (!(state instanceof GridLockState)){
            throw new IllegalStateException("Not a GridLockState");
        }
        GridLockState GLState = (GridLockState) state;
        GridLockBoard board = GLState.getBoard();
        GridLockPiece mainPiece = board.getPiece(GridLockBoard.MAIN_PIECE_ID);
        int distance = board.getSize() - (mainPiece.getX() + mainPiece.getSize());
        int blockDistance = 0;
        int cont = mainPiece.getX() + mainPiece.getSize();
        while(cont < board.getSize()) {
            if (board.getCell(mainPiece.getY(),cont) != GridLockBoard.BLANK_MARK){
                blockDistance++;
                GridLockPiece gridLockPiece = board.getPiece(board.getCell(mainPiece.getY(),cont));

                // move down
                int auxMoveDistanceDown = 0;
                int distanceToMoveDown = gridLockPiece.getY() - mainPiece.getY();
                while (distanceToMoveDown >= 0){
                    int y = gridLockPiece.getY() + gridLockPiece.getSize() + distanceToMoveDown;
                    if(y >= board.getSize()){
                        auxMoveDistanceDown = 0;
                        break;
                    }
                    if(board.getCell(y,cont)== GridLockBoard.BLANK_MARK){
                        auxMoveDistanceDown++;
                        distanceToMoveDown--;
                    }else {
                        auxMoveDistanceDown = 0;
                        break;
                    }
                }

                // move UP
                int auxMoveDistanceUp = 0;
                int distanceToMoveUp = (gridLockPiece.getY() + gridLockPiece.getSize()) - mainPiece.getY();
                while (distanceToMoveUp > 0){
                    int y = gridLockPiece.getY() - distanceToMoveUp;
                    if(y<0){
                        auxMoveDistanceUp = 0;
                        break;
                    }
                    if(board.getCell(y,cont) == GridLockBoard.BLANK_MARK){
                        auxMoveDistanceUp++;
                        distanceToMoveUp--;
                    }else {
                        auxMoveDistanceUp = 0;
                        break;
                    }
                }
                if(auxMoveDistanceDown != 0 && auxMoveDistanceUp!= 0){
                    blockDistance += auxMoveDistanceDown<auxMoveDistanceUp?auxMoveDistanceDown:auxMoveDistanceUp;
                }else {
                    if(auxMoveDistanceDown!=0)
                        blockDistance += auxMoveDistanceDown;
                    if(auxMoveDistanceUp!=0)
                        blockDistance += auxMoveDistanceUp;
                }


            }
            cont++;
        }
        return distance + blockDistance;
    }
}
