package core;
import core.Cell;
import core.Player;

public class Board {
    public static final int SIZE = 3;

    private Cell[][] board;

    public Board() {
        board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col].getRepresentation().equals("   ");
    }

    public void setOwner(int row, int col, Player player) {
        board[row][col].setRepresentation(player.getRepresentation());
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getCellRepresentation(int row, int col) {
        return board[row][col].getRepresentation();
    }
}