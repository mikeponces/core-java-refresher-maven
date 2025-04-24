package aero.champ.exercise.records;

public class RecordExplorer {
    public static void main(String[] args) {
        // Create a new Book record
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018, "Programming");
// Access record components using auto-generated accessors
        System.out.println("ISBN: " + book1.isbn());
        System.out.println("Title: " + book1.title());
        System.out.println("Author: " + book1.author());
// Test auto-generated toString()
        System.out.println("\ntoString() output:");
        System.out.println(book1);
// Create a second book with the same data
        Book book2 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018, "Programming");
// Test auto-generated equals() and hashCode()
        System.out.println("\nEquals and HashCode:");
        System.out.println("book1.equals(book2): " + book1.equals(book2));
        System.out.println("book1.hashCode() == book2.hashCode(): " +
                (book1.hashCode() == book2.hashCode()));
// Try to create an invalid book
        try {
            Book invalidBook = new Book("", "Invalid Book", "Unknown", 2023,
                    "Fiction");
        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation works: " + e.getMessage());
        }
    }
}
