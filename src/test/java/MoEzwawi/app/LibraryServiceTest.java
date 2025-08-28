package MoEzwawi.app;

import MoEzwawi.domain.BibliographicItem;
import MoEzwawi.domain.Book;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.EntryType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {
    @Test
    public void addItem_successfullyAddsBook() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        BibliographicItem book = new Book("Java guide", "Uncle Bob", 2008, "1234567890", 333);

        when(mockFactory.create(eq(EntryType.BOOK), anyMap())).thenReturn(book);

        LibraryService service = new LibraryService(mockFactory);
        BibliographicItem item = service.addItem(
                EntryType.BOOK,
                Map.of("title", "Java guide", "author", "Uncle Bob", "year", "2008")
        );

        assertNotNull(item);
        assertEquals("Java guide", item.getTitle());
        assertTrue(service.listAll().contains(book));
        verify(mockFactory).create(eq(EntryType.BOOK), anyMap());
    }
}
