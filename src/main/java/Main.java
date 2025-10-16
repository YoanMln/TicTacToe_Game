

import games.TicTacToe.controller.TicTacToeController;
import games.Puissance4;
import games.Gomoku;
import core.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Choisissez un jeu ===");
        System.out.println("1. TicTacToe");
        System.out.println("2. Puissance 4");
        System.out.println("3. Gomoku");
        System.out.print("Votre choix : ");

        int choix = Integer.parseInt(scanner.nextLine());
        Game game = null;

        switch (choix) {
            case 1 -> {
                TicTacToeController controller = new TicTacToeController();
                controller.startGame();
                return;
            }
            case 2 -> game = new Puissance4();
            case 3 -> game = new Gomoku();
            default -> System.out.println("Choix invalide");
        }

        if (game != null) game.start();
    }
}