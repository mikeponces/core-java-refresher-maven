# A modern approach to type-safe conditional logic
- Simplifies type checks and casting in conditional statements
- Eliminates `instanceof` + casting
- Introduced in Java 14

```java
// traditional
Object obj = "Hello World";
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s);
}

// pattern matching
if (obj instanceof String s) { // check + assignment
    System.out.println(s);
}
```

# Benefits
- Eliminates redundant type casting
- Ensures type safety and reduced errors
- Optimized performance and compiler improvement

```java
// pattern matching with switch expressions
switch (obj) {
    case String s -> System.out.println(s);
    case Integer i -> System.out.println(i);
    case null -> System.out.println(null);
    default -> System.out.println("Default");
}

// pattern matching with records
if (obj instanceof Point(int x, int y)) {
    System.out.println(x);
    System.out.println(y);
}
```

# Use Cases
- Parsing and handling different data types
- Reducing boiler plate if-else conditions
- Switch expressions with pattern matching (Java 17+)