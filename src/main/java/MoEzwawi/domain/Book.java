package MoEzwawi.domain;

/**
 * The Book class represents a digital book resource in the library.
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>isbn: International Standard Book Number, identifying the book.</li>
 *   <li>pages: Total number of pages in the book (must be >= 0).</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #Book(String, String, int, String, int)}: Constructs a book with
 *       title, author, year, ISBN, and page count.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #getIsbn()}: Returns the ISBN.</li>
 *   <li>{@link #getPages()}: Returns the page count.</li>
 *   <li>{@link #summary()}: Returns a formatted summary string for the book.</li>
 * </ul>
 */
public class Book extends BibliographicItem {

    /**
     * The International Standard Book Number of the book.
     */
    private final String isbn;

    /**
     * Total number of pages in the book (must be >= 0).
     */
    private final int pages;

    /**
     * Constructs a new Book instance.
     *
     * @param title  The title of the book.
     * @param author The author of the book.
     * @param year   The publication year.
     * @param isbn   The ISBN code (can be empty but not null).
     * @param pages  Total number of pages (must be >= 0).
     */
    public Book(String title, String author, int year, String isbn, int pages) {
        super(title, author, year);
        this.isbn = (isbn == null) ? "" : isbn;
        this.pages = Math.max(0, pages);
    }

    /**
     * Returns the ISBN of the book.
     *
     * @return ISBN string, may be empty.
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Returns the total number of pages.
     *
     * @return number of pages (>= 0).
     */
    public int getPages() {
        return this.pages;
    }

    /**
     * Provides a formatted summary of the book.
     *
     * @return string in the form "Book: Title by Author (Year)".
     */
    @Override
    public String summary() {
        return "Book: " + super.getTitle() + " by " + super.getAuthor() + " (" + super.getYear() + ")";
    }
}
