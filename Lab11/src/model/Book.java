package model;

/**
 * Simple model class that represents a Book in a library system.
 * Keeps track of the book's id, title, author and how many copies are available.
 */
public class Book {
    // Unique identifier for the book (e.g., database PK or inventory id)
    private int id;

    // Book title
    private String title;

    // Book author name
    private String author;

    // Number of copies currently available for borrowing
    private int copiesAvailable;

    /**
     * Constructor to create a Book object with all required fields.
     *
     * @param id              unique id of the book
     * @param title           title of the book
     * @param author          author name
     * @param copiesAvailable initial number of copies available (should be >= 0)
     */
    public Book(int id, String title, String author, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    // --- Accessor (getter) methods ---

    /** @return the book id */
    public int getId() {
        return id;
    }

    /** @return the book title */
    public String getTitle() {
        return title;
    }

    /** @return the author name */
    public String getAuthor() {
        return author;
    }

    /** @return how many copies are currently available */
    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    // --- Business logic methods ---

    /**
     * Attempt to borrow a book.
     * If at least one copy is available, decrement the counter.
     * Otherwise throw IllegalStateException to indicate no copies left.
     *
     * This enforces that copiesAvailable never becomes negative.
     */
    public void borrowBook() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        } else {
            throw new IllegalStateException("No copies available.");
        }
    }

    /**
     * Return a book copy: increment the counter.
     */
    public void returnBook() {
        copiesAvailable++;
    }

    /**
     * Human-readable representation of this Book instance.
     * Useful for logging, debugging or simple UI output.
     */
    @Override
    public String toString() {
        return id + " - " + title + " by " + author + " (Available: " + copiesAvailable + ")";
    }
}
