package MoEzwawi.domain;

/**
 * The Journal class represents an academic or scientific journal entry in the library.
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>issue: The issue identifier of the journal (e.g., "Vol. 10, No. 2").</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #Journal(String, String, int, String)}: Constructs a journal with
 *       title, author/editor, year, and issue information.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #getIssue()}: Returns the journal issue.</li>
 *   <li>{@link #summary()}: Returns a formatted summary string for the journal.</li>
 * </ul>
 */
public class Journal extends MediaItem {

    /**
     * The issue of the journal (e.g., "Vol. 10, No. 2").
     */
    private final String issue;

    /**
     * Constructs a new Journal instance.
     *
     * @param title  The title of the journal.
     * @param author The editor/author of the journal.
     * @param year   The publication year.
     * @param issue  The issue identifier (not null, can be empty).
     */
    public Journal(String title, String author, int year, String issue) {
        super(title, author, year);
        this.issue = (issue == null) ? "" : issue;
    }

    /**
     * Returns the issue of the journal.
     *
     * @return issue string (may be empty).
     */
    public String getIssue() {
        return issue;
    }

    /**
     * Provides a formatted summary of the journal.
     *
     * @return string in the form "Journal: Title [Issue] (Year)".
     */
    @Override
    public String summary() {
        return "Journal: " + getTitle() + " [" + issue + "] (" + getYear() + ")";
    }
}
