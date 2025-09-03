package MoEzwawi.iterator;

import MoEzwawi.domain.*;

import java.util.*;

/**
 * <p>
 * An iterator for traversing a {@link BibliographicAggregate} in depth-first order.
 * </p>
 *
 * <p>
 * This iterator implements the {@link BibliographicIterator} interface and recursively
 * visits all leaf {@link BibliographicItem} elements contained in the given root aggregate,
 * including those nested within sub-collections. It does not return {@link BibliographicAggregate}
 * nodes, only leaves such as {@link Book}, {@link Journal}, or {@link Paper}.
 * </p>
 *
 * <p>
 * This class is a concrete implementation of the Iterator design pattern tailored
 * for hierarchical bibliographic collections.
 * </p>
 */
public class DepthFirstBibliographicIterator implements BibliographicIterator {
    /**
     * Stack for managing traversal state. Each entry is an iterator over a collection's items.
     */
    private final Deque<BibliographicIterator> stack = new ArrayDeque<>();

    /**
     * Holds the next leaf {@link BibliographicItem} to be returned by the iterator.
     * <p>
     * This is a key state variable that tracks the upcoming item in the traversal,
     * enabling {@link #hasNext()} and {@link #next()} to operate efficiently and consistently.
     * It is updated by the {@code advance()} method, and set to {@code null} when no more items remain.
     * </p>
     */
    private BibliographicItem nextItem;

    /**
     * Constructs a new depth-first iterator for a given {@link BibliographicAggregate}.
     * Traversal begins at the root and proceeds recursively through all sub-collections.
     *
     * @param root the root {@link BibliographicAggregate} to iterate over
     */
    public DepthFirstBibliographicIterator(BibliographicAggregate root) {
        if (root != null) {
            this.stack.push(root.iterator());
            this.advance();
        }
    }

    /**
     * Moves the iterator to the next available leaf {@link BibliographicItem}.
     * <p>
     * This method performs a depth-first traversal by:
     * <ul>
     *   <li>Recursively entering sub-collections when encountered.</li>
     *   <li>Skipping non-leaf {@link BibliographicAggregate} nodes.</li>
     *   <li>Setting {@code nextItem} to the next leaf item, or {@code null} if no more items remain.</li>
     * </ul>
     * The traversal state is maintained using a stack of iterators, ensuring all leaf items are visited in the correct order.
     * </p>
     */
    private void advance() {
        nextItem = null;
        while (!stack.isEmpty()) {
            BibliographicIterator currentIterator = stack.peek();
            if (currentIterator.hasNext()) {
                BibliographicItem item = currentIterator.next();
                if (item instanceof BibliographicAggregate aggregate) {
                    stack.push(aggregate.iterator());
                } else {
                    nextItem = item;
                    return;
                }
            } else {
                stack.pop();
            }
        }
    }

    /**
     * Checks if there are more leaf items to visit in the traversal.
     *
     * @return {@code true} if another leaf item is available, {@code false} otherwise
     */
    @Override
    public boolean hasNext() {
        return nextItem != null;
    }

    /**
     * Returns the next leaf {@link BibliographicItem} in the depth-first traversal.
     *
     * @return the next leaf item
     * @throws NoSuchElementException if no more items are available
     */
    @Override
    public BibliographicItem next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        BibliographicItem result = this.nextItem;
        advance();
        return result;
    }
}
