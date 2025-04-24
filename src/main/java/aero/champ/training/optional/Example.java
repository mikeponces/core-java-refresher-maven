package aero.champ.training.optional;

import java.util.Optional;

public class Example {
    public static void main(String[] args) {
        Optional<String> optionalValue = Optional.of("Hello, Optional!");
        Optional<String> optionalEmpty = Optional.empty();

        optionalValue.ifPresent(value -> System.out.println(value));

        String defaultValue = optionalEmpty.orElse("Default Value");
        // orElseGet
        // orElseThrow
    }
}
