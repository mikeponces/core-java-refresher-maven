# What is a Stream
- An _aggregate operation_ that generates a single value from a set of values.
- Represents a sequence of _data elements_ that enables both _sequential_ and _parallel_ aggregate operations.
- Distinct from collections - primarily concerned with performing aggregate computations on data elements.
- A stream can represent an infinite stream of data

# No Storage
- A collection is an in-memory data structure
- A stream has no storage, it does not store elements
- A stream pulls data from a data source on demand and passes them to a pipeline of operations for processing
- Only stores the final result
- Less loops, more function. Less variables, less bugs

# Imperative vs.Functional
- With collections, we need to know _what_ we want and how to _achieve_ it
- Using streams, we indicate the _what_ operations using the built-in methods from the Streams API

```java
int sumOfSquaredOddNumbers =  numbers.stream()
       .filter(x -> x % 2 == 1) // odd
       .map(x -> x * x) // square
       .reduce(0, (x, y) -> x + y); // sum
```

# Stream Operators
- Intermediate (lazy) and terminal (eager)

# Streams are not reusable
- Streams are not re-usable, they are one-shot objects
- A stream cannot be called after a terminal operation is called on it (throws `IllegalStateException`)

```java
Stream<String> fruitStream = fruits.stream();

fruitStream
        .filter(fruit -> fruit.length() > 3)
        .forEach(this::doSomething);

fruitStream.filter(fruit -> fruit.inSeason())
        .forEach(this::doSomething); // throws exception
```

# Debugging Stream Pipeline
```java
List<Integer> processedNumbers = numbers.stream()
    .peek(n -> System.out.println("Original: "+ n))
    .map(n - > n * 2)
    .peek(n -> System.out.println("Doubled: "+ n))
    .filter(n -> n > 5)
    .peek(n -> System.out.println("Filtered: "+ n))
    .toList();
```