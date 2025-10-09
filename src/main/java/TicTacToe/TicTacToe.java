package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    private Board board;
    private Player player1;
    private Player player2;
    private Scanner scanner;

    public TicTacToe() {
        board = new Board();
        scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans TicTacToe !");
        System.out.println("Choisissez le mode de jeu :");
        System.out.println("1. Humain vs Humain");
        System.out.println("2. Humain vs IA");
        System.out.println("3. IA vs IA");
        System.out.print("Votre choix : ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> {
                player1 = new Player(" X ", false);
                player2 = new Player(" O ", false);
            }
            case 2 -> {
                player1 = new Player(" X ", false);
                player2 = new Player(" O ", true);
            }
            case 3 -> {
                player1 = new Player(" X ", true);
                player2 = new Player(" O ", true);
            }
            default -> {
                System.out.println("Choix invalide, mode par défaut : Humain vs IA");
                player1 = new Player(" X ", false);
                player2 = new Player(" O ", true);
            }
        }
    }

    public void start() {
        System.out.println("Joueur 1 : " + player1.getRepresentation());
        System.out.println("Joueur 2 : " + player2.getRepresentation());
        board.display();
        play();
    }

    public void play() {
        Player currentPlayer = player1;

        while (!isOver()) {
            System.out.println("C'est au tour de " + currentPlayer.getRepresentation());
            int[] move = currentPlayer.getMove(board); // 🔹 On utilise getMove() du Player
            board.setOwner(move[0], move[1], currentPlayer);
            board.display();

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        Player winner = getWinner();
        if (winner != null) {
            System.out.println(winner.getRepresentation() + " a gagné !");
        } else {
            System.out.println("Match nul !");
        }
    }

    public boolean isOver() {
        return getWinner() != null || board.isFull();
    }

    public Player getWinner() {
        // Vérifie les lignes
        for (int i = 0; i < Board.SIZE; i++) {
            if (!board.getCell(i, 0).getRepresentation().equals("   ") &&
                    board.getCell(i, 0).getRepresentation().equals(board.getCell(i, 1).getRepresentation()) &&
                    board.getCell(i, 0).getRepresentation().equals(board.getCell(i, 2).getRepresentation())) {
                return board.getCell(i, 0).getRepresentation().equals(player1.getRepresentation()) ? player1 : player2;
            }
        }

        // Vérifie les colonnes
        for (int j = 0; j < Board.SIZE; j++) {
            if (!board.getCell(0, j).getRepresentation().equals("   ") &&
                    board.getCell(0, j).getRepresentation().equals(board.getCell(1, j).getRepresentation()) &&
                    board.getCell(0, j).getRepresentation().equals(board.getCell(2, j).getRepresentation())) {
                return board.getCell(0, j).getRepresentation().equals(player1.getRepresentation()) ? player1 : player2;
            }
        }

        // Vérifie les diagonales
        if (!board.getCell(0, 0).getRepresentation().equals("   ") &&
                board.getCell(0, 0).getRepresentation().equals(board.getCell(1, 1).getRepresentation()) &&
                board.getCell(0, 0).getRepresentation().equals(board.getCell(2, 2).getRepresentation())) {
            return board.getCell(0, 0).getRepresentation().equals(player1.getRepresentation()) ? player1 : player2;
        }

        if (!board.getCell(0, 2).getRepresentation().equals("   ") &&
                board.getCell(0, 2).getRepresentation().equals(board.getCell(1, 1).getRepresentation()) &&
                board.getCell(0, 2).getRepresentation().equals(board.getCell(2, 0).getRepresentation())) {
            return board.getCell(0, 2).getRepresentation().equals(player1.getRepresentation()) ? player1 : player2;
        }

        return null;
    }
}