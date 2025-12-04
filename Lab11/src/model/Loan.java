package model;

import java.time.LocalDate;

/**
 * Represents a loan/checkout of a Book by a Member.
 * Tracks when the loan started, when it's due and (optionally) when it was returned.
 */
public class Loan {
    // The book being borrowed
    private Book book;

    // The member who borrowed the book
    private Member member;

    // Date when the loan started (checkout date)
    private LocalDate loanDate;

    // Date when the book should be returned
    private LocalDate dueDate;

    // Date when the book was actually returned (null if still on loan)
    private LocalDate returnedDate;

    /**
     * Create a new Loan record.
     *
     * @param book     the Book being borrowed
     * @param member   the Member borrowing the book
     * @param loanDate the start/checkout date
     * @param dueDate  the date the book is due
     */
    public Loan(Book book, Member member, LocalDate loanDate, LocalDate dueDate) {
        this.book = book;
        this.member = member;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        // returnedDate intentionally left null until markReturned() is called
    }

    // --- Getters (accessor methods) ---

    /** @return the borrowed Book */
    public Book getBook() {
        return book;
    }

    /** @return the Member who borrowed the book */
    public Member getMember() {
        return member;
    }

    /** @return the loan (checkout) date */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /** @return the due date for this loan */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /** @return the date the book was returned, or null if not yet returned */
    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    /**
     * Mark this loan as returned now.
     * Sets the returnedDate to the current date.
     * (Note: this method only sets the returnedDate — it does not update the Book's available copies.
     */
    public void markReturned() {
        this.returnedDate = LocalDate.now();
    }

    /**
     * A human-readable representation of the loan.
     * If returnedDate is non-null the string includes the return date.
     */
    @Override
    public String toString() {
        return member.getName() + " borrowed \"" + book.getTitle() + "\" on " + loanDate +
               " (Due: " + dueDate + ")" +
               (returnedDate != null ? " - Returned on: " + returnedDate : "");
    }
}
