package MoEzwawi.app;

import MoEzwawi.domain.BibliographicCollection;
import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.error.InvalidInputException;
import MoEzwawi.error.ShieldedException;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.EntryType;
import MoEzwawi.util.Log;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Application-level service that orchestrates bibliographic operations.
 * Performs exception shielding to protect the user-facing interface and
 * prevent leakage of internal implementation details.
 */
public class LibraryService {

    private final BibliographicFactory factory;
    private final BibliographicCollection library = new BibliographicCollection("library", "master", LocalDate.now().getYear());

    /**
     * Constructs the service with the given factory.
     *
     * @param factory the factory used to create bibliographic items
     */
    public LibraryService(BibliographicFactory factory) {
        this.factory = factory;
    }

    /**
     * Adds a bibliographic item to the library using the factory.
     * Shields technical exceptions and exposes only safe messages.
     *
     * @param type   the type of bibliographic item (BOOK, JOURNAL, PAPER)
     * @param params the parameter map for creating the item
     * @return the created bibliographic item
     * @throws ShieldedException if creation fails due to invalid input or other internal issues
     */
    public BibliographicItem newItem(EntryType type, Map<String, String> params) {
        try {
            BibliographicItem item = this.factory.create(type, params);
            library.addItem(item);
            Log.info("Item added: " + item.summary());
            return item;
        } catch (InvalidInputException ex) {
            Log.warn("Invalid input while creating item", ex);
            throw new ShieldedException("Invalid input. Please check the provided fields.", ex);
        } catch (RuntimeException ex) {
            Log.error("Unexpected error while creating item", ex);
            throw new ShieldedException("Unexpected error while creating the item.", ex);
        }
    }

    /**
     * Adds an item to an existing bibliographic collection.
     *
     * @param collection the target collection to add the item into
     * @param item the item to add (can be a Book, Journal, Paper or another Collection)
     * @throws ShieldedException if the collection or item is null
     */
    public void addToCollection(BibliographicCollection collection, BibliographicItem item) {
        try {
            if (collection == null) throw new IllegalArgumentException("Collection must not be null");
            if (item == null) throw new IllegalArgumentException("Item must not be null");
            collection.addItem(item);
            Log.info("Item" + item.getTitle() + "added to collection: " + collection.getTitle());
        } catch (RuntimeException ex) {
            Log.error("Failed to add item to collection: " + collection, ex);
            throw new ShieldedException("Invalid item or collection.", ex);
        }
    }


    /**
     * Returns an immutable view of the stored items.
     *
     * @return a copy of the item list.
     */
    public List<BibliographicItem> listAll() {
        return this.library.getLeaves();
    }
}
