package aero.champ.training.patternmatching;

import java.util.List;

public class Example {
    public static void main(String[] args) {
        List<Object> items = List.of(42,   "123", "OpenAI", 3.14, true, null);

        for(Object item : items) {
            String result = switch(item) {
                case Integer i -> "Integer doubled: "+ i*2;
                case String s -> "Uppercase: "+s.toUpperCase();
                case null -> "Null value encountered";
                default -> "Unknown type";
            };
            System.out.println(result);
        }
    }
}
