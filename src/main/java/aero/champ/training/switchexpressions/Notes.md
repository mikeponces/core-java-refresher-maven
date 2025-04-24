# Java Switch Expressions
- Enhanced from a "control flow mechanism"
- "Expression" allows it to yield a value (more declarative/functional)
- Introduced in Java 12 (Preview), finalized in Java 14
- Eliminates fall-through issues and improves readability
- Uses `->` (arrow) and `yield`

```java
// traditional
String day = "MONDAY";
int numLetters;
switch(day) {
    case "MONDAY":
    case "FRIDAY":
    case "SUNDAY":
      numLetters = 6;
    break;
        
    default:
      numLetters = 0;
     break;
}

// modern
int numLetters2 = switch(day) {
  case "MONDAY", "FRIDAY", "SUNDAY" -> 6;
  default -> 0;
};

int someNumber = switch(fruit) {
    case "APPLE" -> {
        System.out.println("Print something");
        yield 52;
    };
    default -> 0;
};
```

