package TicTacToe;


public class Board {
    public static final int SIZE = 3;

    private Cell[][] board;
    private Player player1;
    private Player player2;

    public Board() {
        board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
        System.out.println("---------------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(" | ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].getRepresentation());
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println("---------------------");
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col].getRepresentation().equals("   ");
    }

    public void setOwner(int row, int col, Player player) {
        board[row][col].setRepresentation(player.getRepresentation());
    }

    public Cell getCell(int row, int col){
        return board[row][col];
    }

public boolean isFull() {
        for (int i =0; i< SIZE; i++) {
            for (int j =0; j< SIZE; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
       return true;
}

}







