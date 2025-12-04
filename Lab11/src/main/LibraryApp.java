package main;

import java.util.Scanner;
import controller.LibraryController;
import service.LibraryService;

/**
 * Entry point of the Library System application.
 * Provides a console-based user menu to interact with LibraryController.
 */
public class LibraryApp {

    public static void main(String[] args) {

        // Create service layer (contains books, members, loans and business logic)
        LibraryService service = new LibraryService();

        // Create controller layer and pass the service instance into it
        LibraryController controller = new LibraryController(service);

        // Add sample books and members so the app has initial data to work with
        controller.addSampleData();

        // Scanner for reading user input from the console
        Scanner sc = new Scanner(System.in);

        // Infinite loop to repeatedly show the menu until user exits
        while (true) {

            // Display the main menu
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. List Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Loans");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            // Read numeric choice from user
            int choice = sc.nextInt();

            // Handle the user selection using switch-case
            switch (choice) {

                // OPTION 1: Display all books
                case 1 -> controller.displayBooks();

                // OPTION 2: Borrow a book
                case 2 -> {
                    System.out.print("Enter Member ID: ");
                    int mId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bId = sc.nextInt();

                    // Controller handles validation and borrowing process
                    controller.borrowBook(mId, bId);
                }

                // OPTION 3: Return a borrowed book
                case 3 -> {
                    System.out.print("Enter Member ID: ");
                    int mId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bId = sc.nextInt();

                    // Controller processes the book return
                    controller.returnBook(mId, bId);
                }

                // OPTION 4: View current loan records
                case 4 -> controller.displayLoans();

                // OPTION 5: Exit the program
                case 5 -> {
                    System.out.println("Exiting...");
                    sc.close(); // Close input scanner to prevent resource leak
                    return;     // End the program
                }

                // If user enters an invalid option
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
