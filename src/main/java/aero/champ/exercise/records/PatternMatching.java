package aero.champ.exercise.records;

public class PatternMatching {

    public sealed interface LibraryItem permits PhysicalBook, EBook, AudioBook {
    }

    public record PhysicalBook(String isbn, String title, String author, int
    publicationYear, int pageCount, String location) implements LibraryItem {
    }

    public record EBook(String isbn, String title, String author, int
    publicationYear, String format, long fileSizeKB) implements LibraryItem {
    }

    public record AudioBook(String isbn, String title, String author, int
    publicationYear, String narrator, int durationMinutes) implements LibraryItem {
    }

    public static void main(String[] args) {
// Create different types of library items
        LibraryItem item1 = new PhysicalBook("978-0134685991", "Effective Java", "Joshua Bloch", 2018, 416, "Section A, Shelf 3");
        LibraryItem item2 = new EBook("978-0596009205", "Head First Java", "Kathy Sierra, Bert Bates", 2005, "PDF", 45000);
        LibraryItem item3 = new AudioBook("978-0544003415", "The Lord of the Rings", "J.R.R. Tolkien", 1954, "Andy Serkis", 1320);
// Use pattern matching to process different library items
        processLibraryItem(item1);
        processLibraryItem(item2);
        processLibraryItem(item3);
    }

    public static void processLibraryItem(LibraryItem item) {
// Using pattern matching with instanceof
        if (item instanceof PhysicalBook book) {
            System.out.println("Physical Book: " + book.title() + " by " +
                    book.author());
            System.out.println("Location: " + book.location());
            System.out.println("Page Count: " + book.pageCount());
        } else if (item instanceof EBook book) {
            System.out.println("E-Book: " + book.title() + " by " +
                    book.author());
            System.out.println("Format: " + book.format());
            System.out.println("File Size: " + book.fileSizeKB() + " KB");
        } else if (item instanceof AudioBook book) {
            System.out.println("Audio Book: " + book.title() + " by " +
                    book.author());
            System.out.println("Narrator: " + book.narrator());
            System.out.println("Duration: " + book.durationMinutes() + "minutes");
        }
        System.out.println("--------");
// Using switch expression with pattern matching (JDK 17+ feature)
// Uncomment if you're using JDK 17 or newer
/*
String description = switch (item) {
case PhysicalBook book -> "Physical Book: " + book.title() + " (" +
book.pageCount() + " pages)";
case EBook book -> "E-Book: " + book.title() + " (" + book.format()
+ ", " + book.fileSizeKB() + " KB)";
case AudioBook book -> "Audio Book: " + book.title() + " (narrated
by " + book.narrator() + ")";
};
System.out.println("Item description: " + description);
System.out.println("--------");
*/
    }
}
