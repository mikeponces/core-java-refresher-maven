package aero.champ.exercise.patternmatching.record;

import aero.champ.exercise.patternmatching.switchcase.ListElement;
import aero.champ.exercise.patternmatching.switchcase.NumericElement;
import aero.champ.exercise.patternmatching.switchcase.TextElement;

import java.util.List;

public sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();

    static String describeShape(Shape shape) {
        return switch (shape) {
// Using record pattern and guard for Circle
            case Circle(Point center, double radius) when center.isOrigin() ->
                    "Circle at origin with radius " + radius + " and area " + shape.area();
// Using record pattern for Circle
            case Circle(Point center, double radius) -> "Circle at point (" + center.x() + ", " + center.y() +
                    ") with radius " + radius + " and area " + shape.area();
// Using record pattern and guard for Rectangle
            case Rectangle(Point topLeft, double width, double height) when width ==
                    height -> "Square with side " + width + " and area " + shape.area();
// Using record pattern for Rectangle
            case Rectangle(Point topLeft, double w, double h) ->
                    "Rectangle with width " + w + ", height " + h + " and area " +
                            shape.area();
// Using record pattern for Triangle
            case Triangle(Point p1, Point p2, Point p3) -> "Triangle with points (" + p1.x() + ", " + p1.y() + "), " +
                    "(" + p2.x() + ", " + p2.y() + "), " +
                    "(" + p3.x() + ", " + p3.y() + ") and area " + shape.area();
// Default case (though unreachable with sealed interface)
            default -> "Unknown shape type";
        };
    }

    static String analyzeNestedStructure(Object data) {
        return switch (data) {
// Nested pattern with ListElement containing TextElement
//            case ListElement(List elements) when !elements.isEmpty()
//                    && (elements.get(0) instanceof TextElement text) -> "List starting with text: " + text;
// Nested pattern with record and primitive matching
            case Circle(Point(var x, var y), double radius) when x > 0 && y > 0 && radius
                    > 5 ->
                    "Large circle in the first quadrant";
// Nested record pattern matching
            case Rectangle(Point(var x, var y), var width, var height) when x == 0 && y ==
                    0 ->
                    "Rectangle at origin with dimensions " + width + "x" + height;
// Match any shape with an area over 100
            case Shape s when s.area() > 100 ->
                    "Large shape with area: " + s.area();
// Default case
            default -> "Structure doesn't match any nested pattern";
        };
    }

    static void main(String[] args) {
        System.out.println("\n--- Advanced Pattern Matching Examples ---");
        Circle originCircle = new Circle(new Point(0, 0), 5.0);
        Circle offsetCircle = new Circle(new Point(10, 10), 3.0);
        Rectangle square = new Rectangle(new Point(0, 0), 4.0, 4.0);
        Rectangle normalRectangle = new Rectangle(new Point(5, 5), 3.0, 7.0);
        Triangle triangle = new Triangle(new Point(0, 0), new Point(0, 3), new Point(4, 0));
        System.out.println(describeShape(originCircle));
        System.out.println(describeShape(offsetCircle));
        System.out.println(describeShape(square));
        System.out.println(describeShape(normalRectangle));
        System.out.println(describeShape(triangle));

        System.out.println("\n--- Nested Pattern Matching Examples ---");
        ListElement listWithText = new ListElement(List.of(
                new TextElement("First item"),
                new NumericElement(42)
        ));
        ListElement listWithoutText = new ListElement(List.of(
                new NumericElement(42),
                new TextElement("Second item")
        ));
        Circle largeCircle = new Circle(new Point(10, 10), 10.0);
        Circle smallCircle = new Circle(new Point(10, 10), 3.0);
        Rectangle originRectangle = new Rectangle(new Point(0, 0), 5.0, 8.0);
        Rectangle largeRectangle = new Rectangle(new Point(5, 5), 20.0, 20.0);
        System.out.println(analyzeNestedStructure(listWithText));
        System.out.println(analyzeNestedStructure(listWithoutText));
        System.out.println(analyzeNestedStructure(largeCircle));
        System.out.println(analyzeNestedStructure(smallCircle));
        System.out.println(analyzeNestedStructure(originRectangle));
        System.out.println(analyzeNestedStructure(largeRectangle));
    }
}
