package core;

public class Board {
    private int rows;
    private int cols;
    private Cell[][] board;

    public static final int SIZE = 3; 

    public Board() {
        this(3, 3);
    }

    public Board(int rows, int cols) {
        
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("❌ Les dimensions du plateau doivent être positives (rows: " + rows + ", cols: " + cols + ")");
        }
        
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
        
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        return board[row][col].getRepresentation().equals("   ");
    }

    public void setOwner(int row, int col, Player player) {
        
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        
        if (player == null) {
            throw new IllegalArgumentException("❌ Le joueur ne peut pas être null");
        }
        
        board[row][col].setRepresentation(player.getRepresentation());
    }

    public Cell getCell(int row, int col) {
        
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
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
        
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        return board[row][col].getRepresentation();
    }

    public boolean isColumnFull(int col) {
        
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Colonne invalide : " + col + " - Limites : (0-" + (cols-1) + ")");
        }
        
        return !isCellEmpty(0, col);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}