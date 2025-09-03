package MoEzwawi.iterator;

import MoEzwawi.domain.BibliographicItem;
import java.util.NoSuchElementException;

/**
 * A simple iterator for traversing the immediate items of a {@link BibliographicAggregate} by index.
 * <p>
 * This iterator provides sequential access to each {@link BibliographicItem} in the aggregate,
 * without descending into nested sub-collections.
 * </p>
 */
public class SimpleBibliographicIterator implements BibliographicIterator {
    private final BibliographicAggregate aggregate;
    private int currentIndex = 0;

    /**
     * Constructs a new iterator for the specified aggregate.
     *
     * @param aggregate the {@link BibliographicAggregate} to iterate over
     */
    public SimpleBibliographicIterator(BibliographicAggregate aggregate) {
        this.aggregate = aggregate;
    }

    /**
     * Checks if there are more bibliographic items in the aggregate.
     *
     * @return {@code true} if there are more items, {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return this.currentIndex < this.aggregate.getSize();
    }

    /**
     * Returns the next {@link BibliographicItem} and updates the {@link #currentIndex}.
     *
     * @return the next {@link BibliographicItem}.
     * @throws NoSuchElementException if no more items are available.
     */
    @Override
    public BibliographicItem next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return aggregate.getItemAt(currentIndex++);
    }
}