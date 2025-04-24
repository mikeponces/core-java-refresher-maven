# Lambda expression:
```
(parameter) -> expression
(parameter1, parameter2) -> expression
```

# Functional interface
- `@FunctionalInterface`
- Contains EXACTLY one abstract method
- Default and static methods are allowed
- Predefined Functional Interfaces: `Consumers`, `Predicates`, `Suppliers`, `Functions`

# Benefits
- Brevity (cutting down verbose syntax)
- Decrease code redundancy
- Improved clarity
- Removal of shadow variables
- Promotion of functional programming principles
- Reusability of code
- Streamlined variable scope

```java
button.addActionListener(new ActionListener() {
    // some code
});

button.addActionListener(e -> {
   // some code 
});

button.addActionListener(e -> performAction());

button.addActionListener(this::performAction);
```

```java
List<Product> products = getProductInventory();

List<String> expensiveProductNames = products.stream()
        .filter(product -> product.getPrice() > 100.00)
        .map(product -> product.getName())
        .sorted()
        .collect(Collectors.toList());

double totalValue = products.stream()
        .mapToDouble(product -> product.getPrice() * product.getQuantity())
        .sum();

Map<String, List<Product>> productsByCategory = products.stream()
        .collect(Collectors.groupingBy(product -> product.getCategory()));
```

```java
CompletableFuture.supplyAsync(() -> fetchOrderData())
        .thenApply(orders -> processOrders(orders))
        .thenAccept(summary -> displayOrderSummary(summary))
        .thenRun(() -> markOrderProcessingComplete());
```

