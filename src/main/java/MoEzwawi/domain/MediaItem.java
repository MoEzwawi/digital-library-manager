package MoEzwawi.domain;

/**
 * The MediaItem class is an abstract representation of a generic media element
 * in the digital library, such as a {@link Book}, {@link Journal}, or {@link Paper}.
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>title: The title of the media item (cannot be null or blank).</li>
 *   <li>author: The author or main contributor of the media item (cannot be null or blank).</li>
 *   <li>year: The publication year (must be a non-negative integer).</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #MediaItem(String, String, int)}: Constructs a new media item
 *       with validation on title, author, and year.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #getTitle()}: Returns the title of the media item.</li>
 *   <li>{@link #getAuthor()}: Returns the author of the media item.</li>
 *   <li>{@link #getYear()}: Returns the publication year.</li>
 *   <li>{@link #summary()}: Abstract method to provide a user-friendly description
 *       of the media item (implemented by subclasses).</li>
 * </ul>
 */
public abstract class MediaItem {

    /**
     * The title of the media item (cannot be null or blank).
     */
    private final String title;

    /**
     * The author of the media item (cannot be null or blank).
     */
    private final String author;

    /**
     * The publication year of the media item (must be >= 0).
     */
    private final int year;

    /**
     * Constructs a new MediaItem with the specified attributes.
     *
     * @param title  The title of the media item (not null or blank).
     * @param author The author of the media item (not null or blank).
     * @param year   The year of publication (must be non-negative).
     * @throws IllegalArgumentException if any parameter is invalid.
     */
    protected MediaItem(String title, String author, int year) {
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
     * Returns the title of the media item.
     *
     * @return the title as a String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the media item.
     *
     * @return the author as a String.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the publication year of the media item.
     *
     * @return the year as an int.
     */
    public int getYear() {
        return year;
    }

    /**
     * Provides a user-friendly description of the media item.
     * <p>
     * This method is abstract and must be implemented by each subclass to include
     * specific details (e.g., ISBN for books, DOI for papers).
     * </p>
     *
     * @return a textual summary of the media item.
     */
    public abstract String summary();
}
