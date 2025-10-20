package core;

/**
 * Représente le plateau de jeu pour les jeux de plateau comme TicTacToe, Puissance 4, etc.
 * Gère un tableau 2D de cellules avec validation des coordonnées.
 */
public class Board {
    private int rows;
    private int cols;
    private Cell[][] board;

    public static final int SIZE = 3; // pour TicTacToe

    /**
     * Constructeur par défaut qui crée un plateau 3x3 pour TicTacToe.
     */
    public Board() {
        this(3, 3);
    }

    /**
     * Constructeur qui crée un plateau avec des dimensions spécifiées.
     * @param rows le nombre de lignes (doit être > 0)
     * @param cols le nombre de colonnes (doit être > 0)
     * @throws IllegalArgumentException si les dimensions sont négatives ou nulles
     */
    public Board(int rows, int cols) {
        // Validation des paramètres du constructeur
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("❌ Les dimensions du plateau doivent être positives (rows: " + rows + ", cols: " + cols + ")");
        }
        
        this.rows = rows;
        this.cols = cols;
        board = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Vérifie si une cellule à la position donnée est vide.
     * @param row la ligne de la cellule (0 à rows-1)
     * @param col la colonne de la cellule (0 à cols-1)
     * @return true si la cellule est vide, false sinon
     * @throws IllegalArgumentException si les coordonnées sont hors limites
     */
    public boolean isCellEmpty(int row, int col) {
        // Validation des coordonnées
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        return board[row][col].getRepresentation().equals("   ");
    }

    /**
     * Définit le propriétaire d'une cellule (place le symbole du joueur).
     * @param row la ligne de la cellule (0 à rows-1)
     * @param col la colonne de la cellule (0 à cols-1)
     * @param player le joueur qui prend possession de la cellule
     * @throws IllegalArgumentException si les coordonnées sont hors limites ou si le joueur est null
     */
    public void setOwner(int row, int col, Player player) {
        // Validation des coordonnées
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        // Validation du joueur
        if (player == null) {
            throw new IllegalArgumentException("❌ Le joueur ne peut pas être null");
        }
        
        board[row][col].setRepresentation(player.getRepresentation());
    }

    /**
     * Récupère l'objet Cell à la position donnée.
     * @param row la ligne de la cellule (0 à rows-1)
     * @param col la colonne de la cellule (0 à cols-1)
     * @return l'objet Cell à cette position
     * @throws IllegalArgumentException si les coordonnées sont hors limites
     */
    public Cell getCell(int row, int col) {
        // Validation des coordonnées
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        return board[row][col];
    }

    /**
     * Vérifie si le plateau est complètement rempli.
     * @return true si toutes les cellules sont occupées, false sinon
     */
    public boolean isFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Récupère la représentation textuelle d'une cellule.
     * @param row la ligne de la cellule (0 à rows-1)
     * @param col la colonne de la cellule (0 à cols-1)
     * @return la représentation textuelle de la cellule
     * @throws IllegalArgumentException si les coordonnées sont hors limites
     */
    public String getCellRepresentation(int row, int col) {
        // Validation des coordonnées
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Coordonnées invalides : (" + row + "," + col + ") - Limites : (0-" + (rows-1) + ", 0-" + (cols-1) + ")");
        }
        
        return board[row][col].getRepresentation();
    }

    /**
     * Vérifie si une colonne est pleine (utilisé pour Puissance 4).
     * @param col la colonne à vérifier (0 à cols-1)
     * @return true si la colonne est pleine, false sinon
     * @throws IllegalArgumentException si la colonne est hors limites
     */
    public boolean isColumnFull(int col) {
        // Validation de la colonne
        if (col < 0 || col >= cols) {
            throw new IllegalArgumentException("❌ Colonne invalide : " + col + " - Limites : (0-" + (cols-1) + ")");
        }
        
        return !isCellEmpty(0, col);
    }

    /**
     * Récupère le nombre de lignes du plateau.
     * @return le nombre de lignes
     */
    public int getRows() {
        return rows;
    }

    /**
     * Récupère le nombre de colonnes du plateau.
     * @return le nombre de colonnes
     */
    public int getCols() {
        return cols;
    }
}