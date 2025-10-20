package games.TicTacToe.controller;

import core.Board;
import core.Player;
import games.TicTacToe.model.TicTacToe;
import games.TicTacToe.view.ViewTicTacToe;
import games.controller.GameController;

/**
 * Contrôleur pour le jeu TicTacToe suivant l'architecture MVC.
 * Gère l'initialisation du jeu, la boucle de jeu et la coordination entre le modèle et la vue.
 */
public class TicTacToeController extends GameController {

    private final TicTacToe model;
    private final ViewTicTacToe ticTacToeView;

    /**
     * Constructeur qui initialise le contrôleur TicTacToe.
     * Configure les joueurs, le plateau et la vue, puis affiche le message de bienvenue.
     */
    public TicTacToeController() {
        super(initializePlayers()[0], initializePlayers()[1], new Board(3, 3), new ViewTicTacToe());
        
        this.ticTacToeView = (ViewTicTacToe) this.view;
        this.model = new TicTacToe(this.player1, this.player2);
        
        this.ticTacToeView.showWelcome();
    }
    
    /**
     * Initialise les joueurs en demandant le mode de jeu à l'utilisateur.
     * Gère les erreurs de saisie et propose un mode par défaut en cas de problème.
     * @return un tableau contenant les deux joueurs [player1, player2]
     */
    private static Player[] initializePlayers() {
        ViewTicTacToe tempView = new ViewTicTacToe();
        tempView.showWelcome();
        
        int choice = 2; // Valeur par défaut
        
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

    /**
     * Lance la boucle principale du jeu TicTacToe.
     * Gère l'alternance des tours, la validation des coups et l'affichage final.
     * Inclut une gestion d'erreur globale pour éviter les crashes.
     */
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

                // Vérifier si le mouvement est valide (pas d'erreur dans askMove)
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

            // Affichage final
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

    /**
     * Tente de jouer un coup aux coordonnées spécifiées.
     * @param row la ligne où jouer
     * @param col la colonne où jouer
     * @return true si le coup a été joué avec succès, false sinon
     */
    @Override
    public boolean playMove(int row, int col) {
        return model.playMove(row, col);
    }

    /**
     * Vérifie si la partie est terminée.
     * @return true si la partie est finie (victoire ou match nul), false sinon
     */
    @Override
    public boolean isOver() {
        return model.isOver();
    }

    /**
     * Récupère le joueur gagnant.
     * @return le joueur gagnant, ou null si pas de gagnant
     */
    @Override
    public Player getWinner() {
        return model.getWinner();
    }

    /**
     * Change le joueur actuel pour passer au tour suivant.
     */
    @Override
    public void switchPlayer() {
        model.switchPlayer();
    }
}