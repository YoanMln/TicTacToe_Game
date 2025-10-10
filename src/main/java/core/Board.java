package core;

public class Board {
    private int rows;
    private int cols;
    private Cell[][] board;

    public static final int SIZE = 3; // pour TicTacToe

    public Board() {
        this(3, 3);
    }

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
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
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
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

    public boolean isColumnFull(int col) {
        return !isCellEmpty(0, col);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}