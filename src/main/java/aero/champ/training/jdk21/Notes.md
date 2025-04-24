# Introduction to JDK 21
## Virtual Threads
- Lightweight, low-cost threads for high concurrency
- Cheap to create (not OS-managed, but from the JVM)
- Reduce the complexity of asynchronous programming
- Improve scalability without requiring complex thread pools

### Traditional Threads
- Expensive
- Limited (OS-managed)
- Blocking Issues

### How Virtual Threads Help
- Ultra-lightweight
- Efficient resource usage: no wasted system threads
- Simplifies concurrency
- Better scalability

```java
Thread.startVirtualThread(() ->System.out.

println("Hello from Virtual Thread"));
// console shows keyword "virtual" to denote virtual threads

try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> {
    
    });
}
```

## Sequenced Collections
- If you only need to access the first and last element
- New interface for ordered collections
- Provides methods `first()`, `last()`, and `reversed()`
- Works on `List`, `Set`, and `Map`

```java
SequencedCollection<String> names = new LinkedHashSet<>();
// populate names
names.first();
names.last();
names.reversed();
```

## Structured Concurrency
- Simplifies concurrency programming
- Groups related tasks and manage them as a unit
- Improves readability and error handling

```java
// traditional
ExecutorService executor = Executors.newFixedThreadPool(2);

Future<String> userFuture = executor.submit(() -> fetchUserData());
Future<String> orderFuture = executor.submit(() -> fetchOrderHistory());

userFuture.get();
orderFuture.get();

executor.shutdown();
```
### Why we need `StructuredTaskScope`
- It allows multiple tasks in parallel
- Automatically manages tasks as a group

```java
try(var scope = new StructureTaskScope.ShutdownOnFailure()) {
Future<String> task1 = scope.fork(() -> fetchUserData());
Future<String> task2 = scope.fork(() -> fetchOrderHistory());

scope.join();

task1.resultNow();
task2.resultNow();
}
```