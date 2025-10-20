package core;

/**
 * Représente une cellule individuelle du plateau de jeu.
 * Chaque cellule peut être vide ou contenir la représentation d'un joueur.
 */
public class Cell {
    private String representation;

    /**
     * Constructeur par défaut qui crée une cellule vide.
     */
    public Cell() {
        this.representation = "   ";
    }

    /**
     * Récupère la représentation textuelle de la cellule.
     * @return la représentation de la cellule (3 espaces si vide, symbole du joueur sinon)
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * Modifie la représentation de la cellule.
     * @param representation la nouvelle représentation (symbole du joueur)
     * @throws IllegalArgumentException si la représentation est null
     */
    public void setRepresentation(String representation) {
        // Validation du paramètre
        if (representation == null) {
            throw new IllegalArgumentException("❌ La représentation de la cellule ne peut pas être null");
        }
        
        this.representation = representation;
    }

    /**
     * Vérifie si la cellule est vide.
     * @return true si la cellule est vide (contient 3 espaces), false sinon
     */
    public boolean isEmpty() {
        return representation.equals("   ");
    }
}