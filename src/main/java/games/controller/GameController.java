package games.controller;

import core.Board;
import core.Player;
import core.View;

public abstract class GameController {

    protected Board board;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;
    protected View view;

    public GameController(Player p1, Player p2, Board board, View view) {
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;
        this.board = board;
        this.view = view;
    }

    public abstract void startGame();


    public abstract boolean playMove(int row, int col);


    public abstract boolean isOver();


    public abstract Player getWinner();


    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


    public void displayBoard() {
        view.showBoard(board);
    }
}