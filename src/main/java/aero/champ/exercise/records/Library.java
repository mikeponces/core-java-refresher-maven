package aero.champ.exercise.records;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Library(
        String name,
        String location,
        List<Book> books
) {
    // Nested record for book reviews
    public record BookReview(
            String isbn,
            String reviewerName,
            int rating,
            String comment
    ) {
        // Compact constructor for validation
        public BookReview {
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5");
            }
        }
    }

    // Instance variable (mutable state)
    private static final Map<String, List<BookReview>> bookReviews = new HashMap<>();

    // Constructor with deep copy of books list
    public Library(String name, String location, List<Book> books) {
        this.name = name;
        this.location = location;
// Create a defensive copy of the books list
        this.books = new ArrayList<>(books);
    }

    // Method to add a review
    public void addReview(BookReview review) {
        String isbn = review.isbn();
        bookReviews.computeIfAbsent(isbn, k -> new ArrayList<>()).add(review);
    }
    // Method to get reviews for a book
    public List<BookReview> getReviewsForBook(String isbn) {
        return bookReviews.getOrDefault(isbn, List.of());
    }
    // Method to add a book
    public Library addBook(Book book) {
        List<Book> newBooks = new ArrayList<>(books);
        newBooks.add(book);
        return new Library(name, location, newBooks);
    }
}
