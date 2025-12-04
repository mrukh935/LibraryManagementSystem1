package controller;

import service.LibraryService;
import model.Book;
import model.Member;

/**
 * Controller layer that provides simple operations for a console/demo application.
 * It delegates business operations to LibraryService and prints simple feedback.
 */
public class LibraryController {
    // Service that contains the business logic and data storage.
    private final LibraryService service;

    /**
     * Construct the controller with a LibraryService instance.
     * Use dependency injection so tests can pass a mock or alternative service.
     *
     * @param service the LibraryService to delegate to
     */
    public LibraryController(LibraryService service) {
        this.service = service;
    }

    /**
     * Populate the service with some sample books and members.
     */
    public void addSampleData() {
        // Add a couple of books with id, title, author and copies available.
        service.addBook(new Book(1, "Java Programming", "John Doe", 3));
        service.addBook(new Book(2, "Design Patterns", "Erich Gamma", 2));

        // Add sample members (id, name, email).
        service.addMember(new Member(1, "Alice", "alice@example.com"));
        service.addMember(new Member(2, "Bob", "bob@example.com"));
    }

    /**
     * Borrow a book for a member.
     * Delegates to the service and prints a success message.
     * @param memberId the id of the member who wants to borrow
     * @param bookId   the id of the book to borrow
     */
    public void borrowBook(int memberId, int bookId) {
        service.borrowBook(memberId, bookId);
        System.out.println("Book borrowed successfully.");
    }

    /**
     * Return a book for a member.
     * Note: service.returnBook expects bookId first then memberId, so we forward accordingly.
     *
     * @param memberId the id of the member returning the book
     * @param bookId   the id of the book being returned
     */
    public void returnBook(int memberId, int bookId) {
        // Forwarding parameters in the order the service expects: (bookId, memberId)
        service.returnBook(bookId, memberId);
        System.out.println("Book returned successfully.");
    }

    /**
     * Print all books to the console using the Book.toString() representation.
     */
    public void displayBooks() {
        service.getBooks().forEach(System.out::println);
    }

    /**
     * Print all loans to the console using the Loan.toString() representation.
     */
    public void displayLoans() {
        service.getLoans().forEach(System.out::println);
    }
}
