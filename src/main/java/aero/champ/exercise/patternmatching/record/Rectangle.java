package aero.champ.exercise.patternmatching.record;

public record Rectangle (Point topLeft, double width, double height) implements Shape{
    @Override
    public double area() {
        return width * height;
    }
}
