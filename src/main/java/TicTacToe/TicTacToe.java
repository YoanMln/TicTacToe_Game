package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    private Board board;
    private Player player1;
    private Player player2;
    private Scanner scanner;

    public TicTacToe() {
        board = new Board();
        player1 = new Player(" X ");
        player2 = new Player(" O ");
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Bienvenue dans TicTacToe ");
        System.out.println("Joueur 1 : X");
        System.out.println("Joueur 2 : O");
        board.display();
        play();
    }

    public void play() {
        Player currentPlayer = player1;

        while (!isOver()) {
            System.out.println("C'est au tour de " + currentPlayer.getRepresentation());
            int[] move = getMove(currentPlayer);
            setOwner(move[0], move[1], currentPlayer);
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

    public int[] getMove(Player player) {
        int row = -1;
        int col = -1;
        boolean validMove = false;

        while (!validMove) {
            try {
                System.out.println(player.getRepresentation() + " à vous de jouer :");
                System.out.print("Entrez la ligne (0-2) : ");
                row = Integer.parseInt(scanner.nextLine());
                System.out.print("Entrez la colonne (0-2) : ");
                col = Integer.parseInt(scanner.nextLine());

                if (row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE) {
                    if (board.isCellEmpty(row, col)) {
                        validMove = true;
                    } else {
                        System.out.println("Case déjà occupée. Réessayez !");
                    }
                } else {
                    System.out.println("Coordonnées invalides, entrez des nombres entre 0 et 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide !");
            }
        }
        return new int[]{row, col};
    }

    public void setOwner(int row, int col, Player player) {
        board.setOwner(row, col, player);
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

        // Vérifie diagonales
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