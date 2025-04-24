# New Date and Time API
- Replaces legacy `java.util.Date` and `Calendar`
- Classes are immutable and thread-safe
- Provides time zone support
- Package `java.time`

```java
LocalDateTime currentDateTime = LocalDateTime.now();
LocalDate futureDate = LocalDate.now().plusDays(5);
```

# Common Classes
- `LocalDate`
- `LocalTime`
- `LocalDateTime`
- `ZonedDateTime` - includes time zone information
- `Duration` & `Period` - represents time difference (for debugging)
- `Instant` - represents timestamp

```java
// timezone where the code is ran
LocalDate today = LocalDate.now();

// supply specific timezone
ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/New York"));

```

# Parsing and Formatting Dates
```java
LocalDate date = LocalDate.parse("2025-03-22");
DateFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

date.format(formatter);
```