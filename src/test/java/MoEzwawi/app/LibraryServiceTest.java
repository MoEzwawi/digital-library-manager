package MoEzwawi.app;

import MoEzwawi.domain.*;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.EntryType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibraryServiceTest {

    @Test
    public void newItem_shouldAddRegularItemToLibrary() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        Book book = new Book("Java for Dummies", "Barry Burd", 2006, "1234567890", 464);

        when(mockFactory.create(eq(EntryType.BOOK), anyMap())).thenReturn(book);

        LibraryService service = new LibraryService(mockFactory);
        BibliographicItem created = service.newItem(EntryType.BOOK, Map.of(
                "title", "Java for Dummies", "author", "Barry Burd", "year", "2006"
        ));

        assertNotNull(created);
        assertEquals("Java for Dummies", created.getTitle());
        assertEquals(1, service.listAllItems().size());
        assertTrue(service.listAllItems().contains(book));
        verify(mockFactory).create(eq(EntryType.BOOK), anyMap());
    }

    @Test
    public void newItem_shouldAddCollectionToCollectionsList() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        BibliographicCollection collection = new BibliographicCollection("My Collection", "Curator", 2024);

        when(mockFactory.create(eq(EntryType.COLLECTION), anyMap())).thenReturn(collection);

        LibraryService service = new LibraryService(mockFactory);
        BibliographicItem created = service.newItem(EntryType.COLLECTION, Map.of(
                "title", "My Collection", "author", "Curator", "year", "2024"
        ));

        assertNotNull(created);
        assertEquals("My Collection", created.getTitle());
        assertEquals(1, service.listCollections().size());
        assertTrue(service.listCollections().contains(collection));
        verify(mockFactory).create(eq(EntryType.COLLECTION), anyMap());
    }

    @Test
    public void addToCollection_shouldAddItemCorrectly() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        LibraryService service = new LibraryService(mockFactory);

        BibliographicCollection collection = new BibliographicCollection("My Collection", "Curator", 2024);
        Book book = new Book("Java for Dummies", "Barry Burd", 2006, "ISBN", 200);

        service.addToCollection(collection, book);

        assertEquals(1, collection.getItems().size());
        assertTrue(collection.getItems().contains(book));
    }

    @Test
    public void listAllItems_shouldReturnImmutableList() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        Book book = new Book("Java for Dummies", "Barry Burd", 2006, "ISBN", 200);

        when(mockFactory.create(eq(EntryType.BOOK), anyMap())).thenReturn(book);

        LibraryService service = new LibraryService(mockFactory);
        service.newItem(EntryType.BOOK, Map.of("title", "Java for Dummies", "author", "Barry Burd", "year", "2006"));

        List<BibliographicItem> items = service.listAllItems();
        assertThrows(UnsupportedOperationException.class, () -> items.add(book));
    }
}
