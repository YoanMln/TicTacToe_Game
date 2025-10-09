import games.TicTacToe;
import core.Game;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez un jeu :");
        System.out.println("1. TicTacToe");
        System.out.println("2. Puissance 4");
        System.out.println("3. Morpion");

        int choix = Integer.parseInt(scanner.nextLine());
        Game game = null;

        switch (choix) {
            case 1 -> game = new TicTacToe();
            case 2 -> System.out.println("Puissance 4 non encore implémenté");
            case 3 -> System.out.println("Morpion non encore implémenté");
            default -> System.out.println("Choix invalide");
        }

        if (game != null) game.start();
    }
}