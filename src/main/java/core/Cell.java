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
        this.representation = representation;
    }

    public boolean isEmpty() {
        return representation.equals("   ");
    }


}