package MoEzwawi.domain;

/**
 * The BibliographicItem class is an abstract representation of a generic bibliographic element
 * in the digital library, such as a {@link Book}, {@link Journal}, or {@link Paper}.
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>title: The title of the bibliographic item (cannot be null or blank).</li>
 *   <li>author: The author or main contributor of the bibliographic item (cannot be null or blank).</li>
 *   <li>year: The publication year (must be a non-negative integer).</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #BibliographicItem(String, String, int)}: Constructs a new bibliographic item
 *       with validation on title, author, and year.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #getTitle()}: Returns the title of the bibliographic item.</li>
 *   <li>{@link #getAuthor()}: Returns the author of the bibliographic item.</li>
 *   <li>{@link #getYear()}: Returns the publication year.</li>
 *   <li>{@link #summary()}: Abstract method to provide a user-friendly description
 *       of the bibliographic item (implemented by subclasses).</li>
 * </ul>
 */
public abstract class BibliographicItem {

    /**
     * The title of the bibliographic item (cannot be null or blank).
     */
    private final String title;

    /**
     * The author of the bibliographic item (cannot be null or blank).
     */
    private final String author;

    /**
     * The publication year of the bibliographic item (must be >= 0).
     */
    private final int year;

    /**
     * Constructs a new BibliographicItem with the specified attributes.
     *
     * @param title  The title of the bibliographic item (not null or blank).
     * @param author The author of the bibliographic item (not null or blank).
     * @param year   The year of publication (must be non-negative).
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    protected BibliographicItem(String title, String author, int year) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required and must not be blank.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author is required and must not be blank.");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year must be >= 0.");
        }
        this.title = title;
        this.author = author;
        this.year = year;
    }

    /**
     * Returns the title of the bibliographic item.
     *
     * @return the title as a String.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the author of the bibliographic item.
     *
     * @return the author as a String.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the publication year of the bibliographic item.
     *
     * @return the year as an int.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Provides a user-friendly description of the bibliographic item.
     * <p>
     * This method is abstract and must be implemented by each subclass to include
     * specific details (e.g., ISBN for books, DOI for papers).
     * </p>
     *
     * @return a textual summary of the bibliographic item.
     */
    public abstract String summary();

    @Override
    public String toString() {
        return this.summary();
    }
}
