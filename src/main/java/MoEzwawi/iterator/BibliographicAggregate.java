package MoEzwawi.iterator;

import MoEzwawi.domain.BibliographicCollection;
import MoEzwawi.domain.BibliographicItem;


/**
 * Interface representing a collection or aggregate of {@link BibliographicItem} instances
 * that can be traversed using the Iterator design pattern.
 * <p>
 * This interface defines the contract for aggregates that:
 * <ul>
 *   <li>Provide a factory method for creating a {@link BibliographicIterator} to traverse their elements, decoupling traversal logic from internal representation.</li>
 *   <li>Expose indexed access to their immediate children for efficient and flexible iteration.</li>
 *   <li>Report the number of direct children they contain, supporting size-based operations and bounds checking.</li>
 * </ul>
 * <p>
 * Implementations of this interface are typically composite classes, such as {@link BibliographicCollection},
 * but may also include other specialized aggregates in hierarchical or non-hierarchical bibliographic structures.
 * </p>
 */
public interface BibliographicAggregate {

    /**
     * Creates and returns an iterator for traversing the collection.
     *
     * @return a {@link BibliographicIterator} for this aggregate.
     */
    BibliographicIterator iterator();

    /**
     * Returns a bibliographic item at a specified index, if present.
     *
     * @return a {@link BibliographicItem} at a specified index.
     */
    BibliographicItem getItemAt(int index);

    /**
     * Returns the number of direct children of the aggregate.
     *
     * @return the size of the aggregate.
     */
    int getSize();
}

