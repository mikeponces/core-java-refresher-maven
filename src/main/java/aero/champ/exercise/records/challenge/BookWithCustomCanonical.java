package aero.champ.exercise.records.challenge;

import java.time.LocalDate;

public record BookWithCustomCanonical(
        String isbn,
        String title,
        String author,
        int publicationYear,
        String genre,
        LocalDate acquisitionDate
) {
    // Custom canonical constructor
    public BookWithCustomCanonical(String isbn, String title, String author,
                                   int publicationYear, String genre) {
        this(isbn, title, author, publicationYear, genre, LocalDate.now());
    }
    // Compact constructor for validation
    public BookWithCustomCanonical {
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
            throw new IllegalArgumentException("Publication year must be a 4- digit number");
        }
    }
    // Static factory method
    public static BookWithCustomCanonical createWithCurrentDate(String isbn,
                                                                String title, String author, int publicationYear, String genre) {
        return new BookWithCustomCanonical(isbn, title, author,
                publicationYear, genre);
    }
}
