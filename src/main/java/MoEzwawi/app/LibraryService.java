package MoEzwawi.app;

import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.error.InvalidInputException;
import MoEzwawi.error.ShieldedException;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.EntryType;
import MoEzwawi.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Application-level service that orchestrates bibliographic operations.
 * Performs exception shielding to protect the user-facing interface.
 */
public class LibraryService {

    private final BibliographicFactory factory;
    private final List<BibliographicItem> library = new ArrayList<>();

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
    public BibliographicItem addItem(EntryType type, Map<String, String> params) {
        try {
            BibliographicItem item = factory.create(type, params);
            library.add(item);
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
     * Returns an immutable view of the stored items.
     *
     * @return a copy of the item list.
     */
    public List<BibliographicItem> listAll() {
        return List.copyOf(library);
    }
}
