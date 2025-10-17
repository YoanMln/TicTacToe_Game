package core;

public class Cell {
    private String representation;

    public Cell() {
        this.representation = "   ";
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
       
        if (representation == null) {
            throw new IllegalArgumentException("❌ La représentation de la cellule ne peut pas être null");
        }
        
        this.representation = representation;
    }

    public boolean isEmpty() {
        return representation.equals("   ");
    }
}