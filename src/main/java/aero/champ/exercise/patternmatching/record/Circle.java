package aero.champ.exercise.patternmatching.record;

public record Circle(Point center, double radius) implements Shape {
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
