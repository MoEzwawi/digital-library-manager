package MoEzwawi.app;

import MoEzwawi.domain.BibliographicCollection;
import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.EntryType;
import MoEzwawi.util.Log;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>Application-level service that orchestrates bibliographic operations.</p>
 *
 * <p>This service acts as the central access point for creating and organizing
 * bibliographic items (Books, Journals, Papers, and Collections) using a configured
 * {@link BibliographicFactory}.</p>
 *
 * <p>Created items are automatically stored in their appropriate top-level containers:
 * regular entries go into the {@code library}, while collections are stored separately
 * in the {@code collections} container.</p>
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
     *
     * @param type   the type of bibliographic item to create (BOOK, JOURNAL, PAPER, or COLLECTION)
     * @param params a map of input parameters required by the factory for the specified type
     * @return the created bibliographic item
     */
    public BibliographicItem newItem(EntryType type, Map<String, String> params) {
        BibliographicItem item = null;
            item = this.factory.create(type, params);
            if (type != EntryType.COLLECTION){
                this.library.addItem(item);
                Log.info("Item added: " + item.summary());
            } else {
                this.collections.addItem(item);
                Log.info("Collection added: " + item.summary());
            }
        return item;
    }

    /**
     * Adds an item to an existing bibliographic collection.
     *
     * @param collection the target collection to add the item into
     * @param item the item to add (can be a Book, Journal, Paper or another Collection)
     * @throws IllegalArgumentException if the arguments are invalid or null
     */
    public void addToCollection(BibliographicItem collection, BibliographicItem item) {
        if (!(collection instanceof BibliographicCollection collectionInternalVar)) {
            throw new IllegalArgumentException("Collection param must be of type BibliographicCollection.");
        }
        if (item == null) throw new IllegalArgumentException("Item param must not be null");
        collectionInternalVar.addItem(item);
        Log.info("Item " + item.getTitle() + " added to collection: " + collection.getTitle());
    }


    /**
     * Returns an immutable list of all bibliographic items (excluding collections).
     *
     * @return a copy of the item list
     */
    public List<BibliographicItem> listAllItems() {
        return this.library.getItemsAtCurrentLevel();
    }
    /**
     * Returns an immutable list of all bibliographic collections.
     *
     * @return a copy of the collection list
     */
    public List<BibliographicItem> listCollections() {
        return this.collections.getItemsAtCurrentLevel();
    }
}
