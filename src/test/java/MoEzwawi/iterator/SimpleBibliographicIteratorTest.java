package MoEzwawi.iterator;

import MoEzwawi.domain.Book;
import MoEzwawi.domain.Journal;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SimpleBibliographicIteratorTest {

    @Test
    public void iterator_traversesItemsInOrder() {
        BibliographicAggregate mockAggregate = mock(BibliographicAggregate.class);

        Book book = new Book("Title1", "Author1", 2020, "ISBN1", 200);
        Journal journal = new Journal("Title2", "Editor", 2021, "Vol.2");

        when(mockAggregate.getSize()).thenReturn(2);
        when(mockAggregate.getItemAt(0)).thenReturn(book);
        when(mockAggregate.getItemAt(1)).thenReturn(journal);

        BibliographicIterator iterator = new SimpleBibliographicIterator(mockAggregate);

        assertTrue(iterator.hasNext());
        assertEquals(book, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(journal, iterator.next());

        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);

        verify(mockAggregate, times(2)).getItemAt(anyInt());
        verify(mockAggregate, atLeastOnce()).getSize();
    }
}
