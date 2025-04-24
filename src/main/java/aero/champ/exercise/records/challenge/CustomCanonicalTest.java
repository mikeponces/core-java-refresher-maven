package aero.champ.exercise.records.challenge;

public class CustomCanonicalTest {
    public static void main(String[] args) {
// Create a book using the custom canonical constructor (without acquisition date)
        BookWithCustomCanonical book1 = new BookWithCustomCanonical(
                "978-0134685991", "Effective Java", "Joshua Bloch", 2018,
                "Programming"
        );
        System.out.println("Book created with custom canonical constructor:");
        System.out.println(book1);
        System.out.println("Acquisition Date: " + book1.acquisitionDate());
// Create a book using the static factory method
        BookWithCustomCanonical book2 =
                BookWithCustomCanonical.createWithCurrentDate(
                        "978-0062316097", "Sapiens", "Yuval Noah Harari", 2014, "History"
                );
        System.out.println("\nBook created with static factory method:");
        System.out.println(book2);
        System.out.println("Acquisition Date: " + book2.acquisitionDate());
    }
}
