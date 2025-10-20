package games.TicTacToe.model;

import core.Board;
import core.Player;

/**
 * Modèle du jeu TicTacToe qui gère la logique de jeu.
 * Contient les règles du jeu, la détection de victoire et la gestion des tours.
 */
public class TicTacToe {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    /**
     * Constructeur pour initialiser une partie de TicTacToe.
     * @param p1 le premier joueur
     * @param p2 le deuxième joueur
     * @throws IllegalArgumentException si l'un des joueurs est null
     */
    public TicTacToe(Player p1, Player p2) {
        // Validation des paramètres du constructeur
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("❌ Les joueurs ne peuvent pas être null (p1: " + p1 + ", p2: " + p2 + ")");
        }
        
        this.player1 = p1;
        this.player2 = p2;
        this.currentPlayer = player1;
        this.board = new Board();
    }

    /**
     * Récupère le plateau de jeu.
     * @return le plateau de jeu actuel
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Récupère le joueur dont c'est actuellement le tour.
     * @return le joueur actuel
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Vérifie si la partie est terminée (victoire ou match nul).
     * @return true si la partie est finie, false sinon
     */
    public boolean isOver() {
        return getWinner() != null || board.isFull();
    }

    /**
     * Détermine s'il y a un gagnant en vérifiant lignes, colonnes et diagonales.
     * @return le joueur gagnant, ou null si pas de gagnant
     */
    public Player getWinner() {
        // Vérification des lignes
        for (int i = 0; i < Board.SIZE; i++) {
            if (!board.getCellRepresentation(i, 0).equals("   ") &&
                    board.getCellRepresentation(i, 0).equals(board.getCellRepresentation(i, 1)) &&
                    board.getCellRepresentation(i, 0).equals(board.getCellRepresentation(i, 2))) {
                return board.getCellRepresentation(i, 0).equals(player1.getRepresentation()) ? player1 : player2;
            }
        }

        // Vérification des colonnes
        for (int j = 0; j < Board.SIZE; j++) {
            if (!board.getCellRepresentation(0, j).equals("   ") &&
                    board.getCellRepresentation(0, j).equals(board.getCellRepresentation(1, j)) &&
                    board.getCellRepresentation(0, j).equals(board.getCellRepresentation(2, j))) {
                return board.getCellRepresentation(0, j).equals(player1.getRepresentation()) ? player1 : player2;
            }
        }

        // Diagonales
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

    /**
     * Tente de jouer un coup à la position spécifiée.
     * @param row la ligne où jouer (0-2)
     * @param col la colonne où jouer (0-2)
     * @return true si le coup a été joué avec succès, false sinon
     */
    public boolean playMove(int row, int col) {
        // Validation des coordonnées avant d'appeler board.isCellEmpty()
        try {
            if (!board.isCellEmpty(row, col)) {
                return false; // Case occupée
            }
            board.setOwner(row, col, currentPlayer);
            return true; // Coup joué avec succès
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Coup invalide : " + e.getMessage());
            return false; // Coordonnées invalides
        }
    }

    /**
     * Change le joueur actuel pour passer au tour suivant.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}