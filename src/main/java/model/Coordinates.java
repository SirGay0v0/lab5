package model;

public class Coordinates implements Comparable<Coordinates> {
    private Double x;
    private Long y;

    public Coordinates(Double x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinates other) {
        int compareX = this.x.compareTo(other.x);
        if (compareX != 0) {
            return compareX;
        }
        return this.y.compareTo(other.y);
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + '}';
    }
}
