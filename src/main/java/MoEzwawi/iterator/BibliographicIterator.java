package MoEzwawi.iterator;

import MoEzwawi.domain.BibliographicItem;
import java.util.NoSuchElementException;

/**
 * <p>Interface for iterating over a collection of {@link BibliographicItem} elements.</p>
 *
 * <p>This interface is part of the Iterator design pattern. It defines the operations
 * for traversing a collection of bibliographic items without exposing its internal structure.</p>
 *
 * <p>Typically used with {@link BibliographicAggregate} to retrieve an iterator instance.</p>
 */
public interface BibliographicIterator {

    /**
     * Checks if the collection has more bibliographic items.
     *
     * @return {@code true} if there are more elements to iterate over, {@code false} otherwise.
     */
    boolean hasNext();

    /**
     * Returns the next bibliographic item in the iteration.
     *
     * @return the next {@link BibliographicItem}.
     * @throws NoSuchElementException if no more elements are available.
     */
    BibliographicItem next();
}
