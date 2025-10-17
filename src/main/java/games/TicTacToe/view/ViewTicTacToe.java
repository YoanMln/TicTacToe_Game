package games.TicTacToe.view;

import core.Board;
import core.Player;
import core.View;

import java.util.Scanner;

public class ViewTicTacToe extends View {

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("=== Bienvenue dans le jeu du TicTacToe ===");
    }

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

    @Override
    public void showBoard(Board board) {
        // Validation du paramètre
        if (board == null) {
            System.out.println("❌ Erreur : plateau de jeu non initialisé");
            return;
        }
        
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                String cell = board.getCellRepresentation(i, j);
                System.out.print(cell.equals("   ") ? " . " : cell);
            }
            System.out.println();
        }
    }

    public int[] askMove(Player player, Board board) {
       
        if (player == null) {
            System.out.println("❌ Erreur : joueur non défini");
            return new int[]{-1, -1}; 
        }
        if (board == null) {
            System.out.println("❌ Erreur : plateau de jeu non initialisé");
            return new int[]{-1, -1}; 
        }
        
        int row = -1, col = -1;
        boolean validMove = false;
        
        while (!validMove) {
            try {
                System.out.println(player.getRepresentation() + " à vous de jouer !");
                
                
                System.out.print("Entrez la ligne (0-" + (board.getRows() - 1) + ") : ");
                row = Integer.parseInt(scanner.nextLine());
                
                
                System.out.print("Entrez la colonne (0-" + (board.getCols() - 1) + ") : ");
                col = Integer.parseInt(scanner.nextLine());

                
                if (row < 0 || row >= board.getRows() || col < 0 || col >= board.getCols()) {
                    System.out.println("❌ Coordonnées invalides, réessayez !");
                } else if (!board.isCellEmpty(row, col)) {
                    System.out.println("❌ Case déjà occupée, réessayez !");
                } else {
                    validMove = true; 
                }
                
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer des nombres valides !");
            }
        }

        return new int[]{row, col};
    }

    public void showInvalidMove() {
        System.out.println("❌ Coup invalide, réessayez !");
    }

    public void showWinner(Player player) {
        if (player == null) {
            System.out.println("❌ Erreur : impossible d'afficher le gagnant");
            return;
        }
        System.out.println("🎉 Le joueur " + player.getRepresentation() + " a gagné !");
    }

    public void showDraw() {
        System.out.println("🤝 Match nul !");
    }
}