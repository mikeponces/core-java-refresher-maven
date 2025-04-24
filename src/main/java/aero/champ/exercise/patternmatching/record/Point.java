package aero.champ.exercise.patternmatching.record;

public record Point(int x, int y) {
    public boolean isOrigin() {
        return x == 0 && y == 0;
    }
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}
