package aero.champ.exercise.patternmatching.switchcase;

public class PatternMatchingDemo {
    public static void main(String[] args) {
        System.out.println("Java Pattern Matching and Switch Expressions Lab");
// Code will be added here throughout the exercise
        demonstrateBasicSwitchExpression("Monday");
        demonstrateBasicSwitchExpression("Sunday");
        demonstrateBasicSwitchExpression("Holiday");

        demonstrateYield(1); // Winter
        demonstrateYield(4); // Spring
        demonstrateYield(7); // Summer
        demonstrateYield(10); // Fall
        demonstrateYield(13); // Invalid
    }

    public static void demonstrateBasicSwitchExpression(String day) {
// Traditional switch statement
        String traditionalResult;
        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                traditionalResult = "Weekday";
                break;
            case "Saturday":
            case "Sunday":
                traditionalResult = "Weekend";
                break;
            default:
                traditionalResult = "Invalid day";
        }
// Modern switch expression (Java 14+)
        String modernResult = switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
            case "Saturday", "Sunday" -> "Weekend";
            default -> "Invalid day";
        };
        System.out.println("Traditional switch result: " + traditionalResult);
        System.out.println("Modern switch expression result: " + modernResult);
    }

    public static void demonstrateYield(int month) {
        String season = switch (month) {
            case 12, 1, 2 -> {
                String result = "Winter";
                System.out.println("It's cold outside!");
                yield result;
            }
            case 3, 4, 5 -> {
                System.out.println("Flowers are blooming!");
                yield "Spring";
            }
            case 6, 7, 8 -> {
                double temp = 85.5;
                System.out.println("Average temperature: " + temp + "°F");
                yield "Summer";
            }
            case 9, 10, 11 -> {
                System.out.println("Leaves are changing colors!");
                yield "Fall";
            }
            default -> {
                System.out.println("Invalid month: " + month);
                yield "Unknown";
            }
        };
        System.out.println("Season: " + season);
    }
}
