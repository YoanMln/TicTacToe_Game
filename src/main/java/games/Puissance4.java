package games;

import core.Board;
import core.Game;
import core.Player;
import core.View;
import java.util.Scanner;

public class Puissance4 extends Game {
    public static final int ROW = 6;
    public static final int COL = 7;

    private Board board;
    private Player player1;
    private Player player2;
    private View view;
    private Scanner scanner;

    public Puissance4() {
        board = new Board(ROW, COL);
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
        view.showMessage("Bienvenue dans Puissance 4 !");
        view.showMessage("Joueur 1 : " + player1.getRepresentation());
        view.showMessage("Joueur 2 : " + player2.getRepresentation());
        view.showBoard(board);
        play();
    }

    @Override
    public void play() {
        Player currentPlayer = player1;

        while (!isOver()) {
            view.showTurn(currentPlayer);
            int col = getMove(currentPlayer);
            dropToken(col, currentPlayer);
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

    private int getMove(Player player) {
        int col = -1;
        boolean validMove = false;

        if (player.isArtificial()) {

            do {
                col = (int) (Math.random() * COL);
            } while (board.isColumnFull(col));
            System.out.println(player.getRepresentation() + " (IA) joue dans la colonne " + col);
            validMove = true;
        } else {

            while (!validMove) {
                try {
                    System.out.print(player.getRepresentation() + " entrez le numéro de la colonne (0-" + (COL - 1) + ") : ");
                    col = Integer.parseInt(scanner.nextLine());

                    if (col >= 0 && col < COL && !board.isColumnFull(col)) {
                        validMove = true;
                    } else {
                        System.out.println("Colonne invalide ou pleine !");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre valide !");
                }
            }
        }
        return col;
    }


    private void dropToken(int col, Player player) {
        for (int row = ROW - 1; row >= 0; row--) {
            if (board.isCellEmpty(row, col)) {
                board.setOwner(row, col, player);
                break;
            }
        }
    }

    private boolean isOver() {
        return getWinner() != null || board.isFull();
    }

    private Player getWinner() {
        // Vérifie horizontal, vertical, diagonales
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                String repr = board.getCellRepresentation(row, col);
                if (!repr.equals("   ")) {
                    if (checkDirection(row, col, 0, 1, repr) || // horizontal
                            checkDirection(row, col, 1, 0, repr) || // vertical
                            checkDirection(row, col, 1, 1, repr) || // diagonale \
                            checkDirection(row, col, 1, -1, repr)) { // diagonale /
                        return repr.equals(player1.getRepresentation()) ? player1 : player2;
                    }
                }
            }
        }
        return null;
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol, String repr) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int r = row + i * dRow;
            int c = col + i * dCol;
            if (r >= 0 && r < ROW && c >= 0 && c < COL && board.getCellRepresentation(r, c).equals(repr)) {
                count++;
            } else {
                break;
            }
        }
        return count == 4;
    }
}