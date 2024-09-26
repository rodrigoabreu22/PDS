package lab02;

public class Word {
    private String word;
    private int size;
    private Position start;
    private boolean found;
    private Way direction;
    private boolean inserted;

    public Word(String word) {
        this.word = word;
        this.size = word.length();
        this.start = null;
        this.direction = null;
        this.found = false;
        this.inserted = false;
    }

    public String getWord() {
        return word;
    }

    public int getSize() {
        return size;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getStart() {
        return start;
    }

    public void setDirection(Way direction) {
        this.direction = direction;
    }

    public Way getDirection() {
        return direction;
    }

    public void Found() {
        this.found = true;
    }

    public boolean isFound() {
        return found;
    }

    public boolean isInserted() {
        return inserted;
    }

    public void setInserted() {
        this.inserted = true;
    }

    public String toString() {
        return String.format("%-14s %-5d %-8s %-10s\n", this.getWord(), this.getSize(), this.getStart(), this.getDirection());
    }
}
