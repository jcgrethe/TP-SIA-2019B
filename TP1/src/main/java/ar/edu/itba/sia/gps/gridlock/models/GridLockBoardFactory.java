package ar.edu.itba.sia.gps.gridlock.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GridLockBoardFactory {

    public static GridLockBoard generateBoardFromFile(String pathToFile){
        int[][] matrix;
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String firstLine = br.readLine(); //size
            int size = Integer.valueOf(firstLine);
            matrix = new int[size][size];

            for (int i = 0 ; i < size ; i++){
                for (int j = 0 ; j < size ; j++){
                    int character;
                    StringBuilder number = new StringBuilder();
                    while((character = br.read()) != 32) {  //While is not space
                        if (character == 10) break;
                        number.append((char)character);
                    }
                    matrix[j][i] = Integer.valueOf(number.toString()).intValue();
                }
            }

            HashMap<Integer, GridLockPiece> pieces = new HashMap<>();
            for (int i = 0 ; i < size ; i++){
                for (int j = 0 ; j < size ; j++){
                    Integer current = matrix[i][j];
                    if (current == 0)
                        continue;
                    if (pieces.containsKey(current)){
                        pieces.get(current).incrementSize();
                    } else {
                        // Create new piece
                        if (i + 1 < size && matrix[i + 1][j] == current){
                            pieces.put(current, new GridLockPiece(current,
                                    current == GridLockBoard.MAIN_PIECE_ID? GridLockPieceType.MAIN: GridLockPieceType.SECONDARY,
                                    GridLockPieceDirection.HORIZONTAL,
                                    1, i, j));
                        } else if (j + 1 < size && matrix[i][j + 1] == current){
                            pieces.put(current, new GridLockPiece(current,
                                    current == GridLockBoard.MAIN_PIECE_ID? GridLockPieceType.MAIN: GridLockPieceType.SECONDARY,
                                    GridLockPieceDirection.VERTICAL,
                                    1, i, j));
                        } else throw new IllegalArgumentException("Invalid piece.");
                    }
                }
            }

            return new GridLockBoard(matrix, pieces);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read input file.");
        }
    }
}
