package core;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private String representation;
    private boolean isArtificial;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public Player(String representation, boolean isArtificial) {
        
        if (representation == null || representation.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ La représentation du joueur ne peut pas être null ou vide");
        }
        
        this.representation = representation;
        this.isArtificial = isArtificial;
    }

    public String getRepresentation() {
        return representation;
    }

    public boolean isArtificial() {
        return isArtificial;
    }

    public int[] getMove(Board board) {
        
        if (board == null) {
            throw new IllegalArgumentException("❌ Le plateau de jeu ne peut pas être null");
        }
        
        int row = -1, col = -1;

        if (isArtificial) {
            
            do {
                row = random.nextInt(Board.SIZE);
                col = random.nextInt(Board.SIZE);
            } while (!board.isCellEmpty(row, col));

            System.out.println(getRepresentation() + " (IA) joue en (" + row + "," + col + ")");
        } else {
            
            boolean validMove = false;

            while (!validMove) {
                try {
                    System.out.println(getRepresentation() + " à vous de jouer :");
                    
                    System.out.print("Entrez la ligne (0-2) : ");
                    String rowInput = scanner.nextLine();
                    row = Integer.parseInt(rowInput);
                    
                    System.out.print("Entrez la colonne (0-2) : ");
                    String colInput = scanner.nextLine();
                    col = Integer.parseInt(colInput);

                   
                    if (row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE) {
                        if (board.isCellEmpty(row, col)) {
                            validMove = true;
                        } else {
                            System.out.println("❌ Case déjà occupée !");
                        }
                    } else {
                        System.out.println("❌ Coordonnées invalides ! Utilisez des valeurs entre 0 et 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Veuillez entrer des nombres valides !");
                } catch (Exception e) {
                    System.out.println("❌ Erreur lors de la saisie : " + e.getMessage());
                }
            }
        }

        return new int[]{row, col};
    }
}