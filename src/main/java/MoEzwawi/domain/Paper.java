package MoEzwawi.domain;

/**
 * The Paper class represents a research paper or academic article in the library.
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>doi: The Digital Object Identifier for the paper.</li>
 *   <li>venue: The venue where the paper was published (e.g., conference or journal name).</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #Paper(String, String, int, String, String)}: Constructs a paper with
 *       title, author(s), year, DOI, and venue.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #getDoi()}: Returns the DOI.</li>
 *   <li>{@link #getVenue()}: Returns the publication venue.</li>
 *   <li>{@link #summary()}: Returns a formatted summary string for the paper.</li>
 * </ul>
 */
public class Paper extends BibliographicItem {

    /**
     * The Digital Object Identifier of the paper.
     */
    private final String doi;

    /**
     * The publication venue of the paper (e.g., conference or journal name).
     */
    private final String venue;

    /**
     * Constructs a new Paper instance.
     *
     * @param title  The title of the paper.
     * @param author The main author(s) of the paper.
     * @param year   The year of publication.
     * @param doi    The Digital Object Identifier (not null, can be empty).
     * @param venue  The publication venue (not null, can be empty).
     */
    public Paper(String title, String author, int year, String doi, String venue) {
        super(title, author, year);
        this.doi = (doi == null) ? "" : doi;
        this.venue = (venue == null) ? "" : venue;
    }

    /**
     * Returns the DOI of the paper.
     *
     * @return DOI string (may be empty).
     */
    public String getDoi() {
        return this.doi;
    }

    /**
     * Returns the publication venue of the paper.
     *
     * @return venue string (may be empty).
     */
    public String getVenue() {
        return this.venue;
    }

    /**
     * Provides a formatted summary of the paper.
     *
     * @return string in the form "Paper @ Venue: Title (Year)".
     */
    @Override
    public String summary() {
        String where = venue.isBlank() ? "Paper" : "Paper @ " + venue;
        return where + ": " + getTitle() + " (" + getYear() + ")";
    }
}

