package games.TicTacToe.view;

import core.Board;
import core.Player;
import core.View;

import java.util.Scanner;

/**
 * Vue spécialisée pour le jeu TicTacToe.
 * Gère l'affichage et les interactions utilisateur spécifiques au TicTacToe.
 */
public class ViewTicTacToe extends View {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Affiche le message de bienvenue du jeu TicTacToe.
     */
    public void showWelcome() {
        System.out.println("=== Bienvenue dans le jeu du TicTacToe ===");
    }

    /**
     * Demande à l'utilisateur de choisir un mode de jeu avec validation.
     * Gère les erreurs de saisie et redemande jusqu'à obtenir une réponse valide.
     * @return le choix du mode (1=Joueur vs Joueur, 2=Joueur vs IA, 3=IA vs IA)
     */
    public int getModeChoice() {
        int choice = -1;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.println("Choisissez un mode de jeu :");
                System.out.println("1. Joueur vs Joueur");
                System.out.println("2. Joueur vs IA");
                System.out.println("3. IA vs IA");
                System.out.print("Votre choix : ");
                
                choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= 3) {
                    validInput = true;
                } else {
                    System.out.println("❌ Veuillez choisir un nombre entre 1 et 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un nombre valide !");
            }
        }
        
        return choice;
    }

    /**
     * Affiche le plateau de jeu TicTacToe avec un format spécialisé.
     * @param board le plateau à afficher
     */
    @Override
    public void showBoard(Board board) {
        // Validation du paramètre
        if (board == null) {
            System.out.println("❌ Erreur : plateau de jeu non initialisé");
            return;
        }
        
        // Affichage des numéros de colonnes
        System.out.println("\n    0   1   2");
        System.out.println("  ┌───┬───┬───┐");
        
        for (int i = 0; i < board.getRows(); i++) {
            // Affichage du numéro de ligne
            System.out.print(i + " │");
            for (int j = 0; j < board.getCols(); j++) {
                String cell = board.getCellRepresentation(i, j);
                System.out.print(cell.equals("   ") ? " . " : cell);
                System.out.print("│");
            }
            System.out.println();
            if (i < board.getRows() - 1) {
                System.out.println("  ├───┼───┼───┤");
            }
        }
        System.out.println("  └───┴───┴───┘");
    }

    /**
     * Demande au joueur humain de saisir son coup avec validation complète.
     * Gère les erreurs de saisie, les coordonnées invalides et les cases occupées.
     * @param player le joueur qui doit jouer
     * @param board le plateau de jeu pour valider le coup
     * @return un tableau [ligne, colonne] représentant le coup choisi, ou [-1,-1] en cas d'erreur
     */
    public int[] askMove(Player player, Board board) {
        // Validation des paramètres
        if (player == null) {
            System.out.println("❌ Erreur : joueur non défini");
            return new int[]{-1, -1}; // Valeurs d'erreur
        }
        if (board == null) {
            System.out.println("❌ Erreur : plateau de jeu non initialisé");
            return new int[]{-1, -1}; // Valeurs d'erreur
        }
        
        int row = -1, col = -1;
        boolean validMove = false;
        
        while (!validMove) {
            try {
                System.out.println(player.getRepresentation() + " à vous de jouer !");
                
                // Demande de la ligne avec gestion d'exception
                System.out.print("Entrez la ligne (0-" + (board.getRows() - 1) + ") : ");
                row = Integer.parseInt(scanner.nextLine());
                
                // Demande de la colonne avec gestion d'exception
                System.out.print("Entrez la colonne (0-" + (board.getCols() - 1) + ") : ");
                col = Integer.parseInt(scanner.nextLine());

                // Validation des coordonnées
                if (row < 0 || row >= board.getRows() || col < 0 || col >= board.getCols()) {
                    System.out.println("❌ Coordonnées invalides, réessayez !");
                } else if (!board.isCellEmpty(row, col)) {
                    System.out.println("❌ Case déjà occupée, réessayez !");
                } else {
                    validMove = true; // Mouvement valide
                }
                
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer des nombres valides !");
            }
        }

        return new int[]{row, col};
    }

    /**
     * Affiche un message indiquant qu'un coup est invalide.
     */
    public void showInvalidMove() {
        System.out.println("❌ Coup invalide, réessayez !");
    }

    /**
     * Affiche le message de victoire pour le joueur gagnant.
     * @param player le joueur qui a gagné
     */
    public void showWinner(Player player) {
        if (player == null) {
            System.out.println("❌ Erreur : impossible d'afficher le gagnant");
            return;
        }
        System.out.println("🎉 Le joueur " + player.getRepresentation() + " a gagné !");
    }

    /**
     * Affiche le message de match nul.
     */
    public void showDraw() {
        System.out.println("🤝 Match nul !");
    }
}