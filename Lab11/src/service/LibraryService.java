package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Member;
import model.Loan;

/**
 * Simple in-memory service that manages books, members and loans.
 * This is an application/service layer that coordinates model operations.
 */
public class LibraryService {
    // Internal storage for books, members and loans.
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    /** Add a book to the library inventory. */
    public void addBook(Book book) {
        books.add(book);
    }

    /** Register a new library member. */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Return a live reference to the books list.
     * NOTE: This exposes internal state — consider returning an unmodifiable copy.
     */
    public List<Book> getBooks() {
        return books;
    }

    /** Return the member list. Consider returning an unmodifiable list instead. */
    public List<Member> getMembers() {
        return members;
    }

    /** Return the loans list. Consider returning an unmodifiable list instead. */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * Borrow a book on behalf of a member.
     *
     * Steps:
     * 1. Find the member by memberId (throws IllegalArgumentException if not found).
     * 2. Find the book by bookId (throws IllegalArgumentException if not found).
     * 3. Call book.borrowBook() to decrement available copies (this may throw if none available).
     * 4. Create and store a new Loan with today's date and a due date 14 days from now.
     */
    public void borrowBook(int memberId, int bookId) {
        Member member = members.stream()      //converts the list into streams
                .filter(m -> m.getId() == memberId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Member not found."));

        Book book = books.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));

        // This method on Book will throw IllegalStateException if no copies are available.
        book.borrowBook();

        // Create and store the loan (14-day loan period).
        loans.add(new Loan(book, member, LocalDate.now(), LocalDate.now().plusDays(14)));
    }

    /**
     * Return a book for a given member.
     *
     * Steps:
     * 1. Find an active loan record (matching memberId & bookId and not yet returned).
     * 2. Mark the loan as returned (sets returnedDate).
     * 3. Update the Book to increment available copies.
     */
    public void returnBook(int bookId, int memberId) {
        Loan loan = loans.stream()
                .filter(l -> l.getBook().getId() == bookId
                        && l.getMember().getId() == memberId
                        && l.getReturnedDate() == null) // only active loans, still borrowed
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No active loan found."));

        // record return date on Loan
        loan.markReturned();

        // increment the book's available copies
        loan.getBook().returnBook();
    }
}
