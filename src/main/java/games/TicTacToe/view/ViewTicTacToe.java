package games.TicTacToe.view;

import core.Board;
import core.Player;

import java.util.Scanner;

public class ViewTicTacToe {

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("=== Bienvenue dans le jeu du TicTacToe ===");
    }

    public int getModeChoice() {
        System.out.println("Choisissez un mode de jeu :");
        System.out.println("1. Joueur vs Joueur");
        System.out.println("2. Joueur vs IA");
        System.out.println("3. IA vs IA");
        System.out.print("Votre choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void showBoard(Board board) {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                String cell = board.getCellRepresentation(i, j);
                System.out.print(cell.equals("   ") ? " . " : cell);
            }
            System.out.println();
        }
    }

    public int[] askMove(Player player, Board board) {
        int row, col;
        do {
            System.out.println(player.getRepresentation() + " à vous de jouer !");
            System.out.print("Entrez la ligne (0-" + (board.getRows() - 1) + ") : ");
            row = Integer.parseInt(scanner.nextLine());
            System.out.print("Entrez la colonne (0-" + (board.getCols() - 1) + ") : ");
            col = Integer.parseInt(scanner.nextLine());

            if (row < 0 || row >= board.getRows() || col < 0 || col >= board.getCols()) {
                System.out.println("Coordonnées invalides, réessayez !");
            } else if (!board.isCellEmpty(row, col)) {
                System.out.println("Case déjà occupée, réessayez !");
            } else {
                break;
            }
        } while (true);

        return new int[]{row, col};
    }

    public void showInvalidMove() {
        System.out.println("Coup invalide, réessayez !");
    }

    public void showWinner(Player player) {
        System.out.println("Le joueur " + player.getRepresentation() + " a gagné !");
    }

    public void showDraw() {
        System.out.println("Match nul !");
    }
}