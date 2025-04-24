package aero.champ.exercise.records;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryCatalog {
    public static void main(String[] args) {
// Create some books
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018, "Programming");
        Book book2 = new Book("978-0596009205", "Head First Java", "Kathy Sierra, Bert Bates", 2005, "Programming");
        Book book3 = new Book("978-0062316097", "Sapiens", "Yuval Noah Harari", 2014, "History");
        Book book4 = new Book("978-0544003415", "The Lord of the Rings", "J.R.R. Tolkien", 1954, "Fiction");
// Create a library
        List<Book> initialBooks = List.of(book1, book2);
        Library library = new Library("Central Library", "Downtown", initialBooks);
// Add more books using the immutable pattern
        library = library.addBook(book3);
        library = library.addBook(book4);
// Create a review using the nested record
        Library.BookReview review1 = new Library.BookReview(
                book1.isbn(), "John Doe", 5, "Great book for Java developers!"
        );
// Add the review
        library.addReview(review1);
// Add another review
        library.addReview(new Library.BookReview(
                book1.isbn(), "Jane Smith", 4, "Very informative but dense."
        ));
// Print library information
        System.out.println("Library: " + library.name() + " (" +
                library.location() + ")");
        System.out.println("Number of books: " + library.books().size());
        System.out.println("\nBooks in the library:");
        library.books().forEach(book ->
                System.out.println(" - " + book.title() + " by " + book.author() +
                        " (" + book.publicationYear() + ")")
        );
// Get books by genre
        String genreToFilter = "Programming";
        List<Book> programmingBooks = library.books().stream()
                .filter(book -> book.genre().equals(genreToFilter))
                .collect(Collectors.toList());
        System.out.println("\nBooks in the " + genreToFilter + " genre:");
        programmingBooks.forEach(book ->
                System.out.println(" - " + book.title() + " by " + book.author())
        );
// Print reviews for a book
        String isbnToCheck = book1.isbn();
        List<Library.BookReview> reviews =
                library.getReviewsForBook(isbnToCheck);
        Book bookWithReviews = library.books().stream()
                .filter(b -> b.isbn().equals(isbnToCheck))
                .findFirst()
                .orElseThrow();
        System.out.println("\nReviews for " + bookWithReviews.title() + ":");
        if (reviews.isEmpty()) {
            System.out.println("No reviews found.");
        } else {
            reviews.forEach(review ->
                    System.out.println(" - " + review.rating() + "/5 stars by " +
                            review.reviewerName() + ": " + review.comment())
            );
        }
    }
}
