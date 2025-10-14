package games.TicTacToe.controller;

import core.Player;
import games.TicTacToe.model.TicTacToe;
import games.TicTacToe.view.ViewTicTacToe;

public class TicTacToeController {

    private TicTacToe model;
    private ViewTicTacToe view;

    public TicTacToeController() {
        view = new ViewTicTacToe();
        view.showWelcome();

        int choice = view.getModeChoice();
        Player p1, p2;

        switch (choice) {
            case 1 -> {
                p1 = new Player(" X ", false);
                p2 = new Player(" O ", false);
            }
            case 2 -> {
                p1 = new Player(" X ", false);
                p2 = new Player(" O ", true);
            }
            case 3 -> {
                p1 = new Player(" X ", true);
                p2 = new Player(" O ", true);
            }
            default -> {
                System.out.println("Choix invalide, mode par défaut : Humain vs IA");
                p1 = new Player(" X ", false);
                p2 = new Player(" O ", true);
            }
        }

        model = new TicTacToe(p1, p2);
    }

    public void start() {
        while (!model.isOver()) {
            view.showBoard(model.getBoard());

            Player current = model.getCurrentPlayer();
            int[] move;

            if (current.isArtificial()) {
                move = current.getMove(model.getBoard());
            } else {
                move = view.askMove(current, model.getBoard());
            }

            boolean valid = model.playMove(move[0], move[1]);
            if (!valid) {
                view.showInvalidMove();
                continue;
            }

            model.switchPlayer();
        }

        view.showBoard(model.getBoard());

        if (model.getWinner() != null) {
            view.showWinner(model.getWinner());
        } else {
            view.showDraw();
        }
    }
}