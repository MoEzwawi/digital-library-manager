package MoEzwawi.error;

import MoEzwawi.app.LibraryService;
import MoEzwawi.factory.BibliographicFactory;
import MoEzwawi.factory.EntryType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExceptionShieldingTest {
    @Test
    public void addItem_invalidInputIsShielded() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        when(mockFactory.create(eq(EntryType.BOOK), anyMap()))
                .thenThrow(new InvalidInputException("Missing title"));

        LibraryService service = new LibraryService(mockFactory);

        ShieldedException ex = assertThrows(ShieldedException.class, () ->
                service.newItem(EntryType.BOOK, Map.of("author", "A", "year", "2008"))
        );

        assertTrue(ex.getUserMessage().toLowerCase().contains("invalid input"));
        assertInstanceOf(InvalidInputException.class, ex.getCause());
    }

    @Test
    public void addItem_unexpectedErrorIsShielded() {
        BibliographicFactory mockFactory = mock(BibliographicFactory.class);
        when(mockFactory.create(eq(EntryType.BOOK), anyMap())).
                thenThrow(new RuntimeException("boom"));

        LibraryService service = new LibraryService(mockFactory);
        ShieldedException ex = assertThrows(ShieldedException.class, ()->
                service.newItem(EntryType.BOOK, Map.of("title", "Python magic", "author", "Guido")));

        assertTrue(ex.getUserMessage().toLowerCase().contains("unexpected"));
        assertInstanceOf(RuntimeException.class, ex.getCause());
    }
}
