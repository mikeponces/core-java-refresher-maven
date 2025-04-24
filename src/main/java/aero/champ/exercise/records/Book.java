package aero.champ.exercise.records;

public record Book(
        String isbn,
        String title,
        String author,
        int publicationYear,
        String genre
) {
    // Compact constructor for validation
    public Book {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or blank");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        if (publicationYear < 1000 || publicationYear > 9999) {
            throw new IllegalArgumentException("Publication year must be a 4-digit number");
        }
    }
}
