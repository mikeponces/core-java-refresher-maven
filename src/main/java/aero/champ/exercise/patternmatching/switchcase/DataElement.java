package aero.champ.exercise.patternmatching.switchcase;

import java.time.LocalDate;
import java.util.List;

public sealed interface DataElement permits TextElement, NumericElement, DateElement, ListElement {
    String getDescription();

    static void processElement(Object element) {
// Traditional approach with instanceof and casting
        System.out.println("Traditional approach:");
        if (element instanceof TextElement textElement) {
            System.out.println("Found text: " + textElement.getText());
        } else if (element instanceof NumericElement numericElement) {
            System.out.println("Found number: " + numericElement.getValue());
        } else if (element instanceof DateElement dateElement) {
            System.out.println("Found date: " + dateElement.getDate());
        } else if (element instanceof ListElement listElement) {
            System.out.println("Found list with " + listElement.getElements().size() + " elements");
        } else {
            System.out.println("Unknown element type: " + element.getClass().getName());
        }
// Pattern matching approach (Java 16+)
        System.out.println("\nPattern matching approach:");
        if (element instanceof TextElement textElement) {
            System.out.println("Found text: " + textElement.getText());
        } else if (element instanceof NumericElement numericElement) {
            System.out.println("Found number: " + numericElement.getValue());
        } else if (element instanceof DateElement dateElement) {
            System.out.println("Found date: " + dateElement.getDate());
        } else if (element instanceof ListElement listElement) {
            System.out.println("Found list with " + listElement.getElements().size() + " elements");
        } else {
            System.out.println("Unknown element type: " + element.getClass().getName());
        }
    }

    static String analyzeData(Object data) {
        return switch (data) {
            case TextElement txt -> "Text analysis: " + txt.getText().length() + " characters";
            case NumericElement num -> {
                double value = num.getValue();
                String analysis = "Numeric analysis: ";
                if (value < 0) {
                    analysis += "negative number";
                } else if (value == 0) {
                    analysis += "zero";
                } else if (value < 10) {
                    analysis += "single digit positive";
                } else {
                    analysis += "multi-digit positive";
                }
                yield analysis;
            }
            case DateElement date -> {
                LocalDate today = LocalDate.now();
                if (date.getDate().isEqual(today)) {
                    yield "Date analysis: Today";
                } else if (date.getDate().isBefore(today)) {
                    yield "Date analysis: Past date";
                } else {
                    yield "Date analysis: Future date";
                }
            }
            case ListElement list -> {
                int size = list.getElements().size();
                yield "List analysis: " + size + " elements - " +
                        switch (size) {
                            case 0 -> "empty list";
                            case 1 -> "singleton list";
                            case 2, 3, 4 -> "small list";
                            default -> "large list";
                        };
            }
            case String s -> "String analysis: " + s;
            case null -> "Null value detected";
            default -> "Unknown data type: " + data.getClass().getName();
        };
    }

    static void main(String[] args) {
        processElement(new TextElement("Hello, Pattern Matching!"));
        processElement(new NumericElement(42.5));
        processElement(new DateElement(LocalDate.now()));
        processElement(new ListElement(List.of(
                new TextElement("Item 1"),
                new NumericElement(123)
        )));
        processElement("Just a plain string");

        // In main method
        System.out.println("\n--- Data Analysis Results ---");
        System.out.println(analyzeData(new TextElement("Pattern Matching")));
        System.out.println(analyzeData(new NumericElement(-10.5)));
        System.out.println(analyzeData(new NumericElement(5)));
        System.out.println(analyzeData(new NumericElement(42.5)));
        System.out.println(analyzeData(new DateElement(LocalDate.now())));
        System.out.println(analyzeData(new DateElement(LocalDate.now().plusDays(5))));
        System.out.println(analyzeData(new ListElement(List.of())));
        System.out.println(analyzeData(new ListElement(List.of(new TextElement("Single item")))));
        System.out.println(analyzeData(new ListElement(List.of(
                        new TextElement("Item 1"),
                        new NumericElement(123),
                        new DateElement(LocalDate.now())
                ))));
        System.out.println(analyzeData("Raw string data"));
        System.out.println(analyzeData(null));
        System.out.println(analyzeData(new Object()));
    }
}
