package core;

import java.util.Random;
import java.util.Scanner;

/**
 * Représente un joueur dans le jeu (humain ou IA).
 * Gère la représentation du joueur et la saisie des coups.
 */
public class Player {
    private String representation;
    private boolean isArtificial;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    /**
     * Constructeur pour créer un joueur.
     * @param representation le symbole du joueur (ex: " X ", " O ")
     * @param isArtificial true si c'est une IA, false si c'est un humain
     * @throws IllegalArgumentException si la représentation est null ou vide
     */
    public Player(String representation, boolean isArtificial) {
        // Validation du paramètre representation
        if (representation == null || representation.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ La représentation du joueur ne peut pas être null ou vide");
        }
        
        this.representation = representation;
        this.isArtificial = isArtificial;
    }

    /**
     * Récupère la représentation textuelle du joueur.
     * @return le symbole du joueur
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * Indique si le joueur est une IA ou un humain.
     * @return true si c'est une IA, false si c'est un humain
     */
    public boolean isArtificial() {
        return isArtificial;
    }

    /**
     * Demande un coup au joueur (humain ou IA).
     * Pour l'IA : génère un coup aléatoire valide.
     * Pour l'humain : demande la saisie avec validation.
     * @param board le plateau de jeu pour vérifier les coups valides
     * @return un tableau de 2 entiers [ligne, colonne] représentant le coup
     * @throws IllegalArgumentException si le plateau est null
     */
    public int[] getMove(Board board) {
        // Validation du paramètre board
        if (board == null) {
            throw new IllegalArgumentException("❌ Le plateau de jeu ne peut pas être null");
        }
        
        int row = -1, col = -1;

        if (isArtificial) {
            // IA : générer un coup aléatoirement
            do {
                row = random.nextInt(Board.SIZE);
                col = random.nextInt(Board.SIZE);
            } while (!board.isCellEmpty(row, col));

            System.out.println(getRepresentation() + " (IA) joue en (" + row + "," + col + ")");
        } else {
            // Joueur humain : demander le coup avec gestion d'erreurs
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

                    // Validation des limites
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