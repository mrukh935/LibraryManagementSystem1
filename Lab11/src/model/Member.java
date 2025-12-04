package model;

/**
 * Simple model class that represents a library/member system user.
 * Stores a unique id, the member's name and email address.
 */
public class Member {
    // Unique identifier for the member (e.g., primary key in DB)
    private int id;

    // Member's full name
    private String name;

    // Member's contact email
    private String email;

    /**
     * Constructs a Member with the provided id, name and email.
     *
     * @param id    unique id of the member
     * @param name  full name of the member
     * @param email contact email of the member
     */
    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // --- Accessor (getter) methods ---

    /** @return the member id */
    public int getId() {
        return id;
    }

    /** @return the member name */
    public String getName() {
        return name;
    }

    /** @return the member email */
    public String getEmail() {
        return email;
    }

    /**
     * Returns a human-readable representation of this Member.
     * Useful for logging, debugging or quick UI output.
     *
     * @return formatted string like "1 - Alice (alice@example.com)"
     */
    @Override
    public String toString() {
        return id + " - " + name + " (" + email + ")";
    }
}
