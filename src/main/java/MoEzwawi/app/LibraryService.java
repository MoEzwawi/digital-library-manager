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
    private final BibliographicCollection collections = new BibliographicCollection("collections", "master", LocalDate.now().getYear());

    /**
     * Constructs the service with the given factory.
     *
     * @param factory the factory used to create bibliographic items
     */
    public LibraryService(BibliographicFactory factory) {
        this.factory = factory;
    }
    /**
     * Creates a new bibliographic item (Book, Journal, Paper or Collection)
     * using the configured factory. The item is then stored in the appropriate
     * root container based on its type.
     *
     * <p>If the created item is a regular bibliographic entry
     * (i.e., not a collection), it is added to the internal {@code library} container.
     * If the item is a {@code BibliographicCollection}, it is added to the
     * {@code collections} container instead.</p>
     *
     * <p>This method applies exception shielding: it logs internal errors and throws
     * user-friendly messages to avoid leaking implementation details.</p>
     *
     * @param type   the type of bibliographic item to create (BOOK, JOURNAL, PAPER, or COLLECTION)
     * @param params a map of input parameters required by the factory for the specified type
     * @return the created bibliographic item
     * @throws ShieldedException if creation fails due to invalid input or an unexpected error
     */
    public BibliographicItem newItem(EntryType type, Map<String, String> params) {
        try {
            BibliographicItem item = this.factory.create(type, params);
            if (type != EntryType.COLLECTION){
                library.addItem(item);
                Log.info("Item added: " + item.summary());
            } else {
                collections.addItem(item);
                Log.info("Collection added: " + item.summary());
            }
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
    public void addToCollection(BibliographicItem collection, BibliographicItem item) {
        try {
            if (!(collection instanceof BibliographicCollection collectionInternalVar)) {
                throw new IllegalArgumentException("Collection param must be of type BibliographicCollection.");
            }
            if (item == null) throw new IllegalArgumentException("Item param must not be null");
            collectionInternalVar.addItem(item);
            Log.info("Item " + item.getTitle() + " added to collection: " + collection.getTitle());
        } catch (RuntimeException ex) {
            Log.error("Failed to add item to collection: " + collection, ex);
            throw new ShieldedException("Invalid item or collection.", ex);
        }
    }


    /**
     * Returns an immutable list of all bibliographic items (excluding collections).
     *
     * @return a copy of the item list
     */
    public List<BibliographicItem> listAllItems() {
        return this.library.getItems();
    }
    /**
     * Returns an immutable list of all bibliographic collections.
     *
     * @return a copy of the collection list
     */
    public List<BibliographicItem> listCollections() {
        return this.collections.getItems();
    }
}
