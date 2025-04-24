package aero.champ.exercise.patternmatching.record;

public record Triangle(Point p1, Point p2, Point p3) implements Shape {
    @Override
    public double area() {
        // Using the Shoelace formula for simplicity
        return 0.5 * Math.abs(
                p1.x() * (p2.y() - p3.y()) +
                p2.x() * (p3.y() - p1.y()) +
                p3.x() * (p1.y() - p2.y())
        );
    }
}
