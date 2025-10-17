package games.TicTacToe.model;

import core.Board;
import core.Player;

public class TicTacToe {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToe(Player p1, Player p2) {
       
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("❌ Les joueurs ne peuvent pas être null (p1: " + p1 + ", p2: " + p2 + ")");
        }
        
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isOver() {
        return getWinner() != null || board.isFull();
    }

    public Player getWinner() {
       
        for (int i = 0; i < Board.SIZE; i++) {
            if (!board.getCellRepresentation(i, 0).equals("   ") &&
                    board.getCellRepresentation(i, 0).equals(board.getCellRepresentation(i, 1)) &&
                    board.getCellRepresentation(i, 0).equals(board.getCellRepresentation(i, 2))) {
                return board.getCellRepresentation(i, 0).equals(player1.getRepresentation()) ? player1 : player2;
            }
        }

       
        for (int j = 0; j < Board.SIZE; j++) {
            if (!board.getCellRepresentation(0, j).equals("   ") &&
                    board.getCellRepresentation(0, j).equals(board.getCellRepresentation(1, j)) &&
                    board.getCellRepresentation(0, j).equals(board.getCellRepresentation(2, j))) {
                return board.getCellRepresentation(0, j).equals(player1.getRepresentation()) ? player1 : player2;
            }
        }

       
        if (!board.getCellRepresentation(0, 0).equals("   ") &&
                board.getCellRepresentation(0, 0).equals(board.getCellRepresentation(1, 1)) &&
                board.getCellRepresentation(0, 0).equals(board.getCellRepresentation(2, 2))) {
            return board.getCellRepresentation(0, 0).equals(player1.getRepresentation()) ? player1 : player2;
        }

        if (!board.getCellRepresentation(0, 2).equals("   ") &&
                board.getCellRepresentation(0, 2).equals(board.getCellRepresentation(1, 1)) &&
                board.getCellRepresentation(0, 2).equals(board.getCellRepresentation(2, 0))) {
            return board.getCellRepresentation(0, 2).equals(player1.getRepresentation()) ? player1 : player2;
        }

        return null;
    }

    public boolean playMove(int row, int col) {
        
        try {
            if (!board.isCellEmpty(row, col)) {
                return false;
            }
            board.setOwner(row, col, currentPlayer);
            return true; 
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Coup invalide : " + e.getMessage());
            return false; 
        }
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}