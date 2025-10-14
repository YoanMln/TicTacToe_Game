package games;

import core.Board;
import core.Game;
import core.Player;
import core.View;
import java.util.Scanner;

public class Gomoku extends Game {
    public static final int SIZE = 15;
    public static final int ALIGN = 5;

    private Board board;
    private Player player1;
    private Player player2;
    private View view;
    private Scanner scanner;

    public Gomoku() {
        board = new Board(SIZE, SIZE);
        view = new View();
        scanner = new Scanner(System.in);

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

    @Override
    public void start() {
        view.showMessage("Bienvenue dans GoMoku !");
        view.showBoard(board);
        play();
    }

    @Override
    public void play() {
        Player currentPlayer = player1;

        while (!isOver()) {
            view.showTurn(currentPlayer);
            int[] move = currentPlayer.getMove(board);
            board.setOwner(move[0], move[1], currentPlayer);
            view.showBoard(board);

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        Player winner = getWinner();
        if (winner != null) {
            view.showVictory(winner);
        } else {
            view.showDraw();
        }
    }

    private boolean isOver() {
        return getWinner() != null || board.isFull();
    }

    private Player getWinner() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                String repr = board.getCellRepresentation(row, col);
                if (!repr.equals("   ")) {
                    if (checkDirection(row, col, 0, 1, repr) ||
                            checkDirection(row, col, 1, 0, repr) ||
                            checkDirection(row, col, 1, 1, repr) ||
                            checkDirection(row, col, 1, -1, repr)) {
                        return repr.equals(player1.getRepresentation()) ? player1 : player2;
                    }
                }
            }
        }
        return null;
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol, String repr) {
        int count = 0;
        for (int i = 0; i < ALIGN; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < SIZE && c >= 0 && c < SIZE &&
                    board.getCellRepresentation(r, c).equals(repr)) {
                count++;
            } else {
                break;
            }
        }
        return count == ALIGN;
    }
}