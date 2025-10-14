package core;

public class View {
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showBoard(Board board) {
        String separator = "-".repeat(board.getCols() * 4 + 1);
        System.out.println(separator);
        for (int i = 0; i < board.getRows(); i++) {
            System.out.print("| ");
            for (int j = 0; j < board.getCols(); j++) {
                System.out.print(board.getCellRepresentation(i, j));
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println(separator);
        }

        System.out.print("  ");
        for (int j = 0; j < board.getCols(); j++) {
            System.out.printf(" %2d ", j);
        }
        System.out.println();
    }

    public void showTurn(Player player) {
        System.out.println(player.getRepresentation() + " à vous de jouer !");
    }

    public void showVictory(Player player) {
        System.out.println(player.getRepresentation() + " a gagné !");
    }

    public void showDraw() {
        System.out.println("Match nul !");
    }
}