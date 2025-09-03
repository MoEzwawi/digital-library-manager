package MoEzwawi.iterator;

import MoEzwawi.domain.BibliographicCollection;
import MoEzwawi.domain.Book;
import MoEzwawi.domain.Paper;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DepthFirstBibliographicIteratorTest {

    @Test
    public void iterator_traversesNestedLeafItemsInDepthOrder() {
        Book book1 = new Book("Book A", "Author A", 2010, "ISBN-A", 100);
        Paper paper1 = new Paper("Paper B", "Author B", 2022, "10.9999/demo.paper.01", "Conf X");

        // inner collection
        BibliographicCollection inner = new BibliographicCollection("Inner Coll", "Curator", 2023);
        inner.addItem(paper1);

        // outer collection
        BibliographicCollection outer = new BibliographicCollection("Main Coll", "Master", 2024);
        outer.addItem(book1);
        outer.addItem(inner);

        BibliographicIterator iterator = new DepthFirstBibliographicIterator(outer);

        assertTrue(iterator.hasNext());
        assertEquals(book1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(paper1, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
