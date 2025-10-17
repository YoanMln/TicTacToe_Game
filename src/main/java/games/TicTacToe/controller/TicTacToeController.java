package games.TicTacToe.controller;

import core.Board;
import core.Player;
import games.TicTacToe.model.TicTacToe;
import games.TicTacToe.view.ViewTicTacToe;
import games.controller.GameController;

public class TicTacToeController extends GameController {

    private final TicTacToe model;
    private final ViewTicTacToe ticTacToeView;

    public TicTacToeController() {
        super(initializePlayers()[0], initializePlayers()[1], new Board(3, 3), new ViewTicTacToe());
        
        this.ticTacToeView = (ViewTicTacToe) this.view;
        this.model = new TicTacToe(this.player1, this.player2);
        
       
        this.ticTacToeView.showWelcome();
    }
    
    private static Player[] initializePlayers() {
        ViewTicTacToe tempView = new ViewTicTacToe();
        tempView.showWelcome();
        
        int choice = 2; 
        
        try {
            choice = tempView.getModeChoice();
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de la sélection du mode : " + e.getMessage());
            System.out.println("🔄 Utilisation du mode par défaut : Joueur vs IA");
        }
        
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
                System.out.println("🔄 Choix invalide, mode par défaut : Joueur vs IA");
                p1 = new Player(" X ", false);
                p2 = new Player(" O ", true);
            }
        }
        return new Player[]{p1, p2};
    }

    @Override
    public void startGame() {
        try {
            while (!isOver()) {
                view.showBoard(model.getBoard());

                int[] move;
                Player current = model.getCurrentPlayer();
                
                if (current.isArtificial()) {
                    move = current.getMove(model.getBoard());
                } else {
                    move = ticTacToeView.askMove(current, model.getBoard());
                }

                
                if (move[0] == -1 || move[1] == -1) {
                    ticTacToeView.showInvalidMove();
                    continue;
                }

                if (!playMove(move[0], move[1])) {
                    ticTacToeView.showInvalidMove();
                    continue;
                }

                switchPlayer();
            }

            
            view.showBoard(model.getBoard());

            if (getWinner() != null) {
                ticTacToeView.showWinner(getWinner());
            } else {
                ticTacToeView.showDraw();
            }
            
        } catch (Exception e) {
            System.out.println("❌ Erreur critique pendant le jeu : " + e.getMessage());
            System.out.println("🔄 Le jeu va se terminer.");
        }
    }

    @Override
    public boolean playMove(int row, int col) {
        return model.playMove(row, col);
    }

    @Override
    public boolean isOver() {
        return model.isOver();
    }

    @Override
    public Player getWinner() {
        return model.getWinner();
    }

    @Override
    public void switchPlayer() {
        model.switchPlayer();
    }
}