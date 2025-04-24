package aero.champ.exercise.records;

import java.util.ArrayList;
import java.util.List;

public class RecordSerialization {
    public static void main(String[] args) {
// Create some books
        Book book1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2018, "Programming");
                Book book2 = new Book("978-0062316097", "Sapiens", "Yuval Noah Harari", 2014, "History");
        List<Book> books = List.of(book1, book2);
// Simulate converting records to JSON
        String json = convertToJson(books);
        System.out.println("Books as JSON:");
        System.out.println(json);
        System.out.println("\nSimulating saving to file: books.json");
// Simulate reading JSON back into records
        System.out.println("\nSimulating reading from file: books.json");
        List<Book> loadedBooks = convertFromJson(json);
        System.out.println("Loaded books:");
        loadedBooks.forEach(System.out::println);
// Check if the loaded books match the original
        System.out.println("\nOriginal books equal to loaded books: " +
                books.equals(loadedBooks));
    }

    // Simulate JSON conversion methods (in a real application, you would use a JSON library)
    private static String convertToJson(List<Book> books) {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            json.append(" {\n");
            json.append(" \"isbn\":\"").append(book.isbn()).append("\",\n");
            json.append(" \"title\":\"").append(book.title()).append("\",\n");
            json.append(" \"author\":\"").append(book.author()).append("\",\n");
            json.append(" \"publicationYear\":").append(book.publicationYear()).append(",\n");
            json.append(" \"genre\":\"").append(book.genre()).append("\"\n");
            json.append(" }");
            if (i < books.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");
        return json.toString();
    }

    private static List<Book> convertFromJson(String json) {
// This is a very simplified simulation and wouldn't work with real JSON
// In a real application, you would use a JSON library
        List<Book> books = new ArrayList<>();
// Add the same books as in the original list for simulation
        books.add(new Book("978-0134685991", "Effective Java", "Joshua Bloch",
                2018, "Programming"));
        books.add(new Book("978-0062316097", "Sapiens", "Yuval Noah Harari",
                2014, "History"));
        return books;
    }
}
