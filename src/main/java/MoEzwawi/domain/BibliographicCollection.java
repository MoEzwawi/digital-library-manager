package MoEzwawi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The BibliographicCollection class represents a collection of bibliographic items.
 * <p>
 * This class serves as the Composite in the Composite design pattern, allowing
 * multiple {@link BibliographicItem} instances to be managed as a single entity.
 * </p>
 * <p>
 * Attributes:
 * </p>
 * <ul>
 *   <li>items: A list of bibliographic items contained in this collection.</li>
 * </ul>
 * <p>
 * Constructors:
 * </p>
 * <ul>
 *   <li>{@link #BibliographicCollection(String, String, int)}: Constructs a collection
 *       with a title, author, and year.</li>
 * </ul>
 * <p>
 * Methods:
 * </p>
 * <ul>
 *   <li>{@link #addItem(BibliographicItem)}: Adds a bibliographic item to the collection.</li>
 *   <li>{@link #getItems()}: Returns an unmodifiable view of the items in the collection.</li>
 *   <li>{@link #summary()}: Returns a formatted summary string for the collection.</li>
 * </ul>
 */
public class BibliographicCollection extends BibliographicItem{
    /**
     * A list of bibliographic items contained in this collection.
     */
    private final List<BibliographicItem> items = new ArrayList<>();

    /**
     * Constructs a new BibliographicCollection instance.
     *
     * @param title  The title of the collection.
     * @param author The creator of the collection.
     * @param year   The creation year of the collection.
     */
    public BibliographicCollection(String title, String author, int year) {
        super(title, author, year);
    }
    /**
     * Adds a bibliographic item to this collection.
     *
     * @param item The {@link BibliographicItem} to add.
     */
    public void addItem(BibliographicItem item){
        this.items.add(item);
    }
    /**
     * Returns an unmodifiable view of the items in the collection.
     *
     * @return List of {@link BibliographicItem} contained in this collection.
     */
    public List<BibliographicItem> getItems() {
        return List.copyOf(this.items);
    }

    /**
     * Returns a flat list of all leaf items contained in this collection.
     *
     * @return list of leaf bibliographic items
     */
    public List<BibliographicItem> getLeaves() {
        List<BibliographicItem> leaves = new ArrayList<>();
        for (BibliographicItem item : this.items) {
            if (item instanceof BibliographicCollection collection) {
                leaves.addAll(collection.getLeaves());
            } else {
                leaves.add(item);
            }
        }
        return leaves;
    }

    /**
     * Provides a formatted summary of the collection.
     *
     * @return string in the form "Collection: Title by Author (Year), N item(s)".
     */

    @Override
    public String summary() {
        return "Collection: " + super.getTitle() + " by " + super.getAuthor() + " (" + super.getYear() + "), "
                + items.size() + " item(s)";
    }
}
