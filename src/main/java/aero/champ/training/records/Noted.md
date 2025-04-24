# A modern approach to Immutable Data classes
- Used for Data Transfer Objects (DTO) 
- DTO's lightweight alternatives to Entity objects
- POJO's are simple classes but implements methods like `equals`, `hashCode`, getters/setters, etc.
- Introduced in Java 14 (Preview), finalized in Java 16
- `Records` are **immutable**. Once you declare it and set the values, it cannot be changed.
- DTO's are implemented as `Records` in Spring Boot
- You can declare custom methods and constructors in a Record

# Traditional Class vs. Record
- Class: requires explicit field declarations, constructors, getters/setters, and overrides `equals` and `hashCode`
- Record: Automatically provides constructor, `equals`, `hashCode`, and `toString`
- Records are more optimized for memory compared to POJOs

```java
public record Person(String name, int age) {
    // constructor
    // getters name(), age()
}
```

# Use Cases
- DTO's
- Value Objects (representing simple immutable objects)
- Response Models
- (Read-only) database records (rare)

```java

public record Employee(String name, int salary) {
    static double MIN_SALARY = 30_000;
    
    public Employee {
        if (salary < MIN_SALARY) {
            throw new IllegalArgumentException();
        }
    }
}
```

# Limitation of Records
- Cannot modify fields (immutable)
- No inheritance: cannot extend (but can implement `interface`)
- Not suitable for large entities: use for simple containers, not complex objects (multiple subclasses)

